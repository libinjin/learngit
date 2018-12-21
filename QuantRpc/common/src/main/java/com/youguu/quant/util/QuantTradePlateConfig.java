package com.youguu.quant.util;

import com.youguu.core.zookeeper.listener.base.DataChangedListener;
import com.youguu.core.zookeeper.pro.ZkPropertiesHelper;

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
    private String realOriginFilePath;
    private String hisOriginFilePath;
    private String parseStockFilePath;
    private String parseRealOriginFilePath;
    private String parseHisOriginFilePath;

    private String warnPhone;
    private int warnTimeInterval;
    private Properties pro;


    public QuantTradePlateConfig() {
        pro = ZkPropertiesHelper.getAndWatchProperties("config.properties", this);
        this.assignment(pro);
    }

    public void assignment(Properties pro) {
        this.stockFilePath = pro.getProperty("stock.file.path");
        this.realOriginFilePath = pro.getProperty("real.origin.file.path");
        this.hisOriginFilePath = pro.getProperty("his.origin.file.path");
        this.parseStockFilePath = pro.getProperty("parse.stock.file.path");
        this.parseRealOriginFilePath = pro.getProperty("parse.real.origin.file.path");
        this.parseHisOriginFilePath = pro.getProperty("parse.his.origin.file.path");
        this.warnPhone = pro.getProperty("warn.phone");
        this.warnTimeInterval = Integer.parseInt(pro.getProperty("warn.time.interval"));
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

    public String getRealOriginFilePath() {
        return realOriginFilePath;
    }

    public String getHisOriginFilePath() {
        return hisOriginFilePath;
    }

    public String getParseRealOriginFilePath() {
        return parseRealOriginFilePath;
    }

    public String getParseHisOriginFilePath() {
        return parseHisOriginFilePath;
    }

    public String getParseStockFilePath() {
        return parseStockFilePath;
    }

    public String getWarnPhone() {
        return warnPhone;
    }

    public int getWarnTimeInterval() {
        return warnTimeInterval;
    }

}
