/**
 * 
 * @author mingfei.z 2018年4月12日 下午11:28:20
 */
package com.mfzhang.mayi.spring.aop.v2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Aspect
@Component("aspectjV2")
public class AopAspectj {

	@Pointcut("execution(* com.mfzhang.mayi.spring.aop.service.*.*(..))")
	private void pc() {}
	
	/**
	 * 环绕通知
	 * 
	 * @author mingfei.z
	 * @param pjp
	 */
	@Around("pc()")
	public void saySt(ProceedingJoinPoint pjp) {
		try {
			System.err.println("--- hello(v2) ---");
			pjp.proceed();
			System.err.println("--- bye bye(v2) ---");
		} catch (Throwable e) {
			
			System.err.println("--- nothing(v2) ---");
		}
	}
	
}
