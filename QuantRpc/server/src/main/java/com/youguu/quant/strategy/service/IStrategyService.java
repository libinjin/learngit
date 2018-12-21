package com.youguu.quant.strategy.service;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.pojo.Strategy;

import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
public interface IStrategyService {
    public int saveStrategy(Strategy strategy);

    public int deleteStrategy(int id);

    public int updateStrategy(Strategy strategy);

    public Strategy getStrategy(int id);

    public PageHolder<Strategy> queryStrategyByPage(Map<String, Object> map, int pageIndex, int pageSize);

    public int updateStrategyStatus(int id, int status);

    /**
     * 查询已上架并且客户端显示的策略
     * @return
     */
    public List<Strategy> findAll();

    public Strategy findStrategyByCategoryId(String categoryId);

    public List<Integer> findAllRunStrategyId();

    /**
     * 判断该策略是否有数据文件
     * @param strategyId
     * @return
     */
    public boolean isExitsFile(int strategyId);

    /**
     * 清理该策略的数据文件，清理完成后才能重新“回测”
     * @param strategyId
     * @return
     */
    public boolean clearStrategyData(int strategyId);
}
