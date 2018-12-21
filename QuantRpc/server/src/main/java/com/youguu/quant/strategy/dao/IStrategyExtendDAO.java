package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyExtend;

/**
 * Created by lenovo on 2016/11/21.
 * 数据跟随Strategy直接生成
 */
public interface IStrategyExtendDAO {

    int saveStrategyExtend(StrategyExtend se);

    StrategyExtend getStrategyExtend(int strategyId);

    /**
     * 买入+1 持仓+1
     * @param strategyId
     * @return
     */
    int incBuy(int strategyId);

    /**
     * 卖出+1 持仓-1
     * @param strategyId
     * @return
     */
    int incSell(int strategyId);

    int updatePositionNum(int strategyId,int positionNum);

}
