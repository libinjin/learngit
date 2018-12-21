package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IRecommendHotStockDAO;
import com.youguu.quant.strategy.pojo.RecommendHotStock;
import com.youguu.quant.strategy.service.IRecommendHotStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/2 15:03
 */
@Service("recommendHotStockService")
public class RecommendHotStockService implements IRecommendHotStockService {

    @Resource
    private IRecommendHotStockDAO recommendHotStockDAO;
    @Override
    public int addRecommendHotStock(RecommendHotStock recommendHotStock) {
        return recommendHotStockDAO.addRecommendHotStock(recommendHotStock);
    }

    @Override
    public int batchAddRecommendHotStock(List<RecommendHotStock> list) {
        return recommendHotStockDAO.batchAddRecommendHotStock(list);
    }

    @Override
    public int delRecommendHotStock(int strategyId) {
        return recommendHotStockDAO.delRecommendHotStock(strategyId);
    }

    @Override
    public List<RecommendHotStock> getRecommendHotStockList(int strategyId, int type) {
        return recommendHotStockDAO.getRecommendHotStockList(strategyId, type);
    }

    @Override
    public int delRecommendHotStockByType(int type, int strategyId) {
        return recommendHotStockDAO.delRecommendHotStockByType(type, strategyId);
    }
}
