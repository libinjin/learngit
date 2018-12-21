package com.youguu.quant.strategy.pojo;

import java.util.Date;

/**
 * Created by lenovo on 2016/11/18.
 * 当日交易明细
 * 每天实时信号更新 ，
 * 每个交易日凌晨清空数据
 * 每次清算会重新计算收益 ， 更新rank
 */
public class StockTradeToday {

    public static final int TYPE_BUY = 1;
    public static final int TYPE_SELL = 2;


    private int id;

    /**
     * 策略id
     */
    private int strategyId;
    /**
     * 数据类型，1-当日买入；2-当日卖出
     */
    private int type;
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
     * yyyyMMddHH24mi
     */
    private long tradeTime;

    /**
     * 价格 扩大一万倍
     */
    private int price;

    /**
     * 收益率
     * -99 代表默认值 ， 表示还没有生成数据
     */
    private double profit = -99.0;

    /**
     * 排序字段
     * 实时信号 rank规则 HHMMSS + stockcode
     * 清算之后 rank规则 收益率四位 + stockcode
     */
    private long rank;

    /**
     * 入库时间
     */
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StockTradeToday{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", type=" + type +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", tradeTime=" + tradeTime +
                ", price=" + price +
                ", profit=" + profit +
                ", rank=" + rank +
                ", createTime=" + createTime +
                '}';
    }
}
