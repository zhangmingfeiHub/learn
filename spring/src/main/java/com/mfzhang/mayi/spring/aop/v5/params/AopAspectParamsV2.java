/**
 * 
 * @author mingfei.z 2018年4月19日 下午9:49:42
 */
package com.mfzhang.mayi.spring.aop.v5.params;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.mfzhang.mayi.common.exception.CustomException;
import com.mfzhang.mayi.common.util.JsonUtils;

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
	private HttpServletRequest request;
	
	@Autowired
	private CommonController commonController;
	
	@Pointcut("execution(* com.mfzhang.mayi.spring.aop.controller.*.*(..)) "
			+ "&& @annotation(com.mfzhang.mayi.spring.aop.controller.AuthCheck)")
	public void checkParam() {};
	
	@Before("checkParam()")
	public void doBefore(JoinPoint jp) throws CustomException {
		logger.info("---before: args={}", JsonUtils.writeValueAsString(jp.getArgs()));
		
//		jp.getSignature().getClass().get

		/*
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (req != null) {
			String tokenV2 = request.getParameter(CommonConstants.TOKEN_KEY);
			logger.info("---before: tokenV2={}", tokenV2);
		}
		*/

		
		if (null != request) {
//			String token = request.getParameter(CommonConstants.TOKEN_KEY);
			String token = null;
			Cookie[] cookies = request.getCookies();
			if (null != cookies && cookies.length > 0) {
				for (Cookie c : cookies) {
					if (CommonConstants.TOKEN_KEY.equals(c.getName())) {
						token = c.getValue();
					}
					logger.info("---cookie---{}", c.getName());
					logger.info("---cookie---{}", c.getValue());
				}
			}
			
			logger.info("---before: token={}", token);
			if (!StringUtils.isEmpty(token) && token.equals("auth_123")) {
				logger.info("---before: token={}, auth check success", token);	
			} else {
				logger.info("---before: token={}, auth check fail", token);
				throw new CustomException("auth check fail");
			}
		}
		
//		commonController.checkAuth(jp.getArgs());
	}

	@AfterReturning(value = "checkParam()", returning = "obj")
	public void doAfterReturning(JoinPoint jp, Object obj) {
		logger.info("---AfterReturning: args={}", JsonUtils.writeValueAsString(jp.getArgs()));
		logger.info("---AfterReturning: args={}, result={}", JsonUtils.writeValueAsString(jp.getArgs()),
				JsonUtils.writeValueAsString(obj));
	}
	
}
