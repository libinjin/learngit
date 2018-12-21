package com.youguu.quant.signal.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.util.JSON;
import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.rpc.server.signal.SignalServiceThriftRpcServiceImpl;
import com.youguu.quant.signal.pojo.*;
import com.youguu.quant.signal.service.ISignalService;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignalServiceServiceTest extends BaseTestClass {

    ISignalService service = (ISignalService) getBean("signalService");

    SignalServiceThriftRpcServiceImpl thriftRpcService = (SignalServiceThriftRpcServiceImpl) getBean("signalServiceThriftRpcService");

    @Test
    public void test() throws Exception {
        Map<String, List<TradeSignal>> stringListMap = service.loadTradeSignal(1, "11600000", "11600004", "11600005", "11600006", "11600007");
//        Map<String, List<TradeSignal>> stringListMap1 = service.loadTradeSignal(1, "11600000", "11600004", "11600005", "11600006", "11600007");
//        Map<String, List<TradeSignal>> stringListMap2 = service.loadTradeSignal(1, "11600000", "11600004", "11600005", "11600006", "11600007");
//        Map<String, List<TradeSignal>> stringListMap3 = service.loadTradeSignal(1, "11600000", "11600004", "11600005", "11600006", "11600007");
        System.out.println(stringListMap);
    }

    @Test
    public void queryPageStrategyTradeRecord() throws Exception {
        String[] stocks = new String[]{"21300434"};
        List<StrategyTradeRecord> list = service.queryPageStrategyTradeRecord(2236, 3298, 2, 1, 20, stocks);
        System.out.println(list);
    }

    @Test
    public void queryStockRealtimeSignalList() throws Exception {
        Map<String, StockRealtimeSignal> map = service.queryStockRealtimeSignalList(1111, 224);

        String json = JSONArray.toJSONString(map);
        System.out.println(json);

        Map<String, StockRealtimeSignal> map1 = (Map<String, StockRealtimeSignal>) JSON.parse(json);
        System.out.println(map1);

    }

    @Test
    public void queryAverageProfitCurve() {
        String[] stocks = new String[]{"21300522", "11600010", "11600018", "11600055", "11600060"};
        //String[] stocks=new String[]{"10000300"};
        List<ProfitCurve> list = service.queryAverageProfitCurve(1111, 200, stocks);
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void testQuerySumProfitNew(){
        List<SumProfit> list = service.querySumProfitNew(2235,20151027, 20161027, 0, new String[]{"21000410","21000599","21300391","21002413"});
        for(SumProfit profit : list){
            System.out.println(profit.getStockCode()+"\t"+profit.getProfitRate());
        }

    }

    @Test
    public void getNetData() {
        long start = System.currentTimeMillis();
        StockNetData stockNetData = service.getNetData(2222, "21002807", 20130927, 20150715, 20160928);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(stockNetData);
    }

    @Test
    public void dataForIndex() {

        long start = System.currentTimeMillis();
        SignalIndex signalIndex = null;
        try {
            signalIndex = service.dataForIndex(2235, 3298, "21002145", 20131028, 20151028, 20161028);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println(signalIndex);
        List<StrategyTradeRecord> list = signalIndex.getStrategyTradeRecords();
        for (StrategyTradeRecord strategyTradeRecord : list) {
            System.out.println(strategyTradeRecord);
        }

    }

    @Test
    public void loadTradeSignal() {
        Map<String, List<TradeSignal>> map = service.loadTradeSignal(2235, 2, new String[]{"11600693"});
        List<TradeSignal> list = map.get("11600693");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    @Test
    public void testInitHS300Curve(){
        List<ProfitCurve> list = service.initHS300Curve(20151027, 20161027);
        System.out.println(list.get(list.size()-1).getHsProfitRate());
    }

    @Test
    public void testSort(){
        List<SumProfit> sumProfits = new ArrayList<>();//每只股票的总收益率


        SumProfit sumProfit = new SumProfit();
        sumProfit.setStockCode("000002");
        sumProfit.setStockName("呸呸");
        sumProfit.setStrategyId(2222);
        sumProfit.setProfitRate("-0.2");
        sumProfits.add(sumProfit);

        sumProfit = new SumProfit();
        sumProfit.setStockCode("000001");
        sumProfit.setStockName("哈哈");
        sumProfit.setStrategyId(2222);
        sumProfit.setProfitRate("-10000");
        sumProfits.add(sumProfit);

        sumProfit = new SumProfit();
        sumProfit.setStockCode("000003");
        sumProfit.setStockName("呵呵");
        sumProfit.setStrategyId(2222);
        sumProfit.setProfitRate("12");
        sumProfits.add(sumProfit);

        sumProfit = new SumProfit();
        sumProfit.setStockCode("000004");
        sumProfit.setStockName("最后加入");
        sumProfit.setStrategyId(2222);
        sumProfit.setProfitRate("-10000");
        sumProfits.add(sumProfit);

        Collections.sort(sumProfits);

        System.out.println(sumProfits);
    }


    @Test
    public void testFindDnaStockHold(){
        DnaStockHold stockHold = service.findDnaStockHold("11600000", 3298);
        System.out.println(JSONObject.toJSONString(stockHold));
    }
}