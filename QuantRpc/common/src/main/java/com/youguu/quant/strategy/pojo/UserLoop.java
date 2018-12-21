package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/25.
 */
public class UserLoop implements Serializable {
    public static final int TYPE_REPORT = 0;
    public static final int TYPE_DNA = 1;
    private int id;
    private int userId;
    private Date loopDate;
    private int loopNum;
    private int finishNum;
    private int shareNum;//已分享次数
    /**
     * 类型
     * 0 回测
     * 1 破解DNA(K线相似度)
     */
    private int type;
    private Date updateTime;
    private Date createTime;

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

    public Date getLoopDate() {
        return loopDate;
    }

    public void setLoopDate(Date loopDate) {
        this.loopDate = loopDate;
    }

    public int getLoopNum() {
        return loopNum;
    }

    public void setLoopNum(int loopNum) {
        this.loopNum = loopNum;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
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

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    @Override
    public String toString() {
        return "UserLoop{" +
                "id=" + id +
                ", userId=" + userId +
                ", loopDate=" + loopDate +
                ", loopNum=" + loopNum +
                ", finishNum=" + finishNum +
                ", shareNum=" + shareNum +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
