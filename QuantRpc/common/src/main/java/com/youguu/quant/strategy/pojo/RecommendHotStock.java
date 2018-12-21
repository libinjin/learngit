package com.youguu.quant.strategy.pojo;

import java.util.Date;
/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/2 14:15
 */
public class RecommendHotStock {

    private int id;
    private int strategyId;
    private String stockCode;
    private int type; //1-推荐股票；2-热门股票
    private Date createTime;
    private String stockName;

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

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public String toString() {
        return "RecommendHotStock{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", stockCode='" + stockCode + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", stockName='" + stockName + '\'' +
                '}';
    }
}
