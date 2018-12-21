package com.youguu.quant.rpc.client.signal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.signal.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Service("signalRpcService")
public class SignalRpcServiceImpl implements ISignalRpcService{
    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_CLIENT);

    private SignalClient getClient() {
        return new SignalClient();
    }

    @Override
    public List<String> loadStockFileName(int strategyId) {
        try {
            String json = getClient().loadStockFileName(strategyId);
            return JSON.parseObject(json, List.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, String... stockCode) {
        try {
            String stocks = StringUtils.join(stockCode, ",");
            String json = getClient().loadTradeSignal(strategyId, stocks);
            return JSON.parseObject(json, new TypeReference<Map<String, List<TradeSignal>>>(){});
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, int days, String... stockCode) {
        try {
            String stocks = StringUtils.join(stockCode, ",");
            String json = getClient().loadTradeSignalDays(strategyId, days, stocks);
            return JSON.parseObject(json, new TypeReference<Map<String, List<TradeSignal>>>(){});
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, long startDate, long endDate, String... stockCode) {
        try {
            String stocks = StringUtils.join(stockCode, ",");
            String json = getClient().loadTradeSignalSection(strategyId, startDate, endDate, stocks);
            return JSON.parseObject(json, new TypeReference<Map<String, List<TradeSignal>>>(){});
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public TradeSignal getLastTradeSignal(int strategyId, String stockCode) {
        try {
            String json = getClient().getLastTradeSignal(strategyId, stockCode);
            return JSON.parseObject(json, TradeSignal.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public TradeSignal getLastBuyTradeSignal(int strategyId, String stockCode) {
        try {
            String json = getClient().readOneTradeSignal(strategyId, stockCode);
            return JSON.parseObject(json, TradeSignal.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    public List<StrategyTradeRecord> queryPageStrategyTradeRecord(int strategyId, int userId, int type, int pageIndex, int pageSize, String stocks) {
        try {
            String json = getClient().queryPageStrategyTradeRecord(strategyId, userId, type, pageIndex, pageSize, stocks);
            return JSONArray.parseArray(json,StrategyTradeRecord.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public Map<String, StockRealtimeSignal> queryStockRealtimeSignalList(int strategyId, int userId) {
        try {
            String json = getClient().queryStockRealtimeSignalList(strategyId, userId);
            return (Map<String,StockRealtimeSignal>) JSON.parse(json);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public List<ProfitCurve> queryAverageProfitCurve(int strategyId,int days, String stocks) {
        try {
            String json = getClient().queryAverageProfitCurve(strategyId,days, stocks);
            return JSONArray.parseArray(json,ProfitCurve.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public List<SumProfit> querySumProfit(int strategyId, int days, int type, String stocks) {
        try {
            String json = getClient().querySumProfit(strategyId, days, type, stocks);
            return JSONArray.parseArray(json,SumProfit.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public List<SumProfit> querySumProfitNew(int strategyId, long startDate, long endDate, int type, String stocks) {
        try {
            String json = getClient().querySumProfitNew(strategyId, startDate, endDate, type, stocks);
            return JSONArray.parseArray(json,SumProfit.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public int heartBeat() {
        try {
            return getClient().heartBeat();
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    @Override
    public JSONObject deleteSignalFile(int strategyId) {
        try {
            String json = getClient().deleteSignalFile(strategyId);
            return JSONObject.parseObject(json);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public String countProfit(int strategyId, Date date) {
        try {
            long time = date.getTime();
            String json = getClient().countProfit(strategyId,time);
            return json;
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public List<Stock5dayAndNowHold> countfiveDay(int strategyId, double five) {
        try {
            String json = getClient().countfiveDay(strategyId,five);
            if("".equals(json))
            {
                return null;
            }
            return JSONArray.parseArray(json,Stock5dayAndNowHold.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public List<Stock5dayAndNowHold> countNowHoldStock(int strategyId, double now) {
        try {
            String json = getClient().countNowHoldStock(strategyId,now);
            if("".equals(json))
            {
                return null;
            }
            return JSONArray.parseArray(json,Stock5dayAndNowHold.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public int disReportData(int strategyId) {
        try {
            return getClient().disReportData(strategyId);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    @Override
    public DnaStockHold findDnaStockHold(String stockCode, int userId) {
        try {
            String json = getClient().findDnaStockHold(stockCode, userId);
            return JSON.parseObject(json, DnaStockHold.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }


    @Override
    public TradeSignal getLastTradeSignal(int strategyId, int date, String stockCode) {
        try {
            String json = getClient().getLastTradeSignalByDate(strategyId, date, stockCode);
            return JSON.parseObject(json, TradeSignal.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public TradeSignal getLastBuyTradeSignal(int strategyId, int date, String stockCode) {
        try {
            String json = getClient().getLastBuyTradeSignalByDate(strategyId, date , stockCode);
            return JSON.parseObject(json, TradeSignal.class);
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }
}
