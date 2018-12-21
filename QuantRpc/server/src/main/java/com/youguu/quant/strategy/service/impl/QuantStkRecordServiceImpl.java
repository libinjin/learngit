package com.youguu.quant.strategy.service.impl;

import com.youguu.asteroid.rpc.client.AsteroidRPCClientFactory;
import com.youguu.asteroid.rpc.client.tradeday.ITradeDayRPCService;
import com.youguu.asteroid.tradeday.pojo.MncgStkDiv;
import com.youguu.core.util.QuoteUtil;
import com.youguu.quant.signal.pojo.TradeSignal;
import com.youguu.quant.strategy.dao.IQuantStkRecordDAO;
import com.youguu.quant.strategy.pojo.QuantStkRecord;
import com.youguu.quant.strategy.service.IQuantStkRecordService;
import com.youguu.quant.strategy.service.IStrategyService;
import com.youguu.quant.util.FileUtil;
import com.youguu.quant.util.QuantTradePlateConfig;
import com.youguu.quote.pojo.XRDRAdjustFactor;
import com.youguu.quote.pojo.XRDRRecord;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xyj on 2016/9/28.
 */
@Service("quantStkRecordService")
public class QuantStkRecordServiceImpl implements IQuantStkRecordService {

    @Resource
    private IQuantStkRecordDAO quantStkRecordDAO;

    @Resource
    private IStrategyService strategyService;

    ITradeDayRPCService tradeDayRPCService = AsteroidRPCClientFactory.getTradeDayRPCService();

    @Override
    public int saveQuantStkRecord(QuantStkRecord qsr) {
        return quantStkRecordDAO.saveQuantStkRecord(qsr);
    }

    @Override
    public boolean ifExists(int rid, int sid) {
        return quantStkRecordDAO.ifExists(rid, sid) > 0 ? true : false;
    }

    @Override
    public String disposeQuantStk(int strategyId, Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String exdivDate = sdf.format(time);
        long exdivTime = Long.valueOf(sdf2.format(time) + "0000");

        //策略文件地址
        String path = QuantTradePlateConfig.getInstance().getStockFilePath();
        String filePath = path + File.separator + "%s" + File.separator + "%s" + ".dat";//策略id,股票代码
        String folderPath = path + File.separator + "%s" + File.separator + "%s" + "_bak" + File.separator;//策略id,日期

        //查询指定日期除权除息数据
        List<MncgStkDiv> listMsd = tradeDayRPCService.findAllMncgStkDivByDate(exdivDate);
        if (listMsd == null || listMsd.size() == 0) {
            return "0;除权除息数据为空";
        }

        //遍历除权除息集合List
        String stockCode = "";
        List<TradeSignal> tsList;//旧信号列表
        List<TradeSignal> mList;//需要
        List<TradeSignal> nmList;
        XRDRRecord xr;
        XRDRAdjustFactor xf;
        QuantStkRecord qsr;
        boolean backFlag = false;
        for (MncgStkDiv msd : listMsd) {
            stockCode = QuoteUtil.convertTo8(msd.getStockCode());
            //封装除权记录对象
            xr = getXRDRRecord(msd, time);
            //获得因子
            xf = calcForwardFactor(xr);

            mList = new ArrayList<>();
            nmList = new ArrayList<>();

            filePath = path + File.separator + "%s" + File.separator + "%s" + ".dat";//策略id,股票代码
            //判断该策略下的股票是否做过除权处理
            if (ifExists(msd.getId(), strategyId)) {
                continue;
            }

            //获取一支股票文件的实际路径
            filePath = String.format(filePath, strategyId, stockCode);

            //读取股票文件转换为信号List
            tsList = FileUtil.read(filePath, 0, -1);
            if (tsList == null || tsList.size() == 0) {
                continue;
            }

            //遍历信号List,过滤出需要修改的数据集合
            for (TradeSignal ts : tsList) {
                if (ts.getDatetime() < exdivTime) {
                    mList.add(ts);
                } else {
                    nmList.add(ts);
                }
            }
            //计算处理需要除权价格的List
            calculate(mList, xf);

            //增加除权处理记录
            qsr = new QuantStkRecord();
            copyProperties(qsr, msd, strategyId);
            saveQuantStkRecord(qsr);

            //备份源文件
            backFlag = backupsFile(filePath, folderPath, strategyId, stockCode);
            if (!backFlag) {
                return "0;备份文件失败";
            }

            //生成新文件
            createFile(mList, nmList, filePath);
        }

        return "1;处理除权除息数据成功";
    }

