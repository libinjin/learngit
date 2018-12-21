package com.youguu.quant.rpc.client.signal;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.core.util.RPCServiceClient;
import com.youguu.core.util.rpc.multipex.RPCMultiplexConnection;
import com.youguu.core.util.rpc.multipex.RPCMultiplexPool;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService;
import org.apache.thrift.TException;

/**
 * Created by SomeBody on 2016/8/30.
 */
public class SignalClient implements SignalServiceThriftRpcService.Iface {

    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_CLIENT);

    private static RPCMultiplexPool pool = RPCServiceClient.getMultiplexCPool(Constants.QuantRpcPOOL);

    private RPCMultiplexConnection getConnection(){
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public String loadStockFileName(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).loadStockFileName(strategyId);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String loadTradeSignal(int strategyId, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).loadTradeSignal(strategyId, stocks);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String loadTradeSignalDays(int strategyId, int days, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).loadTradeSignalDays(strategyId, days, stocks);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String loadTradeSignalSection(int strategyId, long startDate, long endDate, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).loadTradeSignalSection(strategyId, startDate, endDate, stockCode);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String getLastTradeSignal(int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).getLastTradeSignal(strategyId, stockCode);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String readOneTradeSignal(int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).readOneTradeSignal(strategyId, stockCode);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    public String queryPageStrategyTradeRecord(int strategyId, int userId, int type, int pageIndex, int pageSize, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).queryPageStrategyTradeRecord(strategyId, userId, type, pageIndex, pageSize, stocks);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String queryStockRealtimeSignalList(int strategyId, int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).queryStockRealtimeSignalList(strategyId, userId);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String queryAverageProfitCurve(int strategyId,int days, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).queryAverageProfitCurve(strategyId,days, stocks);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String querySumProfit(int strategyId, int days, int type, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).querySumProfit(strategyId, days, type, stocks);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String querySumProfitNew(int strategyId, long startDate, long endDate, int type, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).querySumProfitNew(strategyId, startDate, endDate, type, stocks);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }


    @Override
    public int heartBeat() throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).heartBeat();
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String deleteSignalFile(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).deleteSignalFile(strategyId);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String countProfit(int strategyId, long date) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).countProfit(strategyId, date);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String countfiveDay(int strategyId, double five) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).countfiveDay(strategyId, five);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String countNowHoldStock(int strategyId, double now) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).countNowHoldStock(strategyId, now);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public int disReportData(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).disReportData(strategyId);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String findDnaStockHold(String stockCode, int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).findDnaStockHold(stockCode, userId);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String getLastTradeSignalByDate(int strategyId, int statDate, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).getLastTradeSignalByDate(strategyId, statDate, stockCode);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String getLastBuyTradeSignalByDate(int strategyId, int statDate, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(SignalServiceThriftRpcService.Client.class).getLastBuyTradeSignalByDate(strategyId,statDate, stockCode);
        } catch(TException e){
            client.setIdle(false);
            throw e;
        }finally {
            if(client != null){
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }
}
