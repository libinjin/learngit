package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/24.
 */
public class StrategyComment implements Serializable {

    /**
     * 待审
     */
    public final static int DS_STATUS = 1;

    /**
     * 已发布
     */
    public final static int YFB_STATUS = 2;

    private int id;
    private int strategyId;
    private int userId;
    private String cotent;
    private Date createTime;
    private int status;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StrategyComment{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", userId=" + userId +
                ", cotent='" + cotent + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
