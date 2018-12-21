package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IUserLoopRecordDAO;
import com.youguu.quant.strategy.pojo.UserLoopRecord;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Repository
public class UserLoopRecordDAO extends QuantDAO<UserLoopRecord> implements IUserLoopRecordDAO {
    @Override
    public int saveUserLoopRecord(UserLoopRecord record) {
        return insert(record);
    }

    @Override
    public List<UserLoopRecord> findUserLoopList(String time,int type) {
        Map<String,Object> map  = new HashMap<>();
        map.put("startTime",time+" 00:00:00");
        map.put("endTime",time+" 23:59:59");
        map.put("type",type);
        return findBy("findUserLoopList",map);
    }
}
