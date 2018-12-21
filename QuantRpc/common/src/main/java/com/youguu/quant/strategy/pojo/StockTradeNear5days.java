package com.youguu.quant.strategy.pojo;

import java.util.Date;

/**
 * Created by lenovo on 2016/11/18.
 * 近五日买入且持仓股票
 * 清算时生成数据
 */
public class StockTradeNear5days {
    private  int id;
    /**
     * 策略id
     */
    private int strategyId;

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
     * 收益率
     */
    private double profit;

    /**
     * 入库时间
     */
    private Date createTime;

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    /**
     * 排序字段
     * 清算之后 rank规则 收益率四位 + stockcode
     */
    private long rank;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StockTradeNear5days{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", tradeTime=" + tradeTime +
                ", price=" + price +
                ", profit=" + profit +
                ", createTime=" + createTime +
                ", rank=" + rank +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
