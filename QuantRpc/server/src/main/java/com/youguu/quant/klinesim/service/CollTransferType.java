/**  
* @Title: CollTransferType.java
* @Package com.youguu.quote.f10.annotation
* @Description: transfer service annotation
* @author TangYanhong
* @date 2014年4月3日 上午11:02:41
* @version V1.0  
*/
 
package com.youguu.quant.klinesim.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: CollTransferType
 * @Description: transfer service annotation
 * 
 * 
 */
@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CollTransferType {
	String collection() default "";
}
