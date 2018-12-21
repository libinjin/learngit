package com.youguu.quant.strategy.pojo;

/**
 * 自选股预测
 */
public class StrategyPortfolioForecast {
    private String stockCode;//股票代码（8位）
    private String stockName;//股票名称
    private double chg;//平均涨（跌）幅
    private double upProbability;//上涨概率
    private double downProbability;//下跌概率
    private String nodata;//1-没有预测数据；2-有效数据

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getChg() {
        return chg;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }

    public double getUpProbability() {
        return upProbability;
    }

    public void setUpProbability(double upProbability) {
        this.upProbability = upProbability;
    }

    public double getDownProbability() {
        return downProbability;
    }

    public void setDownProbability(double downProbability) {
        this.downProbability = downProbability;
    }

    public String getNodata() {
        return nodata;
    }

    public void setNodata(String nodata) {
        this.nodata = nodata;
    }
}
