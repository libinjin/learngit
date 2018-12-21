package com.youguu.quant.strategy.pojo;

/**
 * Created by SomeBody on 2016/8/31.
 */
public class AverageProfit {
    private double profit;//平均收益率
    private double overtop;//超越沪深300点数

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getOvertop() {
        return overtop;
    }

    public void setOvertop(double overtop) {
        this.overtop = overtop;
    }
}
