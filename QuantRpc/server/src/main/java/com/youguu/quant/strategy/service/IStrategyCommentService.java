package com.youguu.quant.strategy.service;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.pojo.StrategyComment;

import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
public interface IStrategyCommentService {

    public int saveStrategyComment(StrategyComment strategy);

    public int deleteStrategyComment(int id);

    public int updateStrategyComment(StrategyComment strategy);

    public StrategyComment getStrategyComment(int id);

    public PageHolder<StrategyComment> queryStrategyCommentByPage(Map<String, Object> map, int pageIndex, int pageSize);

    public List<StrategyComment> findStrategyCommentList(int strategyId,int pageIndex, int pageSize);

    public int updateCommentStatus(int id, int status);

}
