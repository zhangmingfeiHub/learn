/**
 * 
 * @author mingfei.z 2018年10月2日 下午3:28:59
 */
package com.mfzhang.mayi.springmvc.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.LastModified;

import com.mfzhang.mayi.springmvc.controller.CustomDispatcherServletComponentController;

/**
 * 
 * @author mingfei.z
 */
public class CustomHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		Object handlerBean = null;
		if (handler instanceof HandlerMethod) {
			handlerBean = ((HandlerMethod) handler).getBean();
		}
		
		return (null == handlerBean ? handler : handlerBean) instanceof CustomDispatcherServletComponentController;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String contextPath = request.getContextPath();
		String url = request.getRequestURI().toString();
		
		Object handlerBean = null;
		if (handler instanceof HandlerMethod) {
			handlerBean = ((HandlerMethod) handler).getBean();
		}
		
		if (url.startsWith(contextPath + "/custom/handler-mapping")) {
			return ((CustomDispatcherServletComponentController) (null == handlerBean ? handler : handlerBean)).customHM();
		} else if (url.startsWith(contextPath + "/custom/handler-adapter")) {
			return ((CustomDispatcherServletComponentController) (null == handlerBean ? handler : handlerBean)).customHA();
		} else {
			return ((Controller) handler).handleRequest(request, response); 
		}
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		
		if (handler instanceof LastModified)
			return ((LastModified) handler).getLastModified(request);
		
		return -1L;
	}

}
