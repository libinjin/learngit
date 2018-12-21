package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xyj on 2016/8/30.
 */
public class StrategyRunLog implements Serializable {

    /**
     * 未加载
     */
    public final static int WJZ_RUN_STATUS=1;

    /**
     * 已加载
     */
    public final static int YJZ_RUN_STATUS=2;

    /**
     * 正在运行
     */
    public final static int ZZYX_RUN_STATUS=3;

    /**
     * 运行完成
     */
    public final static int YXWC_RUN_STATUS=4;

    /**
     * 运行失败
     */
    public final static int YXSB_RUN_STATUS=5;

    private int id;
    private int strategyId;
    private int status;
    private Date createTime;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StrategyRunLog{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
