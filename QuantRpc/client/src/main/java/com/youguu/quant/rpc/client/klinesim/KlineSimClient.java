package com.youguu.quant.rpc.client.klinesim;

import org.apache.thrift.TException;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.core.util.RPCServiceClient;
import com.youguu.core.util.rpc.multipex.RPCMultiplexConnection;
import com.youguu.core.util.rpc.multipex.RPCMultiplexPool;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.rpc.thrift.gen.KLineServiceThriftRpcService;
import com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService;

/**
 * Created by SomeBody on 2016/8/30.
 */
public class KlineSimClient implements KLineServiceThriftRpcService.Iface {

    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_CLIENT);
    
    private static RPCMultiplexPool pool = RPCServiceClient.getMultiplexCPool(Constants.QuantRpcPOOL);
    
    private volatile static KlineSimClient klineSimClient;
    private KlineSimClient (){}
    public static KlineSimClient getInstance() {
        if (klineSimClient == null){
            synchronized (KlineSimClient.class){
                if(klineSimClient==null){
                    klineSimClient= new KlineSimClient(){};
                }
            }
        }

        return klineSimClient;
    }

    private RPCMultiplexConnection getConnection(){
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
    
	@Override
	public String findKLineSimById(String id,int userId) throws TException {
		RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(KLineServiceThriftRpcService.Client.class).findKLineSimById(id,userId);
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
	public String saveKlineSimResult(String klinesim) throws TException {
		RPCMultiplexConnection client = null;
        try {
            client = getConnection();
            return client.getClient(KLineServiceThriftRpcService.Client.class).saveKlineSimResult(klinesim);
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

   
}
