/**
 * 
 * @author mingfei.z 2018年4月12日 下午11:54:36
 */
package com.mfzhang.mayi.spring.aop.v3.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 传递通知参数
 * @author mingfei.z
 */
//@Aspect
@Component("aspectjV3Config")
public class AopAspectj {

	@Pointcut("execution(* com.mfzhang.mayi.spring.aop.service.*.*(..))"
			+ "&& args(a)")
	public void pc(Integer a) {}
	
	@Before("pc(a)")
	public void sayHello(Integer a) {

		System.err.println("--- hello(v3 config) ---" + a.toString() );
	}

	@AfterReturning("pc(a)")
	public void sayBye(Integer a) {

		System.err.println("--- bye bye(v3 config) ---");
	}

	@AfterThrowing("pc(a)")
	public void sayNo(Integer a) {

		System.err.println("--- nothing(v3 config) ---");
	}
	
}
