package com.youguu.quant.klinesim.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.klinesim.dao.KlineSimDao;
import com.youguu.quant.klinesim.service.CollTransferType;
import com.youguu.quant.klinesim.service.KlineSimService;
import com.youguu.quant.strategy.service.IStrategyUserService;

@Service("klineSimService")
@CollTransferType(collection = "klineSimResult")
public class KlineSimServiceImpl implements KlineSimService{
	
	@Resource
	private KlineSimDao klineSimDAO;
	
	
	@Override
	public String saveKlineSim(KlineSimResult klinesim) {
		return klineSimDAO.saveKlineSim(klinesim);
	}

	@Override
	public KlineSimResult getKlineSimById(String id) {
		return klineSimDAO.getKlineSimById(id);
	}

}
