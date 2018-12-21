package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.DNAAcl;
import com.youguu.quant.strategy.pojo.UserLoop;
import com.youguu.quant.strategy.pojo.UserLoopRecord;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/25.
 */
public interface IUserLoopService {

    public int saveUserLoop(int userId,int type);

    /**
     * 增加使用次数
     * @param userId
     * @return
     */
    public int incrLoopNum(int userId,int type);

    /**
     * 完成使用回测记录
     * @param userId
     * @param strategyId
     * @param stocks
     * @return
     */
    public int incrBackFinishNum(int userId,int strategyId,String stocks);

    /**
     * 完成使用破解DNA记录
     * @param userId
     * @param stocks
     * @return
     */
    int incDNAFinishNum(int userId,String stocks);

    /**
     * 获取用户试用次数
     * @param userId
     * @param type
     * @return
     */
    public UserLoop getUserLoop(int userId,int type);

    public int deleteAllUserLoop();

    public List<UserLoopRecord> findUserLoopList(String time);

    /**
     * 查询用户DNA的次数
     * @param userId
     * @return
     * 0000 有权限
     * 0001 没有权限
     */
    DNAAcl queryDNANum(int userId);
}
