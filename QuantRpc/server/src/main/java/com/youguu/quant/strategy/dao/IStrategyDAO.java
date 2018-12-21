package com.youguu.quant.strategy.dao;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.pojo.Strategy;

import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
public interface IStrategyDAO {
    public int saveStrategy(Strategy strategy);

    public int deleteStrategy(int id);

    public int updateStrategy(Strategy strategy);

    public Strategy getStrategy(int id);

    public PageHolder<Strategy> queryStrategyByPage(Map<String, Object> map, int pageIndex, int pageSize);

    public int updateStrategyStatus(int id, int status);

    public List<Strategy> findAll();

    public Strategy findStrategyByCategoryId(String categoryId);

    public List<Integer> findAllRunStrategyId();

}
