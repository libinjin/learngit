package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/25.
 */
public class StrategyBestStock implements Comparable<StrategyBestStock>, Serializable {
    private int strategyId;
    private String strategyName;
    private String stockCode;
    private String stockName;
    private String findTime;
    private String findTimeStr;
    private Double profit;
    private String profitStr;
    private Double rank;
    private Date updateTime;
    private String logo;
    private int buyPrice;
    private String buyPriceStr;
    private String lastDirect;

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getFindTime() {
        return findTime;
    }

    public void setFindTime(String findTime) {
        this.findTime = findTime;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyPriceStr() {
        return buyPriceStr;
    }

    public void setBuyPriceStr(String buyPriceStr) {
        this.buyPriceStr = buyPriceStr;
    }

    @Override
    public String toString() {
        return "StrategyBestStock{" +
                "strategyId=" + strategyId +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", findTime='" + findTime + '\'' +
                ", profit=" + profit +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public int compareTo(StrategyBestStock o) {

        double result = profit- o.getProfit();
        if(result>0){
            return -1;
        }else {
            return 1;
        }
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getProfitStr() {
        return profitStr;
    }

    public void setProfitStr(String profitStr) {
        this.profitStr = profitStr;
    }

    public String getFindTimeStr() {
        return findTimeStr;
    }

    public void setFindTimeStr(String findTimeStr) {
        this.findTimeStr = findTimeStr;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLastDirect() {
        return lastDirect;
    }

    public void setLastDirect(String lastDirect) {
        this.lastDirect = lastDirect;
    }
}
