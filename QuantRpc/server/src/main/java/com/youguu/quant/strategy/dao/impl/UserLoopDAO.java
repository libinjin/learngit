package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.base.QuantTradePlateConfig;
import com.youguu.quant.strategy.dao.IUserLoopDAO;
import com.youguu.quant.strategy.pojo.UserLoop;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Repository
public class UserLoopDAO extends QuantDAO<UserLoop> implements IUserLoopDAO {

    @Override
    public int saveUserLoop(UserLoop userLoop) {
        return insert(userLoop);
    }

    @Override
    public int incrLoopNum(int userId,int type) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("updateTime",new Date());
        map.put("loopNum", QuantTradePlateConfig.getInstance().getTypeShareLoopNum(type));
        map.put("type",type);
        return updateBy("incrLoopNum",map);
    }

    @Override
    public int incrFinishNum(int userId,int type) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("updateTime",new Date());
        map.put("type",type);
        return updateBy("incrFinishNum",map);
    }

    @Override
    public UserLoop getUserLoop(int userId,int type) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("type",type);
        return findUniqueBy("selectbyType",map);
    }

    @Override
    public int deleteAllUserLoop() {
        return deleteBy("deleteAll",null);
    }
}
