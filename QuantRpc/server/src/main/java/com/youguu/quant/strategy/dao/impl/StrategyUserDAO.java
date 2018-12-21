package com.youguu.quant.strategy.dao.impl;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyUserDAO;
import com.youguu.quant.strategy.pojo.StrategyUser;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Repository
public class StrategyUserDAO extends QuantDAO<StrategyUser> implements IStrategyUserDAO {
    @Override
    public int saveStrategyUser(StrategyUser strategy) {
        return insert(strategy);
    }

    @Override
    public int expandExpireTime(int userId, int strategyId, int days) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",userId);
        map.put("strategyId",strategyId);
        map.put("days",days);
        return updateBy("expandExpireTime",map);
    }

    @Override
    public StrategyUser getStrategyUser(int id) {
        return get(id);
    }

    @Override
    public List<StrategyUser> findStrategyByUserId(int userId, int expire) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",userId);
        map.put("expire",expire);
        return findBy("findStrategyByUserId",map);
    }

    @Override
    public PageHolder<StrategyUser> queryStrategyUserByPage(Map<String, Object> map, int pageIndex, int pageSize) {
        return pagedQuery("queryStrategyUserByPage",map,pageIndex,pageSize);
    }

    @Override
    public StrategyUser getStrategyUser(int userId, int strategyId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",userId);
        map.put("strategyId",strategyId);
        return findUniqueBy("getStrategyByUidSid",map);
    }

    @Override
    public List<StrategyUser> findByUserIdAndStrategyId(List<Integer> userIdList, int strategyId) {
        Map<String,Object> map = new HashMap<>();
        map.put("list",userIdList);
        map.put("strategyId",strategyId);
        return this.findBy("findByUserIdAndStrategyId", map);
    }

    @Override
    public int deleteStrategyUser(int id) {
        return delete(id);
    }

    @Override
    public StrategyUser getStrategyUserAll(int userId, int strategyId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",userId);
        map.put("strategyId",strategyId);
        return findUniqueBy("getStrategyByUidSidAll",map);
    }

    @Override
    public boolean isExpire(int userId, int strategyId) {
        StrategyUser strategyUser = getStrategyUserAll(userId, strategyId);
        if(strategyUser == null){
            return true;
        }
        int result = strategyUser.getExpireTime().compareTo(new Date());
        return result>=0?false:true;
    }

    @Override
    public List<StrategyUser> findStrategyBuyUserCount(String time) {
        return findBy("findStrategyBuyUserCount", time);
    }

    @Override
    public List<StrategyUser> findStrategyBuyUserList(String time) {
        return findBy("findStrategyBuyUserList", time);
    }

    @Override
    public int findStrategyBuyUserAllCount(String time) {
        List<StrategyUser> list = findBy("findStrategyBuyUserAllCount", time);
        if(list != null && list.size()>0)
        {
            return list.size();
        }
        return 0;
    }
}
