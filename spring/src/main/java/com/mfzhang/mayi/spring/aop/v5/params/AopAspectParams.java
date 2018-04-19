/**
 * 
 * @author mingfei.z 2018年4月19日 下午3:35:10
 */
package com.mfzhang.mayi.spring.aop.v5.params;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfzhang.mayi.common.util.JsonUtils;

/**
 * 
 * @author mingfei.z
 */
@Aspect
public class AopAspectParams {

	private static final Logger logger = LoggerFactory.getLogger(AopAspectParams.class);
	
	@Pointcut(value = "execution(* com.mfzhang.mayi.spring.aop.service.AopService.addUserInfo1(..))")
	public void pc1() {}

	@Pointcut(value = "execution(* com.mfzhang.mayi.spring.aop.service.AopService.addUserInfo2(..))")
	public void pc2() {}
	
	@Before(value = "pc1()")
	public void before(JoinPoint jp) {
		logger.info(" --- 前置通知，toString(): " + jp.toString());
		logger.info(" --- 前置通知，toShortString(): " + jp.toShortString());
		logger.info(" --- 前置通知，toLongString(): " + jp.toLongString());
		logger.info(" --- 前置通知，getThis(): " + jp.getThis().getClass()); // 获取代理类
		logger.info(" --- 前置通知，getTarget(): " + jp.getTarget());
		logger.info(" --- 前置通知，getArgs(): " + JsonUtils.writeValueAsString(jp.getArgs()));
		logger.info(" --- 前置通知，getSignature(): " + jp.getSignature());
		logger.info(" --- 前置通知，getSourceLocation(): " + jp.getSourceLocation());
		logger.info(" --- 前置通知，getKind(): " + jp.getKind());
		logger.info(" --- 前置通知，getStaticPart(): " + jp.getStaticPart());
		
		
		logger.info(" --- 前置通知，getSignature().getDeclaringTypeName(): " + jp.getSignature().getDeclaringTypeName());
		logger.info(" --- 前置通知，getSignature().getModifiers(): " + jp.getSignature().getModifiers());
		logger.info(" --- 前置通知，getSignature().getName(): " + jp.getSignature().getName());
		logger.info(" --- 前置通知，getSignature().getDeclaringType(): " + jp.getSignature().getDeclaringType());
		
		
		logger.info(" --- 前置通知，getTarget().getClass().getName(): " + jp.getTarget().getClass().getName());
	}
	
	@AfterReturning(pointcut = "pc2()", returning = "obj")
	public void afterReturning(JoinPoint jp, Object obj) {
		logger.info(" --- 后置通知，toString(): " + jp.toString());
		logger.info(" --- 后置通知，toShortString(): " + jp.toShortString());
		logger.info(" --- 后置通知，toLongString(): " + jp.toLongString());
		logger.info(" --- 后置通知，getThis(): " + jp.getThis().getClass());
		logger.info(" --- 后置通知，getTarget(): " + jp.getTarget());
		logger.info(" --- 后置通知，getSignature(): " + jp.getSignature());
		logger.info(" --- 后置通知，getSourceLocation(): " + jp.getSourceLocation());
		logger.info(" --- 后置通知，getKind(): " + jp.getKind());
		logger.info(" --- 后置通知，getStaticPart(): " + jp.getStaticPart());

		
		logger.info(" --- 后置通知，getSignature().getDeclaringTypeName(): " + jp.getSignature().getDeclaringTypeName());
		logger.info(" --- 后置通知，getSignature().getModifiers(): " + jp.getSignature().getModifiers());
		logger.info(" --- 后置通知，getSignature().getDeclaringType(): " + jp.getSignature().getDeclaringType());
		
		
		logger.info(" --- 后置通知，getTarget().getClass().getName(): " + jp.getTarget().getClass().getName()); // 目标类，类名
		logger.info(" --- 后置通知，getSignature().getName(): " + jp.getSignature().getName()); // 方法名
		logger.info(" --- 后置通知，getArgs(): " + JsonUtils.writeValueAsString(jp.getArgs())); // 方法名
		logger.info(" --- 后置通知，返回值：{}", JsonUtils.writeValueAsString(obj)); // 返回值
		
	}
	
}
