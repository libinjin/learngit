package com.youguu.quant.rpc.server.signal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService;
import com.youguu.quant.signal.pojo.*;
import com.youguu.quant.signal.service.ISignalService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Service("signalServiceThriftRpcService")
public class SignalServiceThriftRpcServiceImpl implements SignalServiceThriftRpcService.Iface {

    private static final Log logger = LogFactory.getLog(SignalServiceThriftRpcServiceImpl.class);

    @Resource
    private ISignalService signalService;

    @Override
    public String loadStockFileName(int strategyId) throws TException {
        List<String> fileNameList = signalService.loadStockFileName(strategyId);
        if(fileNameList!=null && fileNameList.size()>0){
            return JSON.toJSONString(fileNameList);
        }
        return "";
    }

    @Override
    public String loadTradeSignal(int strategyId, String stocks) throws TException {
        if(stocks!=null && !"".equals(stocks)){
            Map<String, List<TradeSignal>> map = signalService.loadTradeSignal(strategyId, stocks.split(","));
            return JSON.toJSONString(map);
        }
        return "";
    }

    @Override
    public String loadTradeSignalDays(int strategyId, int days, String stocks) throws TException {
        if(stocks!=null && !"".equals(stocks)){
            Map<String, List<TradeSignal>> map = signalService.loadTradeSignal(strategyId, days, stocks.split(","));
            return JSON.toJSONString(map);
        }
        return "";
    }

    @Override
    public String loadTradeSignalSection(int strategyId, long startDate, long endDate, String stockCode) throws TException {
        if(stockCode!=null && !"".equals(stockCode)){
            Map<String, List<TradeSignal>> map = signalService.loadTradeSignal(strategyId, startDate, endDate, stockCode.split(","));
            return JSON.toJSONString(map);
        }
        return "";
    }

    @Override
    public String getLastTradeSignal(int strategyId, String stockCode) throws TException {
        TradeSignal ts = signalService.getLastTradeSignal(strategyId, stockCode);
        return JSON.toJSONString(ts);
    }

    @Override
    public String readOneTradeSignal(int strategyId, String stockCode) throws TException {
        TradeSignal ts = signalService.getLastBuyTradeSignal(strategyId, stockCode);
        return JSON.toJSONString(ts);
    }

    public String queryPageStrategyTradeRecord(int strategyId, int userId, int type, int pageIndex, int pageSize, String stocks) throws TException {
        List<StrategyTradeRecord> list = signalService.queryPageStrategyTradeRecord(strategyId, userId, type, pageIndex, pageSize, stocks.split(","));
        String res = JSONArray.toJSONString(list);
        return res;
    }

    @Override
    public String queryStockRealtimeSignalList(int strategyId, int userId) throws TException {
        Map<String,StockRealtimeSignal> map=signalService.queryStockRealtimeSignalList(strategyId,userId);
        String res = JSONArray.toJSONString(map);
        return res;
    }

    @Override
    public String queryAverageProfitCurve(int strategyId, int days,String stocks) throws TException {
        List<ProfitCurve> list=signalService.queryAverageProfitCurve(strategyId,days,stocks.split(","));
        String res = JSONArray.toJSONString(list);
        return res;
    }

    @Override
    public String querySumProfit(int strategyId, int days, int type, String stocks) throws TException {
        List<SumProfit> list=signalService.querySumProfit(strategyId, days, type,stocks.split(","));
        String res = JSONArray.toJSONString(list);
        return res;
    }

    @Override
    public String querySumProfitNew(int strategyId, long startDate, long endDate, int type, String stocks) throws TException {
        List<SumProfit> list=signalService.querySumProfitNew(strategyId, startDate, endDate, type,stocks.split(","));
        String res = JSONArray.toJSONString(list);
        return res;
    }


    @Override
    public int heartBeat() throws TException {
        return 1;
    }

    @Override
    public String deleteSignalFile(int strategyId) throws TException {
        return signalService.deleteSignalFile(strategyId).toJSONString();
    }

    @Override
    public String countProfit(int strategyId, long date) throws TException {
        Date time = new Date(date);
        return signalService.countProfit(strategyId,time);
    }

    @Override
    public String countfiveDay(int strategyId, double five) throws TException {
        logger.info("-------------------------------------------countfiveDay："+five);
        List<Stock5dayAndNowHold> list = signalService.countfiveDay(strategyId, five);
        if(list == null || list.size()==0)
        {
            return "";
        }
        String res = JSONArray.toJSONString(list);
        return res;
    }

    @Override
    public String countNowHoldStock(int strategyId, double now) throws TException {
        logger.info("------------------------------------------countNowHoldStock："+now);
        List<Stock5dayAndNowHold> list = signalService.countNowHoldStock(strategyId,now);
        if(list == null || list.size()==0)
        {
            return "";
        }
        String res = JSONArray.toJSONString(list);
        return res;
    }

    @Override
    public int disReportData(int strategyId) throws TException {
        return signalService.disReportData(strategyId);
    }

    @Override
    public String findDnaStockHold(String stockCode, int userId) throws TException {
        return JSONObject.toJSONString(signalService.findDnaStockHold(stockCode,userId));
    }

    @Override
    public String getLastTradeSignalByDate(int strategyId, int statDate, String stockCode) throws TException {
        return JSONObject.toJSONString(signalService.getLastTradeSignal(strategyId, statDate,stockCode));
    }

    @Override
    public String getLastBuyTradeSignalByDate(int strategyId, int statDate, String stockCode) throws TException {
        return JSONObject.toJSONString(signalService.getLastBuyTradeSignal(strategyId, statDate,stockCode));
    }
}
