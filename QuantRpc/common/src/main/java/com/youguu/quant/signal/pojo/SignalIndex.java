package com.youguu.quant.signal.pojo;

import java.util.List;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/1 20:42
 */
public class SignalIndex {

    private int strategyId;
    private List<ProfitCurve> profitCurves;     //收益曲线
    private List<SumProfit> sumProfits;         //每只股票的总收益率
    private List<StrategyTradeRecord> strategyTradeRecords; //前3条交易记录
    private String allSumProfit;    //策略近三年总收益率
    private String oneAllSumProfit; //策略近一年总收益率
    private String allComHS;        //策略近三年总收益率超越同期沪深
    private String oneAllComHs;     //策略近一年总收益率超越同期沪深
    private Double highValue;       //曲线最高点
    private Double lowValue;        //曲线最低点

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public List<ProfitCurve> getProfitCurves() {
        return profitCurves;
    }

    public void setProfitCurves(List<ProfitCurve> profitCurves) {
        this.profitCurves = profitCurves;
    }

    public List<SumProfit> getSumProfits() {
        return sumProfits;
    }

    public void setSumProfits(List<SumProfit> sumProfits) {
        this.sumProfits = sumProfits;
    }

    public List<StrategyTradeRecord> getStrategyTradeRecords() {
        return strategyTradeRecords;
    }

    public void setStrategyTradeRecords(List<StrategyTradeRecord> strategyTradeRecords) {
        this.strategyTradeRecords = strategyTradeRecords;
    }

    public String getAllSumProfit() {
        return allSumProfit;
    }

    public void setAllSumProfit(String allSumProfit) {
        this.allSumProfit = allSumProfit;
    }

    public String getOneAllSumProfit() {
        return oneAllSumProfit;
    }

    public void setOneAllSumProfit(String oneAllSumProfit) {
        this.oneAllSumProfit = oneAllSumProfit;
    }

    public String getAllComHS() {
        return allComHS;
    }

    public void setAllComHS(String allComHS) {
        this.allComHS = allComHS;
    }

    public String getOneAllComHs() {
        return oneAllComHs;
    }

    public void setOneAllComHs(String oneAllComHs) {
        this.oneAllComHs = oneAllComHs;
    }

    public Double getHighValue() {
        return highValue;
    }

    public void setHighValue(Double highValue) {
        this.highValue = highValue;
    }

    public Double getLowValue() {
        return lowValue;
    }

    public void setLowValue(Double lowValue) {
        this.lowValue = lowValue;
    }

    @Override
    public String toString() {
        return "SignalIndex{" +
                "strategyId=" + strategyId +
                ", profitCurves=" + profitCurves +
                ", sumProfits=" + sumProfits +
                ", strategyTradeRecords=" + strategyTradeRecords +
                ", allSumProfit='" + allSumProfit + '\'' +
                ", oneAllSumProfit='" + oneAllSumProfit + '\'' +
                ", allComHS='" + allComHS + '\'' +
                ", oneAllComHs='" + oneAllComHs + '\'' +
                '}';
    }
}
