package com.youguu.quant.strategy.pojo;

/**
 * Created by leo on 2016/11/21.
 */
public class StockSearchResponse {
    private String stockCode;//股票代码（8位）
    private String stockName;//股票名称
    private boolean isFollow;//是否已关注，true-是；false-否
    private boolean isPosition;//是否持有，true-是；false-否

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

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean isFollow) {
        this.isFollow = isFollow;
    }

    public boolean isPosition() {
        return isPosition;
    }

    public void setPosition(boolean isPosition) {
        this.isPosition = isPosition;
    }
}
