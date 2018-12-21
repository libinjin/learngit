package com.youguu.quant.strategy.dao.impl;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyCommentDAO;
import com.youguu.quant.strategy.pojo.StrategyComment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Repository
public class StrategyCommentDAO extends QuantDAO<StrategyComment> implements IStrategyCommentDAO {
    @Override
    public int saveStrategyComment(StrategyComment strategyComment) {
        return insert(strategyComment);
    }

    @Override
    public int deleteStrategyComment(int id) {
        return delete(id);
    }

    @Override
    public int updateStrategyComment(StrategyComment strategyComment) {
        return update(strategyComment);
    }

    @Override
    public StrategyComment getStrategyComment(int id) {
        return get(id);
    }

    @Override
    public PageHolder<StrategyComment> queryStrategyCommentByPage(Map<String, Object> map, int pageIndex, int pageSize) {
        return pagedQuery("queryStrategyCommentByPage",map,pageIndex,pageSize);
    }

    @Override
    public List<StrategyComment> findStrategyCommentList(int strategyId, int pageIndex, int pageSize) {
        pageIndex = (pageIndex - 1) * pageSize;
        Map<String, Integer> map = new HashMap<>();
        map.put("strategyId",strategyId);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        return findBy("findStrategyCommentList",map);
    }

    @Override
    public int updateCommentStatus(int id, int status) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        return updateBy("updateCommentStatus",map);
    }
}
