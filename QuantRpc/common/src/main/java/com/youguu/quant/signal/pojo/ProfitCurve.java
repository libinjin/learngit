package com.youguu.quant.signal.pojo;

/**
 * Created by SomeBody on 2016/8/25.
 * 盈利曲线
 */
public class ProfitCurve implements Comparable<ProfitCurve> {
    private String date;
    private double profitRate;//策略盈利
    private double hsProfitRate;//沪深盈利


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getHsProfitRate() {
        return hsProfitRate;
    }

    public void setHsProfitRate(double hsProfitRate) {
        this.hsProfitRate = hsProfitRate;
    }

    @Override
    public String toString() {
        return "ProfitCurve{" +
                "date='" + date + '\'' +
                ", profitRate='" + profitRate + '\'' +
                ", hsProfitRate='" + hsProfitRate + '\'' +
                '}';
    }

    @Override
    public int compareTo(ProfitCurve o) {
        return (int) (new Long(date) - new Long(o.getDate()));
    }
}
