package com.youguu.quant.klinesim.dao;

import com.youguu.quant.klinesim.KlineSimResult;




public interface KlineSimDao  {
	
	String saveKlineSim(KlineSimResult klinesim);

	KlineSimResult getKlineSimById(String id);

}
