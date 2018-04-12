/**
 * 
 * @author mingfei.z 2018年4月12日 下午11:54:36
 */
package com.mfzhang.mayi.spring.aop.v3;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Aspect
//@Component("aspectjV3")
public class AopAspectj {

	@Pointcut("execution(* com.mfzhang.mayi.spring.aop.service.*.*(..))"
			+ "&& args(userId)")
	public void pc(Integer userId) {}
	
	@Before("pc(userId)")
	public void sayHello(Integer userId) {

		System.err.println("--- hello(v3) ---");
	}

	@AfterReturning("pc(userId)")
	public void sayBye(Integer userId) {

		System.err.println("--- bye bye(v3) ---");
	}

	@AfterThrowing("pc(userId)")
	public void sayNo(Integer userId) {

		System.err.println("--- nothing(v3) ---");
	}
	
}
