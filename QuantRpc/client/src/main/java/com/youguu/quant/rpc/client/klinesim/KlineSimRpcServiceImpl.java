package com.youguu.quant.rpc.client.klinesim;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.rpc.common.Constants;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Service("klineSimRpcService")
public class KlineSimRpcServiceImpl implements IKLineSimRpcService{
    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_CLIENT);
    
    private KlineSimClient getClient() {
        return KlineSimClient.getInstance();
    }
	@Override
	public KlineSimResult getKlineSimById(String id, int userId) {
		 try {
	            String json = getClient().findKLineSimById(id,userId);
	            if("".equals(json))
	            {
	                return null;
	            }
	            return JSON.parseObject(json, KlineSimResult.class);
	     } catch (TException e) {
	            logger.error(e.getMessage(),e);
	            return null;
	     }
	}

	@Override
	public String saveKlineSimData(KlineSimResult klinesim) {
		try {
            return getClient().saveKlineSimResult(JSON.toJSONString(klinesim));
		} catch (TException e) {
            logger.error(e.getMessage(),e);
            return null;
		}
	}
    
    

}
