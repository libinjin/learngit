package com.youguu.quant.signal.pojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/8 16:59
 */
public class StockNetData {

    private String stockCode;
    private LinkedHashMap<String,Double> dayNet;
    private double midNet;
    private double totalNet;
    private List<StrategyTradeRecord> tradeRecords;


    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Map<String, Double> getDayNet() {
        return dayNet;
    }

    public void setDayNet(LinkedHashMap<String, Double> dayNet) {
        this.dayNet = dayNet;
    }

    public double getMidNet() {
        return midNet;
    }

    public void setMidNet(double midNet) {
        this.midNet = midNet;
    }

    public double getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(double totalNet) {
        this.totalNet = totalNet;
    }

    public List<StrategyTradeRecord> getTradeRecords() {
        return tradeRecords;
    }

    public void setTradeRecords(List<StrategyTradeRecord> tradeRecords) {
        this.tradeRecords = tradeRecords;
    }

    @Override
    public String toString() {
        return "StockNetData{" +
                "stockCode='" + stockCode + '\'' +
                ", dayNet=" + dayNet +
                ", midNet=" + midNet +
                ", totalNet=" + totalNet +
                ", tradeRecords=" + tradeRecords +
                '}';
    }
}
