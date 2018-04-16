/**
 * 
 * @author mingfei.z 2018年4月16日 下午10:05:24
 */
package com.mfzhang.mayi.spring.aop.v2.xml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * xml配置方式，实现aop环绕通知
 * @author mingfei.z
 */
public class AopAspect {

	public Object say(ProceedingJoinPoint pj) {
		Object result = null;
		
		try {
			System.err.println("--- hello(v2 xml) ---");
			result = pj.proceed();
			System.err.println("--- bye bye(v2 xml) ---");
		} catch (Throwable e) {
			System.err.println("--- nothing(v2 xml) ---");
		}
		
		return result;
	}
	
}
