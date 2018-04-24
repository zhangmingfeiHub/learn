/**
 * 
 * @author mingfei.z 2018年4月24日 下午6:53:37
 */
package com.mfzhang.mayi.spring.aop.v5.indicator;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mingfei.z
 */
@Aspect
public class AopAspectJIndicatorArgs {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("args(com.mfzhang.mayi.spring.aop.vo.UserInfoVo)")
	public void pc() {}
	
	@Around(value = "pc()")
	public Object doAround(ProceedingJoinPoint jp) {
		Object result = null;
		
		try {
			logger.info("-------around start----------------");
			result = jp.proceed();
			logger.info("-------around end----------------");
		} catch (Exception e) {
			// TODO: handle exception
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
