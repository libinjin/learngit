package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.RecommendHotStock;

import java.util.List;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/2 15:00
 */
public interface IRecommendHotStockService {

    /**
     * 添加推荐/热门股票
     * @param recommendHotStock
     * @return
     */
    int addRecommendHotStock(RecommendHotStock recommendHotStock);

    /**
     * 批量添加推荐/热门股票
     * @param list
     * @return
     */
    int batchAddRecommendHotStock(List<RecommendHotStock> list);

    /**
     * 根据策略ID删除推荐/热门股票
     * @param strategyId
     * @return
     */
    int delRecommendHotStock(int strategyId);

    /**
     * 获取推荐/热门股票
     * @param strategyId
     * @param type
     * @return
     */
    List<RecommendHotStock> getRecommendHotStockList(int strategyId,int type);

    int delRecommendHotStockByType(int type, int strategyId);
}
