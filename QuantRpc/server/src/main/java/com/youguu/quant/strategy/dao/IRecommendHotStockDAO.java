package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.RecommendHotStock;

import java.util.List;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/2 14:48
 */
public interface IRecommendHotStockDAO {

    int addRecommendHotStock(RecommendHotStock recommendHotStock);

    int batchAddRecommendHotStock(List<RecommendHotStock> list);

    int delRecommendHotStock(int strategyId);

    List<RecommendHotStock> getRecommendHotStockList(int strategyId,int type);

    int delRecommendHotStockByType(int type, int strategyId);
}
