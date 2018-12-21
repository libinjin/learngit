package com.youguu.quant.strategy.pojo;

import java.util.Date;

/**
 * Created by lenovo on 2016/11/18.
 * 策略扩展属性表
 * 表中的数据需要在清算的时候重新生成持仓数量 ，
 * 每个交易日凌晨清理买入 ， 卖出的数量
 *
 */
public class StrategyExtend {
    /**
     * 策略id
     */
    private int strategyId;

    /**
     * 今日买入数量
     */
    private int buyNum;

    /**
     * 今日卖出数量
     */
    private int sellNum;

    /**
     * 当前持仓数量
     */
    private int positionNum;

    /**
     * 修改时间
     */
    private Date updateTime;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public int getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(int positionNum) {
        this.positionNum = positionNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StrategyExtend{" +
                "strategyId=" + strategyId +
                ", buyNum=" + buyNum +
                ", sellNum=" + sellNum +
                ", positionNum=" + positionNum +
                ", updateTime=" + updateTime +
                '}';
    }
}
