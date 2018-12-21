package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SomeBody on 2016/8/24.
 */
public class Strategy implements Serializable{

    /**
     * 回测
     */
    public final static int HC_TYPE = 1;

    /**
     * 实时
     */
    public final static int SS_TYPE = 2;

    /**
     * 有效
     */
    public final static int VALID_DISPLAY_STATUS=1;

    /**
     * 无效
     */
    public final static int INVALID_DISPLAY_STATUS=0;

    /**
     * 未回测
     */
    public final static int WHC_RUN_STATUS=1;

    /**
     * 回测中
     */
    public final static int HCZ_RUN_STATUS=2;

    /**
     * 回测完成
     */
    public final static int HCWC_RUN_STATUS=3;

    /**
     * 回测失败
     */
    public final static int HCSB_RUN_STATUS=4;

    /**
     * 实时信号运行中
     */
    public final static int YXZ_RUN_STATUS=5;

    /**
     * 实时信号停止
     */
    public final static int XHTZ_RUN_STATUS=6;

    /**
     * 实时信号异常
     */
    public final static int XHYC_RUN_STATUS=7;

    /**
     * 正在清算
     */
    public final static int CLEAR_RUNNING=8;

    /**
     * 清算完成
     */
    public final static int CLEAR_FINISHING=9;

    /**
     * 增加或删除策略
     */
    public final static int MQ_FLAG_1=1;

    /**
     * 回测策略
     */
    public final static int MQ_FLAG_2=2;

    /**
     * 实时策略
     */
    public final static int MQ_FLAG_3=3;

    /**
     * 回补数据
     */
    public final static int MQ_FLAG_4=4;

    public final static String FIRST_BUY = "HI，主人！快来告诉我你关注的股票吧~！";
    public final static String NO_SIGNAL = "HI，主人！当前没有模拟交易信号~";
    //主人！我在{时间} {买卖} {股票名称} {价格}
    public final static String YES_SIGNAL = "主人，我在%s 以%s元 %s %s";

    private int id;
    private String name;
    private String logo;
    private String description;
    private int type;
    private int rank;
    private String ratingLabel;
    private String categoryId;
    private int displayStatus;
    private Date updateTime;
    private Date createTime;
    private String remark;
    private String className;
    private String runStatus;
    /**
     * 沪深300盈利率
     */
    private String hsProfit;

    /**
     * 是否购买
     */
    private boolean isBuy;

    /**
     * 上线时间
     */
    private Date upLineTime;
    /**
     * 图片标签地址
     */
    private String imgLabel;
    /**
     * 是否跳转到新主页
     */
    private int forwardNewHome;
    /**
     * 图片base64
     */
    private String banner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRatingLabel() {
        return ratingLabel;
    }

    public void setRatingLabel(String ratingLabel) {
        this.ratingLabel = ratingLabel;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(int displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getHsProfit() {
        return hsProfit;
    }

    public void setHsProfit(String hsProfit) {
        this.hsProfit = hsProfit;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Date getUpLineTime() {
        return upLineTime;
    }

    public void setUpLineTime(Date upLineTime) {
        this.upLineTime = upLineTime;
    }

    public String getImgLabel() {
        return imgLabel;
    }

    public void setImgLabel(String imgLabel) {
        this.imgLabel = imgLabel;
    }

    public int getForwardNewHome() {
        return forwardNewHome;
    }

    public void setForwardNewHome(int forwardNewHome) {
        this.forwardNewHome = forwardNewHome;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", rank=" + rank +
                ", ratingLabel='" + ratingLabel + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", displayStatus=" + displayStatus +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", className='" + className + '\'' +
                ", runStatus='" + runStatus + '\'' +
                ", hsProfit='" + hsProfit + '\'' +
                ", isBuy=" + isBuy +
                ", upLineTime=" + upLineTime +
                ", forwardNewHome=" + forwardNewHome +
                ", banner=" + banner +
                '}';
    }
}
