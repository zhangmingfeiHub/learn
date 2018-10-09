/**
 * 
 * @author mingfei.z 2018年10月2日 上午11:29:48
 */
package com.mfzhang.mayi.springmvc.component;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

/**
 * 自定义HandlerMapping 处理器映射器
 * @author mingfei.z
 */
public class CustomHandlerMapping implements HandlerMapping {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		
		logger.info("===自定义HandlerMapping 处理器映射器===");
		String url = request.getRequestURI().toString();
		String contextPath = request.getContextPath();
		if (url.startsWith(contextPath + "/hw/custom-mapping"))
		{
			
		}
		
		return null;
	}

}
