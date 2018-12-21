package com.youguu.quant.rpc.client.signal;

import com.youguu.quant.rpc.client.QuantTradeRpcClientFactory;
import com.youguu.quant.signal.pojo.SumProfit;
import com.youguu.quant.signal.pojo.TradeSignal;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SignalRpcServiceImplTest {

    static ISignalRpcService signalRpcService = QuantTradeRpcClientFactory.getSignalRpcService();


    @Test
    public void testLoadStockFileName() throws Exception {
        List<String> list = signalRpcService.loadStockFileName(2235);
        for(String fileName : list){
            System.out.println(fileName);
        }
    }

    @Test
    public void testLoadTradeSignal() throws Exception {
        String[] stocks = new String[]{"11600103","11600110","11600112","21002157","21002750"};
        Map<String, List<TradeSignal>> map = signalRpcService.loadTradeSignal(123, stocks);
        for (List<TradeSignal> value : map.values()) {
            System.out.println("Value = " + value.size());
        }
    }

    @Test
    public void querySumProfit(){
        String aaa="11600757,11600058,11600693,11600895,11600361,11603108,11600988,11601388,11600113,11600689";
        String[] stocks=new String[]{"21300522", "11600010", "11600018", "11600055", "11600060"};
        //String[] stocks=new String[]{"10000300"};
        List<SumProfit> list=signalRpcService.querySumProfit(1111, 750, 0, aaa);
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void testCountProfit(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time  = "2016-11-03 00:00:00";
            Date date = sdf.parse(time);
            System.out.println(signalRpcService.countProfit(2236,date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}