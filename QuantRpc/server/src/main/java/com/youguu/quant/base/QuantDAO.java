package com.youguu.quant.base;

import com.youguu.core.dao.SqlDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;

public class QuantDAO<T> extends SqlDAO<T> {
	public QuantDAO() {
		super();
		setUseSimpleName(true);
	}

	@Resource(name = "quantTradeSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

}
