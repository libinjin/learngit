package com.youguu.quant.signal.pojo;

/**
 * Created by SomeBody on 2016/8/25.
 * 股票最新交易信号
 */
public class StockRealtimeSignal {
    private String date;//MM-dd hh:mm
    private String direction;
    private String stockCode;
    private String stockName;
    private String price;

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

    @Override
    public String toString() {
        return "StockRealtimeSignal{" +
                "date='" + date + '\'' +
                ", direction='" + direction + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
