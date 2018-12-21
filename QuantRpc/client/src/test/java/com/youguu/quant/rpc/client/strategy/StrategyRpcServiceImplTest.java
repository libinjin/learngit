package com.youguu.quant.rpc.client.strategy;

import com.alibaba.fastjson.JSONObject;
import com.youguu.core.util.PageHolder;
import com.youguu.quant.rpc.client.QuantTradeRpcClientFactory;
import com.youguu.quant.strategy.pojo.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StrategyRpcServiceImplTest {

    public static IStrategyRpcService service = QuantTradeRpcClientFactory.getStrategyRpcService();

    @Test
    public void testSaveStrategyBestStock() throws Exception {

    }

    @Test
    public void testQueryAllStrategy() throws Exception {

    }

    @Test
    public void testSaveStrategyComment() throws Exception {

    }

    @Test
    public void testDeleteStrategyComment() throws Exception {

    }

    @Test
    public void testUpdateStrategyComment() throws Exception {

    }

    @Test
    public void testGetStrategyComment() throws Exception {

    }

    @Test
    public void testQueryStrategyCommentByPage() throws Exception {

    }

    @Test
    public void testSaveStrategyScore() throws Exception {

    }

    @Test
    public void testGetStrategyScore() throws Exception {

    }

    @Test
    public void testSaveStrategy() throws Exception {
        Strategy s = new Strategy();
        s.setName("aaa");
        service.saveStrategy(s);
    }

    @Test
    public void testDeleteStrategy() throws Exception {

    }

    @Test
    public void testUpdateStrategy() throws Exception {

    }

    @Test
    public void testGetStrategy() throws Exception {

    }

    @Test
    public void testQueryStrategyByPage() throws Exception {
        PageHolder<Strategy> page =  service.queryStrategyByPage(1,-1,-1,-1, 1, 10);
        for(Strategy s:page.getList())
        {
            System.out.println(s.getName());
        }
    }

    @Test
    public void testUpdateStrategyStatus() throws Exception {
        service.updateStrategyStatus(1,2);
    }

    @Test
    public void testSaveStrategyUser() throws Exception {

    }

    @Test
    public void testExpandExpireTime() throws Exception {

    }

    @Test
    public void testGetStrategyUser() throws Exception {

    }

    @Test
    public void testFindStrategyByUserId() throws Exception {
        System.out.println(service.findStrategyByUserId(123,1));
    }

    @Test
    public void testQueryStrategyUserByPage() throws Exception {

    }

    @Test
    public void testBatchSaveStrategyUserStock() throws Exception {

    }

    @Test
    public void testFindStockByStrategyAndUser() throws Exception {

    }

    @Test
    public void testSaveUserLoop() throws Exception {

    }

    @Test
    public void testIncrLoopNum() throws Exception {

    }

    @Test
    public void testIncrFinishNum() throws Exception {
        service.incrFinishNum(206655,1111,"111111,2222222");
    }

    @Test
    public void testFindUserLoopByUserId() throws Exception {

    }

    @Test
    public void testBatchInsertStrategyStockOneProfit() throws Exception {

    }

    @Test
    public void testTruncateStrategyStockOneProfit() throws Exception {

    }

    @Test
    public void testQueryStrategyStockOneProfit() throws Exception {

    }

    @Test
    public void testBatchInsertStrategyStockThreeProfit() throws Exception {

    }

    @Test
    public void testTruncateStrategyStockThreeProfit() throws Exception {

    }

    @Test
    public void testQueryStrategyStockThreeProfit() throws Exception {

    }

    @Test
    public void testGetStrategyStockBlacklist() throws Exception {
        List<StrategyStockBlacklist> list =  service.getStrategyStockBlacklist(1111);
        System.out.println(list != null ? list.get(0) : "null");
    }

    @Test
    public void testDeleteAllUserLoop() throws Exception {
        System.out.println(service.deleteAllUserLoop());
    }

    @Test
    public void testSendTradeMq() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("strategyid","123");
        obj.put("starttime","111");
        obj.put("endtime","222");
        service.sendTradeMq(4,obj.toJSONString());
    }

    @Test
    public void testQueryAllStrategyBestStock(){

    }

    @Test
    public void testDisposeQuantStk()throws Exception{
        String time = "2016-09-30 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        System.out.println(service.disposeQuantStk(1,date));
    }

    @Test
    public void testIsExitsFile()throws Exception{
        System.out.println(service.isExitsFile(2237));
    }

    @Test
    public void testClearStrategyData()throws Exception{
        System.out.println(service.clearStrategyData(2236));
    }


    @Test
    public void queryTodayTrade(){
        System.out.println(service.queryTodayTrade(2238,1,0,10));
    }
    public static StockTradeToday getStockTradeToday(){
        StockTradeToday stt = new StockTradeToday();
        stt.setStrategyId(2237);
        stt.setType(1);
        stt.setStockCode("21000002");
        stt.setStockName("万科 A");
        stt.setTradeTime(201611211002L);
        stt.setPrice(127100);
        stt.setRank(1301000002);
        stt.setCreateTime(new Date());
        return stt;
    }
    @Test
    public void addTodayTrade(){
        service.addTodayTrade(2238,getStockTradeToday());
    }

    @Test
    public void clearTodayTrade(){
        service.clearTodayTrade(2237,12);
    }

    @Test
    public void updateTodayBuy(){
        List<StockTradeToday> list = service.queryTodayTrade(2238,1,0,10);
        for(StockTradeToday st:list){
            st.setRank(12);
        }
        service.updateTodayBuy(2238,list);
    }


    public static List<StockTradeNear5days> getStockTradeNear5days(){
        List<StockTradeNear5days> list = new ArrayList<>();
        StockTradeNear5days stt = new StockTradeNear5days();
        stt.setStrategyId(2238);
        stt.setStockCode("21000002");
        stt.setStockName("万科 A");
        stt.setTradeTime(201611211002L);
        stt.setPrice(127100);
        stt.setRank(1001000002);
        stt.setCreateTime(new Date());
        list.add(stt);

        stt = new StockTradeNear5days();
        stt.setStrategyId(2238);
        stt.setStockCode("21000001");
        stt.setStockName("平安银行");
        stt.setTradeTime(201611211002L);
        stt.setPrice(127100);
        stt.setRank(1001000001);
        stt.setCreateTime(new Date());
        list.add(stt);

        stt = new StockTradeNear5days();
        stt.setStrategyId(2238);
        stt.setStockCode("11600000");
        stt.setStockName("浦发银行");
        stt.setTradeTime(201611211002L);
        stt.setPrice(127100);
        stt.setRank(1001600000);
        stt.setCreateTime(new Date());
        list.add(stt);


        return list;
    }

    @Test
    public void addStockTradeNear5Days(){
        service.addStockTradeNear5Days(2238,getStockTradeNear5days());
    }


    @Test
    public void clearNear5DaysTrade(){
        service.clearNear5DaysTrade(2238);
    }


    @Test
    public void queryDays5Trade(){
        System.out.println(service.queryDays5Trade(2238,0,2));
    }

    @Test
    public void searchStrategyBestStock(){
        StrategySearch strategySearch = service.searchStrategyBestStock();

        System.out.println(strategySearch.getList().get(0));
    }
}