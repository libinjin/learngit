package com.youguu.quant.signal.pojo;

/**
 * Created by SomeBody on 2016/8/25.
 * 近三年盈利曲线
 */
public class StrategyTradeProfit {
    private int strategyId;
    private Double oneYearProfit;
    private Double threeYearProfit;
    private ProfitCurve profitCurve;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public Double getOneYearProfit() {
        return oneYearProfit;
    }

    public void setOneYearProfit(Double oneYearProfit) {
        this.oneYearProfit = oneYearProfit;
    }

    public Double getThreeYearProfit() {
        return threeYearProfit;
    }

    public void setThreeYearProfit(Double threeYearProfit) {
        this.threeYearProfit = threeYearProfit;
    }

    public ProfitCurve getProfitCurve() {
        return profitCurve;
    }

    public void setProfitCurve(ProfitCurve profitCurve) {
        this.profitCurve = profitCurve;
    }
}
