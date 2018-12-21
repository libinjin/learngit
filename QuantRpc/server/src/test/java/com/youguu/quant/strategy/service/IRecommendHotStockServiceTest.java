package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.RecommendHotStock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangKai
 * @className
 * @description
 * @date 2016/9/2 15:20
 */
public class IRecommendHotStockServiceTest extends BaseTestClass {

    IRecommendHotStockService service = (IRecommendHotStockService)getBean("recommendHotStockService");

    @Test
    public void addRecommendHotStock(){
        RecommendHotStock recommendHotStock=new RecommendHotStock();
        recommendHotStock.setStrategyId(1111);
        recommendHotStock.setCreateTime(new Date());
        recommendHotStock.setStockCode("600000");
        recommendHotStock.setType(1);
        System.out.println(service.addRecommendHotStock(recommendHotStock));
    }

    @Test
    public void batchAddRecommendHotStock(){
        List<RecommendHotStock> list=new ArrayList<>();
        for (int i=0;i<6;i++){
            RecommendHotStock recommendHotStock=new RecommendHotStock();
            recommendHotStock.setStrategyId(1111);
            recommendHotStock.setCreateTime(new Date());
            recommendHotStock.setStockCode("60000"+i);
            recommendHotStock.setType(1);
            list.add(recommendHotStock);
        }
        System.out.println(service.batchAddRecommendHotStock(list));
    }

    @Test
    public void delRecommendHotStock(){
        System.out.println(service.delRecommendHotStock(1111));
    }

    @Test
    public void getRecommendHotStockList(){
        System.out.println(service.getRecommendHotStockList(1111,1));
    }
}
