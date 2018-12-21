package com.youguu.quant.strategy.service;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.pojo.StrategyUser;

import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
public interface IStrategyUserService {

    public int saveStrategyUser(StrategyUser strategy);

    /**
     * 延长策略使用时间
     * @param userId 用户ID
     * @param strategyId 策略ID
     * @param days 延长天数
     * @return
     */
    public int expandExpireTime(int userId, int strategyId, int days,String payType,double price);

    public StrategyUser getStrategyUser(int id);

    /**
     * 查询用户名下的策略列表
     * @param userId 用户ID
     * @param expire 1-未过期策略；0-全部策略
     * @return
     */
    public List<StrategyUser> findStrategyByUserId(int userId, int expire);

    /**
     * 查询用户名下的策略列表
     * @param userId 用户ID
     * @param expire 1-未过期策略；0-全部策略
     * @return
     */
    public Map<Integer, StrategyUser> findStrategyMapByUserId(int userId, int expire);

    public PageHolder<StrategyUser> queryStrategyUserByPage(Map<String, Object> map, int pageIndex, int pageSize);

    public StrategyUser getStrategyUser(int userId,int strategyId);

    /**
     * 是否有回测的资格
     * @param userId
     * @param strategyId
     * @return
     */
    public boolean isHavePermission(int userId,int strategyId);

    /**
     * 是否有使用破解DNA的权限
     * @param userId
     * @return
     */
    boolean haveDNAPermission(int userId);

    /**
     * 查询某策略下未过期的用户
     * @param userIdList
     * @param strategyId
     * @return
     */
    public List<StrategyUser> findByUserIdAndStrategyId(List<Integer> userIdList, int strategyId);

    /**
     * 购买的策略是否过期
     * @param userId
     * @param strategyId
     * @return
     */
    public boolean isExpire(int userId, int strategyId);

    /**
     *  查看当前付费用户统计
     * @param time
     * @return
     */
    public List<StrategyUser> findStrategyBuyUserCount(String time);

    /**
     * 查看指定时间还没有过期的用户List
     * @param time
     * @return
     */
    public List<StrategyUser> findStrategyBuyUserList(String time);

    /**
     *  查看用户购买统计用户总数
     * @param time
     * @return
     */
    public int findStrategyBuyUserAllCount(String time);

}
