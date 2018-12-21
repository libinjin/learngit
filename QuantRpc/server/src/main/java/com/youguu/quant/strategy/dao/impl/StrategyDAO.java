package com.youguu.quant.strategy.dao.impl;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyDAO;
import com.youguu.quant.strategy.pojo.Strategy;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Repository
public class StrategyDAO extends QuantDAO<Strategy> implements IStrategyDAO {
    @Override
    public int saveStrategy(Strategy strategy) {
        return insert(strategy);
    }

    @Override
    public int deleteStrategy(int id) {
        return delete(id);
    }

    @Override
    public int updateStrategy(Strategy strategy) {
        return update(strategy);
    }

    @Override
    public Strategy getStrategy(int id) {
        return get(id);
    }

    @Override
    public PageHolder<Strategy> queryStrategyByPage(Map<String, Object> map, int pageIndex, int pageSize) {
        return pagedQuery("queryStrategyByPage",map,pageIndex,pageSize);
    }

    @Override
    public int updateStrategyStatus(int id, int status) {
        Map<String,Integer> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        return updateBy("updateStrategyStatus",map);
    }

    @Override
    public List<Strategy> findAll() {
        return findBy("findAll",null);
    }

    @Override
    public Strategy findStrategyByCategoryId(String categoryId) {
        return findUniqueBy("findStrategyByCategoryId",categoryId);
    }

    @Override
    public List<Integer> findAllRunStrategyId() {
        return findBy("findAllRunStrategyId",null);
    }
}
