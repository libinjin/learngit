package com.youguu.quant.strategy.pojo;

import java.util.Date;

/**
 * 自选股交易记录
 */
public class StrategyPortfolio {
    private Date tradeTime;//交易时间
    private String direction;//买卖方向,B-买入；S-卖出；H-持有
    private String stockCode;//股票代码（8位）
    private String stockName;//股票名称
    private double price;//交易价格

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
