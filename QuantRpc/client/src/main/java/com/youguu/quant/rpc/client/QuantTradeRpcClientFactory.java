package com.youguu.quant.rpc.client;

import com.youguu.quant.rpc.client.klinesim.IKLineSimRpcService;
import com.youguu.quant.rpc.client.klinesim.KlineSimRpcServiceImpl;
import com.youguu.quant.rpc.client.signal.ISignalRpcService;
import com.youguu.quant.rpc.client.signal.SignalRpcServiceImpl;
import com.youguu.quant.rpc.client.strategy.IStrategyRpcService;
import com.youguu.quant.rpc.client.strategy.StrategyRpcServiceImpl;


public class QuantTradeRpcClientFactory {
    private static IStrategyRpcService strategyRpcService = null;

    public static IStrategyRpcService getStrategyRpcService() {
        if (strategyRpcService != null) {
            return strategyRpcService;
        } else {
            synchronized (QuantTradeRpcClientFactory.class) {
                if (strategyRpcService != null) {
                    return strategyRpcService;
                } else {
                    strategyRpcService = new StrategyRpcServiceImpl();
                }
            }
        }
        return strategyRpcService;
    }

    private static ISignalRpcService signalRpcService = null;

    public static ISignalRpcService getSignalRpcService() {
        if (signalRpcService != null) {
            return signalRpcService;
        } else {
            synchronized (QuantTradeRpcClientFactory.class) {
                if (signalRpcService != null) {
                    return signalRpcService;
                } else {
                    signalRpcService = new SignalRpcServiceImpl();
                }
            }
        }
        return signalRpcService;
    }
    
    private static IKLineSimRpcService klineSimRpcService = null;
    
    public static IKLineSimRpcService getKlineSimRpcService(){
    	if(klineSimRpcService != null){
    		return klineSimRpcService;
    	}else{
    		synchronized (QuantTradeRpcClientFactory.class) {
    			if(klineSimRpcService != null){
    				return klineSimRpcService;
    			}else{
    				klineSimRpcService = new KlineSimRpcServiceImpl();
    			}
			}
    	}
    	return klineSimRpcService;
    }
}
