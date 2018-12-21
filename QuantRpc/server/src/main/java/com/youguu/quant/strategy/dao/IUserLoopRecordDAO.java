package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.UserLoopRecord;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/25.
 */
public interface IUserLoopRecordDAO {
    public int saveUserLoopRecord(UserLoopRecord record);

    public List<UserLoopRecord> findUserLoopList(String time,int type);
}
