package com.youguu.quant.signal.service.impl;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.base.QuantTradePlateConfig;
import com.youguu.quant.signal.pojo.TradeSignal;
import com.youguu.quant.signal.service.ISignalService;
import com.youguu.quant.util.FileUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lqipr on 2016/8/31.
 */
public class SignalServiceTest extends BaseTestClass {

    static ISignalService service = (ISignalService)getBean("signalService");

    @Test
    public void test1(){
//        TradeSignal tradeSignal = service.getLastTradeSignal(1111, "11600107");
//        System.out.println(tradeSignal.toString());

        Map<String, List<TradeSignal>> map =  service.loadTradeSignal(1111, "11600082");
        List<TradeSignal> list = map.get("11600082");
        for(TradeSignal tradeSignal : list){
            System.out.println(tradeSignal.toString());
        }
    }

    @Test
    public void testCountNowHoldStock(){
        System.out.println(service.countNowHoldStock(2238,0.08));
    }

    @Test
    public void test2(){
        List<TradeSignal> list = null;
        try {
            String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
            basePath = basePath + "/1111/11600082.dat";
            list = FileUtil.read(basePath, 0, -1);
            for(TradeSignal tradeSignal : list){
                System.out.println(tradeSignal.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        double d = -0.038821003574011095;
        double i = 1.0;
        System.out.println(i+d);
    }

    @Test
    public void testCountProfit(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time  = "2016-11-08 00:00:00";
            Date date = sdf.parse(time);
            System.out.println(service.countProfit(2236,date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDisReportData(){
        System.out.println(service.disReportData(2238));
    }


    @Test
    public void findDnaStockHold(){
        System.out.println(service.findDnaStockHold("11600874",224));
    }

    @Test
    public void testGetStockNames(){
        System.out.println(service.getStockName("11732042"));
    }

}
