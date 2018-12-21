/**  
* @Title: TargetMongoBaseDao.java
* @Package com.youguu.quote.f10.dao
* @Description: MonGo db 访问基类
* @author TangYanhong
* @date 2014年4月2日 下午2:44:39
* @version V1.0  
*/
 
package com.youguu.quant.klinesim.dao;

import com.youguu.core.dao.DataStore;
import com.youguu.core.dao.MongoDAO;

/**
 * @ClassName: TargetMongoBaseDao
 * @Description: MonGo db 访问基类
 * 
 * 
 */
@DataStore(value="QuantRpc",dbName="klinesim")
public class TargetMongoBaseDao<T,K> extends MongoDAO<T, K> {
	
}
