/**
 * 
 * @author mingfei.z 2018年4月12日 下午11:28:20
 */
package com.mfzhang.mayi.spring.aop.v2.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
//@Aspect
@Component("aspectjV2Config")
public class AopAspectj {

	@Pointcut("execution(* com.mfzhang.mayi.spring.aop.service.*.*(..))")
	private void pc() {}
	
	/**
	 * 环绕通知，
	 * 注意：
	 * 1、如果没有调用 pjp.proceed() 来执行目标方法，则只会执行通知，目标方法不会执行；
	 * 2、环绕通知需要返回 目标方法执行后的返回值，即调用 pjp.proceed() 方法返回的是目标方法的返回值，
	 *     不然在实际调用目标方法的地方接收不到返回值，如：
	 *     在Controller 中调用service层接口获取到的值是null；
	 * 
	 * @author mingfei.z
	 * @param pjp
	 */
	@Around("pc()")
	public Object saySt(ProceedingJoinPoint pjp) {
		
		Object result = null;
		
		try {
			System.err.println("--- hello(v2 config) ---");
			result = pjp.proceed();
			System.err.println("--- bye bye(v2 config) ---");
		} catch (Throwable e) {
			
			System.err.println("--- nothing(v2 config) ---");
		}
		
		return result;
	}
	
}
