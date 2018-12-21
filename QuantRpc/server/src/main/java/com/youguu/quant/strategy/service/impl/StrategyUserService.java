package com.youguu.quant.strategy.service.impl;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.dao.IStrategyUserDAO;
import com.youguu.quant.strategy.dao.IStrategyUserRecordDAO;
import com.youguu.quant.strategy.pojo.StrategyUser;
import com.youguu.quant.strategy.pojo.StrategyUserRecord;
import com.youguu.quant.strategy.pojo.UserLoop;
import com.youguu.quant.strategy.service.IStrategyUserService;
import com.youguu.quant.strategy.service.IUserLoopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Service("strategyUserService")
public class StrategyUserService implements IStrategyUserService {
    @Resource
    private IStrategyUserDAO strategyUserDAO;
    @Resource
    private IStrategyUserRecordDAO strategyUserRecordDAO;
    @Resource
    private IUserLoopService userLoopService;


    @Transactional("quantTradeTx")
    @Override
    public int saveStrategyUser(StrategyUser strategy) {
        strategyUserDAO.saveStrategyUser(strategy);
        StrategyUserRecord record = new StrategyUserRecord();
        record.setUserId(strategy.getUserId());
        record.setStrategyId(strategy.getStrategyId());
        record.setCreateTime(new Date());
        strategyUserRecordDAO.saveStrategyUserRecord(record);
        return 1;
    }

    @Transactional("quantTradeTx")
    @Override
    public int expandExpireTime(int userId, int strategyId, int days,String payType,double price) {
        StrategyUser su = strategyUserDAO.getStrategyUserAll(userId, strategyId);
        if (su == null) {
            su = new StrategyUser();
            su.setBuyTime(new Date());
            su.setCreateTime(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            su.setExpireTime(calendar.getTime());
            su.setStrategyId(strategyId);
            su.setUpdateTime(new Date());
            su.setUserId(userId);
            strategyUserDAO.saveStrategyUser(su);
        }
        else
        {
            if(su.getExpireTime().getTime()<=new Date().getTime())
            {
                strategyUserDAO.deleteStrategyUser(su.getId());
                su = new StrategyUser();
                su.setBuyTime(new Date());
                su.setCreateTime(new Date());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                su.setExpireTime(calendar.getTime());
                su.setStrategyId(strategyId);
                su.setUpdateTime(new Date());
                su.setUserId(userId);
                strategyUserDAO.saveStrategyUser(su);
            }
        }

        strategyUserDAO.expandExpireTime(userId, strategyId, days);
        StrategyUserRecord record = new StrategyUserRecord();
        record.setUserId(userId);
        record.setStrategyId(strategyId);
        record.setCreateTime(new Date());
        record.setPayType(payType);
        record.setPrice(price);
        record.setDay(days);
        strategyUserRecordDAO.saveStrategyUserRecord(record);
        return 1;
    }

    @Override
    public StrategyUser getStrategyUser(int id) {
        return strategyUserDAO.getStrategyUser(id);
    }

    @Override
    public List<StrategyUser> findStrategyByUserId(int userId, int expire) {
        return strategyUserDAO.findStrategyByUserId(userId, expire);
    }

    @Override
    public Map<Integer, StrategyUser> findStrategyMapByUserId(int userId, int expire) {
        List<StrategyUser> list = findStrategyByUserId(userId, expire);
        Map<Integer, StrategyUser> map = new HashMap<>();
        if(list!=null && list.size()>0){
            for(StrategyUser strategyUser : list){
                map.put(strategyUser.getStrategyId(), strategyUser);
            }
        }
        return map;
    }

    @Override
    public PageHolder<StrategyUser> queryStrategyUserByPage(Map<String, Object> map, int pageIndex, int pageSize) {
        return strategyUserDAO.queryStrategyUserByPage(map, pageIndex, pageSize);
    }

    @Override
    public StrategyUser getStrategyUser(int userId, int strategyId) {
        return strategyUserDAO.getStrategyUser(userId, strategyId);
    }

    @Override
    public boolean isHavePermission(int userId, int strategyId) {
        StrategyUser su = getStrategyUser(userId, strategyId);
        if (su != null) {
            return true;
        }
        UserLoop ul = userLoopService.getUserLoop(userId,UserLoop.TYPE_REPORT);
        if (ul != null && ul.getLoopNum() > ul.getFinishNum()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean haveDNAPermission(int userId) {
        List<StrategyUser> list = strategyUserDAO.findStrategyByUserId(userId, 1);
        if(list!=null && list.size()>0){
            return true;
        }
        UserLoop ul = userLoopService.getUserLoop(userId,UserLoop.TYPE_DNA);
        if (ul != null && ul.getLoopNum() > ul.getFinishNum()) {
            return true;
        }
        return false;
    }

    @Override
    public List<StrategyUser> findByUserIdAndStrategyId(List<Integer> userIdList, int strategyId) {
        return strategyUserDAO.findByUserIdAndStrategyId(userIdList, strategyId);
    }

    @Override
    public boolean isExpire(int userId, int strategyId) {
        return strategyUserDAO.isExpire(userId, strategyId);
    }

    @Override
    public List<StrategyUser> findStrategyBuyUserCount(String time) {
        return strategyUserDAO.findStrategyBuyUserCount(time);
    }

    @Override
    public List<StrategyUser> findStrategyBuyUserList(String time) {
        return strategyUserDAO.findStrategyBuyUserList(time);
    }

    @Override
    public int findStrategyBuyUserAllCount(String time) {
        return strategyUserDAO.findStrategyBuyUserAllCount(time);
    }
}
