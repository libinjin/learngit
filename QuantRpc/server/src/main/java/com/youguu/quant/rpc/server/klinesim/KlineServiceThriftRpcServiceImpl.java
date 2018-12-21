package com.youguu.quant.rpc.server.klinesim;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.klinesim.service.KlineSimService;
import com.youguu.quant.rpc.thrift.gen.KLineServiceThriftRpcService;
import com.youguu.quant.strategy.pojo.UserLoop;
import com.youguu.quant.strategy.service.IStrategyUserService;
import com.youguu.quant.strategy.service.IUserLoopService;
/**
 * Created by SomeBody on 2016/8/30.
 */
@Service("klineServiceThriftRpcService")
public class KlineServiceThriftRpcServiceImpl implements KLineServiceThriftRpcService.Iface {
	
	@Resource
	private KlineSimService klineSimService;
	
	@Resource
	private IStrategyUserService strategyUserService;
	
	@Resource
	private IUserLoopService userLoopService;

	@Override
	public String findKLineSimById(String id, int userId) throws TException {
		boolean isPermission = strategyUserService.haveDNAPermission(userId);
		if(isPermission){
			KlineSimResult klinesim = klineSimService.getKlineSimById(id);
			if(klinesim != null){
				userLoopService.incDNAFinishNum(userId, id);
				return JSON.toJSONString(klinesim);
			}
		}
		return "";
	}

	@Override
	public String saveKlineSimResult(String klinesim) throws TException {
		KlineSimResult kline = JSON.parseObject(klinesim, KlineSimResult.class);
		return klineSimService.saveKlineSim(kline);
	}

	@Override
	public int heartBeat() throws TException {
		 return 1;
	}
	
}
