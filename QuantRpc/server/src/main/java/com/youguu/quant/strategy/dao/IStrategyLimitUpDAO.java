package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyLimitUp;

import java.util.List;

/**
 * Created by xyj on 2017/3/7.
 */
public interface IStrategyLimitUpDAO {

    public int batchList(List<StrategyLimitUp> list);

    public StrategyLimitUp getMax(String start,String end);

}
