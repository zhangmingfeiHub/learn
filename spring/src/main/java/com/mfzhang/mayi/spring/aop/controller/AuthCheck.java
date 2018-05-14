/**
 * 
 * @author mingfei.z 2018年4月19日 下午11:31:07
 */
package com.mfzhang.mayi.spring.aop.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author mingfei.z
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

	
	
}
