package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/24.
 */
public class StrategyUser implements Serializable {

    /**
     * 未过期策略
     */
    public final static int FIND_WGQ_FLAG = 1;

    /**
     * 全部策略
     */
    public final static int FIND_ALL_FLAG = 0;

    private int id;
    private int userId;
    private int strategyId;
    private Date buyTime;
    private Date expireTime;
    private Date updateTime;
    private Date createTime;
    /**
     *  统计用
     */
    private int num;

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


    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "StrategyUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", strategyId=" + strategyId +
                ", buyTime=" + buyTime +
                ", expireTime=" + expireTime +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
