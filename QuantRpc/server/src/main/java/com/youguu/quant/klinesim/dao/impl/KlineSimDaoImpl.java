package com.youguu.quant.klinesim.dao.impl;

import org.mongodb.morphia.Key;
import org.springframework.stereotype.Repository;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.klinesim.dao.KlineSimDao;
import com.youguu.quant.klinesim.dao.TargetMongoBaseDao;

@Repository("klineSimDao")
public class KlineSimDaoImpl extends TargetMongoBaseDao<KlineSimResult, String> implements KlineSimDao{
	
	private static final Log logger = LogFactory.getLog(KlineSimDaoImpl.class);
	
	@Override
	public String saveKlineSim(KlineSimResult klinesim) {
		Key<KlineSimResult> key = save(klinesim);
		
		if(logger.isDebugEnabled())
			logger.debug("save KlineSimResult ok, key=" + key.toString());
		return (String) key.getId();
	}

	@Override
	public KlineSimResult getKlineSimById(String id) {
		return get(id);
	}
	

}
