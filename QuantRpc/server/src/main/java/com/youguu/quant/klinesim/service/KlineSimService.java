package com.youguu.quant.klinesim.service;

import com.youguu.quant.klinesim.KlineSimResult;

public interface KlineSimService {
	String saveKlineSim(KlineSimResult klinesim);
	KlineSimResult getKlineSimById(String id);
}
