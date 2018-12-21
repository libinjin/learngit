package com.youguu.quant.klinesimilarity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.klinesim.CommonKlinePoint;
import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.klinesim.StockKline;
import com.youguu.quant.klinesim.service.KlineSimService;


public class KlineSimilarityTest extends BaseTestClass {
	
	KlineSimService klineSimService = (KlineSimService) getBean("klineSimService");
    
    @Test
    public void saveKlinesSimTest(){
    	KlineSimResult klinesim = new KlineSimResult();
    	klinesim.setName("浦发银行");
    	klinesim.setAvgDownRation(1.2);
    	klinesim.setAvgRation(1.3);
    	klinesim.setAvgUpRation(4.3);
    	klinesim.setDownRate(3.4);
    	klinesim.setId("600000");
    	klinesim.setUpRate(4.3);
    	List<StockKline> stocksklist = new ArrayList<>();
    	
    	StockKline stock2 = new StockKline();
    	stock2.setCode("000546");
    	stock2.setName("金圆股份");
    	List<CommonKlinePoint> klist2 = new ArrayList<>();
    	CommonKlinePoint com21 = new CommonKlinePoint();
    	com21.setCur(9.5);
    	com21.setOpen(3.4);
    	com21.setDate(20130405);
    	com21.setHigh(10.3);
    	com21.setLow(3.4);
    	com21.setVolume(3453);
    	klist2.add(com21);
    	CommonKlinePoint com22 = new CommonKlinePoint();
    	com22.setCur(9.5);
    	com22.setOpen(3.4);
    	com22.setDate(20130405);
    	com22.setHigh(10.3);
    	com22.setLow(3.4);
    	com22.setVolume(3453);
    	klist2.add(com22);
    	stock2.setList(klist2);
    	stocksklist.add(stock2);
    	
    	StockKline stock1 = new StockKline();
    	stock1.setCode("000001");
    	stock1.setName("平安银行");
    	List<CommonKlinePoint> klist1 = new ArrayList<>();
    	CommonKlinePoint com1 = new CommonKlinePoint();
    	com1.setCur(9.5);
    	com1.setOpen(3.4);
    	com1.setDate(20130405);
    	com1.setHigh(10.3);
    	com1.setLow(3.4);
    	com1.setVolume(3453);
    	klist1.add(com1);
    	CommonKlinePoint com2 = new CommonKlinePoint();
    	com2.setCur(9.5);
    	com2.setOpen(3.4);
    	com2.setDate(20130405);
    	com2.setHigh(10.3);
    	com2.setLow(3.4);
    	com2.setVolume(3453);
    	klist1.add(com2);
    	stock1.setList(klist1);
    	stocksklist.add(stock1);
    	klinesim.setStocksklist(stocksklist);
    	String key = klineSimService.saveKlineSim(klinesim);
    	System.out.println(key);
    }
    
    @Test
    public void getKlineByIdTest(){
    	KlineSimResult ss = klineSimService.getKlineSimById("600000");
    	System.out.println(ss.toString());
    }
	
}
