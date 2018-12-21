package com.youguu.quant.strategy.dao;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.pojo.StrategyUser;

import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
public interface IStrategyUserDAO {
    public int saveStrategyUser(StrategyUser strategy);

    /**
     * 延长策略使用时间
     * @param userId 用户ID
     * @param strategyId 策略ID
     * @param days 延长天数
     * @return
     */
    public int expandExpireTime(int userId, int strategyId, int days);

    public StrategyUser getStrategyUser(int id);

    /**
     * 查询用户名下的策略列表
     * @param userId 用户ID
     * @param expire 1-未过期策略；0-全部策略
     * @return
     */
    public List<StrategyUser> findStrategyByUserId(int userId, int expire);

    public PageHolder<StrategyUser> queryStrategyUserByPage(Map<String, Object> map, int pageIndex, int pageSize);

    public StrategyUser getStrategyUser(int userId,int strategyId);

    public List<StrategyUser> findByUserIdAndStrategyId(List<Integer> userIdList, int strategyId);

    public int deleteStrategyUser(int id);

    public StrategyUser getStrategyUserAll(int userId,int strategyId);

    /**
     * 购买的策略是否过期
     * @param userId
     * @param strategyId
     * @return
     */
    public boolean isExpire(int userId, int strategyId);

    /**
     *  查看用户购买统计
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