    /**
     * 生成新的文件
     *
     * @param mList
     * @param nmList
     * @param filePath
     */
    private void createFile(List<TradeSignal> mList, List<TradeSignal> nmList, String filePath) {
        List<TradeSignal> list = new ArrayList<>();
        list.addAll(mList);
        list.addAll(nmList);
        for (TradeSignal ts : list) {
            FileUtil.write(filePath, ts);
        }
    }

    /**
     * 备份文件
     *
     * @param filePath
     * @return
     */
    private boolean backupsFile(String filePath, String folderPath, int sid, String stockCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        File file1 = new File(filePath);
        String tempPath = String.format(folderPath, sid, sdf.format(new Date()));
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        tempPath = tempPath + stockCode + ".dat";
        File file2 = new File(tempPath);
        return file1.renameTo(file2);
    }

    /**
     * 前复权因子计算
     *
     * @param xrdr
     * @return
     */
    private XRDRAdjustFactor calcForwardFactor(XRDRRecord xrdr) {
        double n = 1;
        double m = 0;

        m = m + n * (xrdr.getPlacingPrice() * xrdr.getPlacingRatio() - xrdr.getCashBTax()) / (1 + xrdr.getPlacingRatio() + xrdr.getBonusRatio() + xrdr.getTransferRatio());

        n = n * (1 / (xrdr.getPlacingRatio() + xrdr.getBonusRatio() + xrdr.getTransferRatio() + 1));

        XRDRAdjustFactor xrdrFactor = new XRDRAdjustFactor();
        xrdrFactor.setFactor(n);
        xrdrFactor.setConstant(m);

        return xrdrFactor;
    }

    /**
     * 封装除权对象
     *
     * @param msd
     * @param time
     * @return
     */
    private XRDRRecord getXRDRRecord(MncgStkDiv msd, Date time) {
        XRDRRecord xr = new XRDRRecord();
        xr.setExdiviDate(time);
        xr.setPlacingPrice(msd.getPlacingPrice() / 10);
        xr.setPlacingRatio(msd.getPlacingRatio() / 10);
        xr.setCashBTax(msd.getCashBt() / 10);
        xr.setTransferRatio(msd.getCapShr() / 10);
        xr.setBonusRatio(msd.getBonusShr() / 10);
        return xr;
    }

    /**
     * 计算除权价格
     *
     * @param mList
     * @param xf
     */
    private void calculate(List<TradeSignal> mList, XRDRAdjustFactor xf) {
        double price;
        double temp;
        double cPrice;
        double cTemp;
        for (TradeSignal ts : mList) {
            if(ts.getPrice() > 0)
            {
                price = divide(ts.getPrice(),10000,3) ;
                temp = add(multiply(price, xf.getFactor(), 2), xf.getConstant(), 2);
                temp = temp * 10000;
                ts.setPrice(new Double(temp).intValue());
            }

            if(ts.getCloseprice() > 0)
            {
                cPrice = divide(ts.getCloseprice(),10000,3) ;
                cTemp = add(multiply(cPrice, xf.getFactor(), 2), xf.getConstant(), 2);
                cTemp = cTemp * 10000;
                ts.setCloseprice(new Double(cTemp).intValue());
            }
        }
    }

    /**
     * 小数位数相除
     * @param arg1
     * @param arg2
     * @param scale
     * @return
     */
    private double divide(double arg1,double arg2,int scale)
    {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 小数相加,四舍五入进位
     *
     * @param arg1
     * @param arg2
     * @return
     */
    private double add(double arg1, double arg2, int scale) {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.add(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 小数相乘,四舍五入进位
     *
     * @param arg1
     * @param arg2
     * @return
     */
    private double multiply(double arg1, double arg2, int scale) {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.multiply(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 对象属性复制
     *
     * @param qsr
     * @param msd
     * @param sid
     */
    private void copyProperties(QuantStkRecord qsr, MncgStkDiv msd, int sid) {
        try {
            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            BeanUtils.copyProperties(qsr, msd);
            qsr.setRid(qsr.getId());
            qsr.setSid(sid);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
