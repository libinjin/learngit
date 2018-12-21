package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.QuantStkRecord;

/**
 * Created by xyj on 2016/9/28.
 */
public interface IQuantStkRecordDAO {

    public int saveQuantStkRecord(QuantStkRecord qsr);

    public int ifExists(int rid,int sid);

}
