package com.youguu.quant.rpc.client.klinesim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youguu.quant.klinesim.CommonKlinePoint;
import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.klinesim.StockKline;
import com.youguu.quant.rpc.client.QuantTradeRpcClientFactory;

public class KlineSimRpcServiceImplTest {

    static IKLineSimRpcService klineSimRpcService = QuantTradeRpcClientFactory.getKlineSimRpcService();
    
    @Test
    public void testFindKlineByIdTest() throws Exception {
        KlineSimResult klinesim = klineSimRpcService.getKlineSimById("21000732",1111);
        System.out.println(klinesim.toString());
        JSONObject res = new JSONObject();
        res.put("status", "0000");
        res.put("message", "OK");
		if(klinesim != null){
			JSONObject resultjson = new JSONObject(); 
			resultjson.put("chg", klinesim.getAvgRation());
			BigDecimal b = new BigDecimal(klinesim.getUpRate()*100);
            String f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
			resultjson.put("upProbability", f1+'%');
			b = new BigDecimal(klinesim.getAvgUpRation()*100);
            f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
			resultjson.put("upAvgChg", f1+'%');
			b = new BigDecimal(klinesim.getDownRate()*100);
            f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
			resultjson.put("downProbability", f1+'%');
			b = new BigDecimal(klinesim.getAvgDownRation()*100);
            f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
			resultjson.put("downAvgChg", f1+'%');
			res.put("result", resultjson);
			List<StockKline> list = klinesim.getStocksklist();
			JSONArray stockArray = new JSONArray();
			for(int i=0;i<list.size();i++){
				StockKline stockkline = list.get(i);
				JSONObject stock = new JSONObject();
				stock.put("stockCode", stockkline.getCode());
				stock.put("stockName", stockkline.getName());
				List<CommonKlinePoint> klinelist = stockkline.getList();
				long startDate = klinelist.get(0).getKeyDate();
				stock.put("minDate", startDate+"");
				CommonKlinePoint startk = klinelist.get(39);
				long endDate = startk.getKeyDate();
				stock.put("maxDate", endDate+"");
				if(i == 0){
					stock.put("profit", 0.0);
				}else{
					CommonKlinePoint endk = klinelist.get(klinelist.size()-1);
					double profit = (endk.getCur()-startk.getCur())/startk.getCur();
					stock.put("profit", profit);
				}
				JSONArray klinearray = new JSONArray();
				for(CommonKlinePoint kpoint : klinelist){
					JSONObject kline = new JSONObject();
					kline.put("tradeDay", kpoint.getKeyDate());
					kline.put("volume", kpoint.getVolume());
					kline.put("openPrice", kpoint.getOpen());
					kline.put("closePrice", kpoint.getCur());
					kline.put("highPrice", kpoint.getHigh());
					kline.put("lowPrice", kpoint.getLow());
					klinearray.add(kline);
				}
				stock.put("kLineList", klinearray);
				stockArray.add(stock);
			}
			res.put("stockList", stockArray);
			
		}
		System.out.println(res.toString());
    }
    
    @Test
    public void testSaveKlintSim() {
    	KlineSimResult klinesim = new KlineSimResult();
    	klinesim.setName("平安银行");
    	klinesim.setAvgDownRation(1.2);
    	klinesim.setAvgRation(1.3);
    	klinesim.setAvgUpRation(4.3);
    	klinesim.setDownRate(3.4);
    	klinesim.setId("000001");
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
    	stock1.setCode("600000");
    	stock1.setName("浦发银行");
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
    	String key = klineSimRpcService.saveKlineSimData(klinesim);
    	System.out.println(key);
    }


   
}