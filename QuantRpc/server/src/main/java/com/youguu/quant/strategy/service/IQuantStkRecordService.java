package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.QuantStkRecord;

import java.util.Date;

/**
 * Created by xyj on 2016/9/28.
 */
public interface IQuantStkRecordService {

    public int saveQuantStkRecord(QuantStkRecord qsr);

    public boolean ifExists(int rid,int sid);

    /**
     * 处理策略除权除息数据
     * @param time
     */
    public String disposeQuantStk(int strategyId, Date time);

}
