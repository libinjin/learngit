package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/25.
 */
public class UserLoopRecord implements Serializable {
    private int id;
    private int userId;
    private int strategyId;
    private String stocks;
    /**
     * 类型
     * 0 回测
     * 1 破解DNA(K线相似度)
     */
    private int type;
    private Date createTime;
    private int num;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserLoopRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", strategyId=" + strategyId +
                ", stocks='" + stocks + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
