package com.youguu.quant.base;

import com.youguu.core.dao.DataSourceLoader;
import com.youguu.core.dao.multi.MultiDataSourceTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration("quantContextLoader")
@ComponentScan({"com.youguu*"})
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ContextLoader extends DataSourceLoader {

	public DataSource getDataSource(String selector){
		return loadMulti(selector);
	}
	
	@Bean
	public SqlSessionFactory quantTradeSessionFactory() throws Exception{
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(getDataSource("quantTrade"));
		DefaultResourceLoader dc=new DefaultResourceLoader();
		bean.setConfigLocation(dc.getResource("classpath:mybatis/quant-mapper-config.xml"));
		return bean.getObject();
	}

	@Bean(name="quantTradeTx")
	public DataSourceTransactionManager quantTradeTx() {
		DataSourceTransactionManager transactionManager = new MultiDataSourceTransactionManager();
		transactionManager.setDataSource(getDataSource("quantTrade"));
		return transactionManager;
	}
	

}
