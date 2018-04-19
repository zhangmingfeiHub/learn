/**
 * 
 * @author mingfei.z 2018年4月19日 下午9:49:42
 */
package com.mfzhang.mayi.spring.aop.v5.params;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mfzhang.mayi.common.util.JsonUtils;
import com.mfzhang.mayi.spring.aop.controller.CommonController;

/**
 * 
 * 
 * 
 * @author mingfei.z
 */
@Aspect
public class AopAspectParamsV2 {

	private static final Logger logger = LoggerFactory.getLogger(AopAspectParamsV2.class);
	
	@Autowired
	private CommonController commonController;
	
	@Pointcut("execution(* com.mfzhang.mayi.spring.aop.controller.*.*(..)) "
			+ "&& @annotation(com.mfzhang.mayi.spring.aop.controller.AuthCheck)")
	public void checkParam() {};
	
	@Before("checkParam()")
	public void doBefore(JoinPoint jp) throws IllegalArgumentException, IllegalAccessException {
		logger.info("---before: args={}", JsonUtils.writeValueAsString(jp.getArgs()));
		
		jp.getSignature().getClass().get
		
		commonController.checkAuth(jp.getArgs());
	}

	@AfterReturning(value = "checkParam()", returning = "obj")
	public void doAfterReturning(JoinPoint jp, Object obj) {
		logger.info("---AfterReturning: args={}", JsonUtils.writeValueAsString(jp.getArgs()));
		logger.info("---AfterReturning: args={}, result={}", JsonUtils.writeValueAsString(jp.getArgs()),
				JsonUtils.writeValueAsString(obj));
	}
	
}
