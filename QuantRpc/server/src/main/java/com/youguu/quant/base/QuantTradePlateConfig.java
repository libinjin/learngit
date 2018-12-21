package com.youguu.quant.base;

import com.youguu.core.util.ParamUtil;
import com.youguu.core.zookeeper.listener.base.DataChangedListener;
import com.youguu.core.zookeeper.pro.ZkPropertiesHelper;
import com.youguu.quant.strategy.pojo.UserLoop;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * 加载量化交易平台配置文件
 */
public class QuantTradePlateConfig implements DataChangedListener {
    private static QuantTradePlateConfig quantTradePlateConfig = new QuantTradePlateConfig();

    public static QuantTradePlateConfig getInstance() {
        return quantTradePlateConfig;
    }

    /**
     * 股票文件存放的基本路径
     */
    private String stockFilePath;
    private int loopNum;
    private int shareNum;

    //破解dna相关的数据
    //默认免费次数
    private int dnaLoopNum;
    //可分享次数
    private int dnaShareNum;
    //每次分享获得免费次数
    private int dnaShareLoopNum;
    private String str_share;
    private String str_share_end;

    private Properties pro;


    public QuantTradePlateConfig() {
        pro = ZkPropertiesHelper.getAndWatchProperties("config.properties", this);
        this.assignment(pro);
    }

    public void assignment(Properties pro) {
        this.stockFilePath = pro.getProperty("stock.file.path");
        this.loopNum = ParamUtil.CheckParam(pro.getProperty("loop.num"),0);
        this.shareNum = ParamUtil.CheckParam(pro.getProperty("share.num"),0);
        this.dnaLoopNum = ParamUtil.CheckParam(pro.getProperty("dna.loopnum"),0);
        this.dnaShareNum = ParamUtil.CheckParam(pro.getProperty("dna.sharenum"),0);
        this.dnaShareLoopNum = ParamUtil.CheckParam(pro.getProperty("dna.dnaShareLoopNum"),0);
        this.str_share = pro.getProperty("dna.strshare");
        this.str_share_end = pro.getProperty("dna.strshareend");

    }

    @Override
    public void reload(String s) {
        Properties pro = new Properties();
        try {
            pro.load(new StringReader(s));
            this.assignment(pro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStockFilePath() {
        return stockFilePath;
    }



    public String getStr_share_end() {
        return str_share_end;
    }

    public String getStr_share() {
        return str_share;
    }




    public int getTypeLoopNum(int type){
        int num = 1;
        switch (type){
            case UserLoop.TYPE_REPORT:
                num = this.loopNum;
                break;
            case UserLoop.TYPE_DNA:
                num = this.dnaLoopNum;
                break;
            default:
                break;
        }
        return num;

    }

    public int getTypeShareNum(int type){
        int num = 1;
        switch (type){
            case UserLoop.TYPE_REPORT:
                num = this.shareNum;
                break;
            case UserLoop.TYPE_DNA:
                num = this.dnaShareNum;
                break;
            default:
                break;
        }
        return num;

    }

    public int getTypeShareLoopNum(int type){
        int num = 1;
        switch (type){
            case UserLoop.TYPE_REPORT:
                num = 1;
                break;
            case UserLoop.TYPE_DNA:
                num = this.dnaShareLoopNum;
                break;
            default:
                break;
        }
        return num;

    }
}
