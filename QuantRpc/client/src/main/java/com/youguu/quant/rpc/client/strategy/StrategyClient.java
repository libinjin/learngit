package com.youguu.quant.rpc.client.strategy;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.core.util.RPCServiceClient;
import com.youguu.core.util.rpc.multipex.RPCMultiplexConnection;
import com.youguu.core.util.rpc.multipex.RPCMultiplexPool;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.rpc.thrift.gen.StrategyServiceThriftRpcService;
import com.youguu.quant.rpc.thrift.gen.StrategyStockOneProfitThrift;
import com.youguu.quant.rpc.thrift.gen.StrategyStockThreeProfitThrift;
import org.apache.thrift.TException;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/26.
 */
public class StrategyClient implements StrategyServiceThriftRpcService.Iface {

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
    public int saveStrategyBestStock(String stockList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategyBestStock(stockList);
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
    public int updateStrategyBestStock(String stockList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateStrategyBestStock(stockList);
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
    public String queryStrategyBestStock(int num) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyBestStock(num);
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
    public String queryAllStrategyBestStock(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryAllStrategyBestStock(strategyId);
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
    public String queryAllStrategy(int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryAllStrategy(userId);
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
    public int saveStrategyComment(String strategy) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategyComment(strategy);
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
    public int deleteStrategyComment(int id) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).deleteStrategyComment(id);
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
    public int updateStrategyComment(String strategy) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateStrategyComment(strategy);
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
    public String getStrategyComment(int id) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategyComment(id);
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
    public String queryStrategyCommentByPage(int strategyId,int userId, int pageIndex, int pageSize) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyCommentByPage(strategyId, userId, pageIndex, pageSize);
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
    public String findStrategyCommentList(int strategyId, int pageIndex, int pageSize) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyCommentList(strategyId, pageIndex, pageSize);
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
    public int updateCommentStatus(int id, int status) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateCommentStatus(id, status);
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
    public int saveStrategyScore(String strategyScore) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategyScore(strategyScore);
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
    public String getStrategyScore(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategyScore(strategyId);
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
    public int saveStrategy(String strategy) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategy(strategy);
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
    public int deleteStrategy(int id) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).deleteStrategy(id);
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
    public int updateStrategy(String strategy) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateStrategy(strategy);
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
    public String getStrategy(int id) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategy(id);
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
    public String queryStrategyByPage(int id,int type,int displayStatus,int runStatus,int pageIndex,int pageSize) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyByPage(id, type, displayStatus, runStatus, pageIndex, pageSize);
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
    public int updateStrategyStatus(int id, int status) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateStrategyStatus(id, status);
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
    public int saveStrategyUser(String strategy) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategyUser(strategy);
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
    public int expandExpireTime(int userId, int strategyId, int days,String payType,double price) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).expandExpireTime(userId, strategyId, days,payType,price);
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
    public String getStrategyUser(int id) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategyUser(id);
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
    public String findStrategyByUserId(int userId, int expire) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyByUserId(userId, expire);
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
    public String findStrategyUserByUserId(int userId, int expire) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyUserByUserId(userId, expire);
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
    public String queryStrategyUserByPage(int userId,int strategyId, int pageIndex, int pageSize) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyUserByPage(userId, strategyId, pageIndex, pageSize);
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
    public int batchSaveStrategyUserStock(int userId, int strategyId,String relationList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).batchSaveStrategyUserStock(userId, strategyId,relationList);
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
    public String findStockByStrategyAndUser(int userId, int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStockByStrategyAndUser(userId,strategyId);
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
    public String queryStrategyPortfolioList(int userId, int strategyId, int type) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyPortfolioList(userId, strategyId, type);
        } catch (TException e) {
            client.setIdle(false);
            throw e;
        } finally {
            if (client != null) {
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public String forecastPortfolioList(int userId, int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).forecastPortfolioList(userId, strategyId);
        } catch (TException e) {
            client.setIdle(false);
            throw e;
        } finally {
            if (client != null) {
                try {
                    pool.returnObject(client);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public int saveUserLoop(int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveUserLoop(userId);
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
    public int deleteAllUserLoop() throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).deleteAllUserLoop();
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
    public int incrLoopNum(int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).incrLoopNum(userId);
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
    public int incrFinishNum(int userId,int strategyId,String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).incrFinishNum(userId, strategyId,stocks);
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
    public String findUserLoopByUserId(int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findUserLoopByUserId(userId);
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
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfitThrift> thriftList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).batchInsertStrategyStockOneProfit(thriftList);
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
    public int truncateStrategyStockOneProfit(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).truncateStrategyStockOneProfit(strategyId);
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
    public String queryStrategyStockOneProfit(int strategyId, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyStockOneProfit(strategyId, stocks);
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
    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfitThrift> thriftList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).batchInsertStrategyStockThreeProfit(thriftList);
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
    public int truncateStrategyStockThreeProfit(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).truncateStrategyStockThreeProfit(strategyId);
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
    public String queryStrategyStockThreeProfit(int strategyId, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyStockThreeProfit(strategyId, stocks);
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
    public String findStrategyStockOneProfit(int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyStockOneProfit(strategyId, stockCode);
        }catch(TException e){
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
    public int saveStrategyStockBlacklist(String ssb) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategyStockBlacklist(ssb);
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
    public String getOneYearAverageProfit(int strategyId, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getOneYearAverageProfit(strategyId, stocks);
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
    public String getThreeYearAverageProfit(int strategyId, String stocks) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getThreeYearAverageProfit(strategyId, stocks);
        }catch(TException e){
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
    public String getStrategyStockBlacklist(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategyStockBlacklist(strategyId);
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
    public int updateStockBlack(int id, String stockCodes) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateStockBlack(id, stockCodes);
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
    public int sendTradeMq(int type, String json) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).sendTradeMq(type, json);
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
    public String getPermissionNum(int userId,int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getPermissionNum(userId, strategyId);
        } catch (TException e){
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
    public String queryStrategyReport(int strategyId, String stocks,int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyReport(strategyId, stocks, userId);
        } catch (TException e){
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
    public String findStockByStrategyAndStock(int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStockByStrategyAndStock(strategyId, stockCode);
        } catch (TException e){
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
    public int delRecommendHotStockByType(int type, int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).delRecommendHotStockByType(type, strategyId);
        } catch (TException e){
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
    public int dredgeStrategyAuthByMq(int userId, int strategyId, int day,String payType,double price) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).dredgeStrategyAuthByMq(userId, strategyId, day, payType, price);
        } catch (TException e){
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
    public String findStrategyByCategoryId(String categoryId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyByCategoryId(categoryId);
        } catch (TException e){
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
    public String findAllRunStrategyId() throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findAllRunStrategyId();
        } catch (TException e){
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
    public String getStrategyUserByUidSid(int userId, int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategyUserByUidSid(userId, strategyId);
        } catch (TException e){
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
            return client.getClient(StrategyServiceThriftRpcService.Client.class).heartBeat();
        } catch (TException e){
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
    public String disposeQuantStk(int strategyId, String time) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).disposeQuantStk(strategyId, time);
        } catch (TException e){
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
    public boolean isExitsFile(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).isExitsFile(strategyId);
        } catch (TException e){
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
    public boolean clearStrategyData(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).clearStrategyData(strategyId);
        } catch (TException e){
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
    public String findAllValidRelation() throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findAllValidRelation();
        } catch (TException e){
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
    public String findStrategyBuyUserCount(String time) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyBuyUserCount(time);
        } catch (TException e){
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
    public String findStrategyUserLoopCount(String time) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findStrategyUserLoopCount(time);
        } catch (TException e){
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
    public String queryStrategyBestStockListBySid(int sid, int limit) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryStrategyBestStockListBySid(sid, limit);
        } catch (TException e){
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

    public int saveStrategyUserStock(int userId, int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).saveStrategyUserStock(userId, strategyId, stockCode);
        } catch (TException e){
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
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).deleteStrategyUserStock(userId, strategyId, stockCode);
        } catch (TException e){
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
    public int selectFollowNum(int userId, int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).selectFollowNum(userId, strategyId);
        } catch (TException e){
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
    public String queryTodayTrade(int strategyId, int type, long seq, int num) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryTodayTrade(strategyId, type, seq, num);
        } catch (TException e){
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
    public int addTodayTrade(int strategyId, String stockTradeToday) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).addTodayTrade(strategyId, stockTradeToday);
        } catch (TException e){
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
    public int clearTodayTrade(int strategyId, int positionNum) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).clearTodayTrade(strategyId, positionNum);
        } catch (TException e){
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
    public int updateTodayBuy(int strategyId, String stockTradeTodayList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).updateTodayBuy(strategyId, stockTradeTodayList);
        } catch (TException e){
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
    public int addStockTradeNear5Days(int strategyId, String stockTradeNear5DaysList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).addStockTradeNear5Days(strategyId, stockTradeNear5DaysList);
        } catch (TException e){
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
    public int clearNear5DaysTrade(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).clearNear5DaysTrade(strategyId);
        } catch (TException e){
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
    public String queryDays5Trade(int strategyId, long seq, int num) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).queryDays5Trade(strategyId, seq, num);
        } catch (TException e){
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
    public String searchStockResult(int userId, int strategyId, String stockCode) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).searchStockResult(userId, strategyId, stockCode);
        } catch (TException e){
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
    public String getStrategyExtend(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getStrategyExtend(strategyId);
        } catch (TException e){
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
    public String findUserFollowStock(int userId, int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findUserFollowStock(userId, strategyId);
        } catch (TException e){
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
    public String findReportData(int day, double lt, double rt, int num) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findReportData(day, lt, rt, num);
        } catch (TException e){
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
    public String findAllStrategyUser(String time) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findAllStrategyUser(time);
        } catch (TException e){
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
    public String searchStrategyBestStock() throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).searchStrategyBestStock();
        } catch (TException e){
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

    public int addRecommendHotStock(String recommendHotStock) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).addRecommendHotStock(recommendHotStock);
        } catch (TException e){
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
    public int batchAddRecommendHotStock(String recommendHotStockList) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).batchAddRecommendHotStock(recommendHotStockList);
        } catch (TException e){
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
    public int delRecommendHotStock(int strategyId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).delRecommendHotStock(strategyId);
        } catch (TException e){
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
    public String getRecommendHotStockList(int strategyId, int type) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).getRecommendHotStockList(strategyId,type);
        } catch (TException e){
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
    public int incrDNALoopNum(int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).incrDNALoopNum(userId);
        } catch (TException e){
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
    public String findDNALoopByUserId(int userId) throws TException {
        RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(StrategyServiceThriftRpcService.Client.class).findDNALoopByUserId(userId);
        } catch (TException e){
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
