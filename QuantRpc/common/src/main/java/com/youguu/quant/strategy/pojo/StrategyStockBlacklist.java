package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xyj on 2016/8/30.
 */
public class StrategyStockBlacklist implements Serializable {

    private int id;
    private int strategyId;
    private String strategyName;
    private String stockCodes;
    private int version;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(String stockCodes) {
        this.stockCodes = stockCodes;
    }

    @Override
    public String toString() {
        return "StrategyStockBlacklist{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", strategyName='" + strategyName + '\'' +
                ", stockCodes='" + stockCodes + '\'' +
                ", version=" + version +
                ", updateTime=" + updateTime +
                '}';
    }
}
