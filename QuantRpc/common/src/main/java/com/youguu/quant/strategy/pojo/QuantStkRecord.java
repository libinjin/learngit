package com.youguu.quant.strategy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xyj on 2016/9/28.
 */
public class QuantStkRecord implements Serializable {

    private int id;
    private int rid;
    private int sid;
    private String stockCode;
    private String stockName;
    private Date endDate;
    private Date declareDate;
    private String divType;
    private String divInfo;
    private float cashBt;
    private float cashAt;
    private float bonusShr;
    private float capShr;
    private float placingPrice;
    private float placingRatio;
    private Date regDate;
    private Date exdivDate;
    private Date bsTraDate;
    private Date csTraDate;
    private int status;
    private Date importTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDeclareDate() {
        return declareDate;
    }

    public void setDeclareDate(Date declareDate) {
        this.declareDate = declareDate;
    }

    public String getDivType() {
        return divType;
    }

    public void setDivType(String divType) {
        this.divType = divType;
    }

    public String getDivInfo() {
        return divInfo;
    }

    public void setDivInfo(String divInfo) {
        this.divInfo = divInfo;
    }

    public float getCashBt() {
        return cashBt;
    }

    public void setCashBt(float cashBt) {
        this.cashBt = cashBt;
    }

    public float getCashAt() {
        return cashAt;
    }

    public void setCashAt(float cashAt) {
        this.cashAt = cashAt;
    }

    public float getBonusShr() {
        return bonusShr;
    }

    public void setBonusShr(float bonusShr) {
        this.bonusShr = bonusShr;
    }

    public float getCapShr() {
        return capShr;
    }

    public void setCapShr(float capShr) {
        this.capShr = capShr;
    }

    public float getPlacingPrice() {
        return placingPrice;
    }

    public void setPlacingPrice(float placingPrice) {
        this.placingPrice = placingPrice;
    }

    public float getPlacingRatio() {
        return placingRatio;
    }

    public void setPlacingRatio(float placingRatio) {
        this.placingRatio = placingRatio;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getExdivDate() {
        return exdivDate;
    }

    public void setExdivDate(Date exdivDate) {
        this.exdivDate = exdivDate;
    }

    public Date getBsTraDate() {
        return bsTraDate;
    }

    public void setBsTraDate(Date bsTraDate) {
        this.bsTraDate = bsTraDate;
    }

    public Date getCsTraDate() {
        return csTraDate;
    }

    public void setCsTraDate(Date csTraDate) {
        this.csTraDate = csTraDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "QuantStkRecord{" +
                "id=" + id +
                ", rid=" + rid +
                ", sid=" + sid +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", endDate=" + endDate +
                ", declareDate=" + declareDate +
                ", divType='" + divType + '\'' +
                ", divInfo='" + divInfo + '\'' +
                ", cashBt=" + cashBt +
                ", cashAt=" + cashAt +
                ", bonusShr=" + bonusShr +
                ", capShr=" + capShr +
                ", placingPrice=" + placingPrice +
                ", placingRatio=" + placingRatio +
                ", regDate=" + regDate +
                ", exdivDate=" + exdivDate +
                ", bsTraDate=" + bsTraDate +
                ", csTraDate=" + csTraDate +
                ", status=" + status +
                ", importTime=" + importTime +
                '}';
    }
}
