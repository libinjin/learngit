package com.youguu.quant.signal.pojo;

/**
 * Created by SomeBody on 2016/8/25.
 * 总收益
 */
public class SumProfit implements Comparable<SumProfit>{
    private int strategyId;
    private String stockCode;
    private String stockName;
    private String profitRate;

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

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public String toString() {
        return "SumProfit{" +
                "strategyId=" + strategyId +
                ", stockCode='" + stockCode + '\'' +
                ", profitRate=" + profitRate +
                '}';
    }

    @Override
    public int compareTo(SumProfit o) {
        try {
            double a = new Double(this.profitRate);
            double b = new Double(o.getProfitRate());
            if (a >= b)
                return -1;
            else
                return 1;
        }catch (Exception e){
            return 1;
        }
    }
}
