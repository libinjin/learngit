package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xyj on 2017/3/7.
 * 存储策略涨停股票
 */
public class StrategyLimitUp implements Serializable {

    private int id;
    private int strategyId;
    private String stockCode;
    private Date createTime;
    private int num;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "StrategyLimitUp{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", stockCode='" + stockCode + '\'' +
                ", createTime=" + createTime +
                ", num=" + num +
                '}';
    }
}
