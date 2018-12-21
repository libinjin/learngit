package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IRecommendHotStockDAO;
import com.youguu.quant.strategy.pojo.RecommendHotStock;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/2 14:50
 */
@Repository
public class RecommendHotStockDAO extends QuantDAO<RecommendHotStock> implements IRecommendHotStockDAO {
    @Override
    public int addRecommendHotStock(RecommendHotStock recommendHotStock) {
        return insert(recommendHotStock);
    }

    @Override
    public int batchAddRecommendHotStock(List<RecommendHotStock> list) {
        return batchInsert(list,"batchInsert");
    }

    @Override
    public int delRecommendHotStock(int strategyId) {
        return deleteBy("delete",strategyId);
    }

    @Override
    public List<RecommendHotStock> getRecommendHotStockList(int strategyId, int type) {
        Map<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("type",type);
        paramsMap.put("strategyId",strategyId);
        List<RecommendHotStock> list = findBy("getRecommendHotStockList",paramsMap);


        //如果查询推荐股票，随机取6条返回
        if(type==1){
            Collections.shuffle(list);
            if(list!=null && list.size()>6){
                return list.subList(0,6);
            }
            return list;
        }
        return list;
    }

    @Override
    public int delRecommendHotStockByType(int type, int strategyId) {
        Map<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("type",type);
        paramsMap.put("strategyId",strategyId);
        return this.deleteBy("delRecommendHotStockByType", paramsMap);
    }
}
