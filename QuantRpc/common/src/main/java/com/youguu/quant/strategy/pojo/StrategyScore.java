package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/25.
 * 全部策略的平均盈利，成功交易次数
 */
public class StrategyScore implements Serializable {
    private int strategyId;
    private int successNum;
    private double averageProfit;
    private Date updateTime;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public double getAverageProfit() {
        return averageProfit;
    }

    public void setAverageProfit(double averageProfit) {
        this.averageProfit = averageProfit;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StrategyScore{" +
                "successNum=" + successNum +
                ", averageProfit=" + averageProfit +
                ", updateTime=" + updateTime +
                '}';
    }
}
