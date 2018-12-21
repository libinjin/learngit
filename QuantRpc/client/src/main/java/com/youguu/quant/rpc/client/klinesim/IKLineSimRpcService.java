package com.youguu.quant.rpc.client.klinesim;

import com.youguu.quant.klinesim.KlineSimResult;

/**
 * Created by SomeBody on 2016/8/30.
 */
public interface IKLineSimRpcService {
	KlineSimResult getKlineSimById(String id, int userId);
	String saveKlineSimData(KlineSimResult klinesim);
}
