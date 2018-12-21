package com.youguu.quant.signal.pojo;

import java.io.Serializable;

/**
 * 记录当日，近五日，当前持仓股票的当日盈利和累计盈利
 * Created by xyj on 2017/1/16.
 */
public class Stock5dayAndNowHold implements Serializable {

    /**
     * 策略id
     */
    private String strategyName;

    /**
     * 股票代码 8位
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 买入时间
     */
    private long tradeTime;

    /**
     * 价格 扩大一万倍
     */
    private int price;

    /**
     * 当日涨幅
     */
    private double profit;

    /**
     * 累计涨幅
     */
    private double countProfit;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

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

    public long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getCountProfit() {
        return countProfit;
    }

    public void setCountProfit(double countProfit) {
        this.countProfit = countProfit;
    }

    @Override
    public String toString() {
        return "Stock5dayAndNowHold{" +
                "strategyName='" + strategyName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", tradeTime=" + tradeTime +
                ", price=" + price +
                ", profit=" + profit +
                ", countProfit=" + countProfit +
                '}';
    }
}
