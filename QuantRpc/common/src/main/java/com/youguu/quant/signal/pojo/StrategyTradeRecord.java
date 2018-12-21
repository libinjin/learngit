package com.youguu.quant.signal.pojo;

/**
 * Created by SomeBody on 2016/8/25.
 */
public class StrategyTradeRecord implements Comparable<StrategyTradeRecord>{
    private String date;
    private String direction;
    private String stockCode;
    private String stockName;
    private String price;
    private String profitRate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    @Override
    public String toString() {
        return "StrategyTradeRecord{" +
                "date='" + date + '\'' +
                ", direction='" + direction + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", price='" + price + '\'' +
                ", profitRate='" + profitRate + '\'' +
                '}';
    }
    @Override
    public int compareTo(StrategyTradeRecord o) {
        return (int)(new Long(date)-new Long(o.getDate()));
    }
}
