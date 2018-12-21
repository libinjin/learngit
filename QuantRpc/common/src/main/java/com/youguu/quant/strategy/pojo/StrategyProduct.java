package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xyj on 2016/8/30.
 */
public class StrategyProduct implements Serializable {

    private int id;
    private String productId;
    private String strategyId;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StrategyProduct{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", strategyId='" + strategyId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
