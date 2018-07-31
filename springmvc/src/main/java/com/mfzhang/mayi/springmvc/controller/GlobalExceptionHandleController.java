package com.mfzhang.mayi.springmvc.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//import com.chinaredstar.home.decoration.designer.common.JsonResult;
//import com.chinaredstar.home.decoration.designer.common.StateCodeEnum;
//import com.chinaredstar.home.decoration.designer.exception.JzCustomException;

/**
 * 全局异常处理
 *
 * @author mingfei.z
 */
@ControllerAdvice
public class GlobalExceptionHandleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult<String> handleException(Exception ex) {
		logger.error("系统异常", ex);
		StackTraceElement[] stackTrace = ex.getStackTrace();
		Class<? extends Exception> class1 = ex.getClass();
		
		JsonResult<String> jsonResult = new JsonResult<>();
		jsonResult.fail(StateCodeEnum.CODE_COMMON_SYSTEM_EXCEPTION);
		return jsonResult;
	}*/
	
	/**
	 * 
	 * @param ex
	 * @return
	 * @author mingfei.z
	 */
	/*@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException(Exception ex) {
		logger.error("系统异常", ex);
		boolean hasResponseBody = hasResponseBody(ex.getStackTrace());
		if (hasResponseBody) {
			JsonResult<String> jsonResult = new JsonResult<>();
			jsonResult.fail(StateCodeEnum.CODE_COMMON_SYSTEM_EXCEPTION);
			return jsonResult;
		} else {
			return "error";
		}
		
	}*/
	
	/*private JsonResult<String> handleException2(Exception ex) {
		JsonResult<String> jsonResult = new JsonResult<>();
		jsonResult.fail(StateCodeEnum.CODE_COMMON_SYSTEM_EXCEPTION);
		return jsonResult;
	}*/
	
	/*private boolean hasResponseBody(StackTraceElement[] stackTraceElements) {
		int length = stackTraceElements.length;
		if (length > 4) {
			length = 4;
		}
		
		for (int i=0; i<length; i++) {
			if (hasResponseBody(stackTraceElements[i])) {
				return true;
			}
		}
		
		return false;
	}*/
	
	/*private boolean hasResponseBody(StackTraceElement stackTraceElement) {
		String className = stackTraceElement.getClassName();
		String methodName = stackTraceElement.getMethodName();
		
		if (className.endsWith("Controller")) {
			try {
				Class<?> controllerClass = Class.forName(className);
				Method[] methods = controllerClass.getDeclaredMethods();
				
				Method method = null;
				for (Method m : methods) {
					if (m.getName().equals(methodName)) {
						method = m;
						break;
					}
				}
				
				if (null != method) {
					ResponseBody annotation = method.getAnnotation(ResponseBody.class);
					if (null != annotation) {
						return true;
					}
				}
				
			} catch (Exception e) {
				logger.error("判断是否含有ResponseBody注解", e);
			}
		}
		
		return false;
	}*/
	
	/*@ExceptionHandler(JzCustomException.class)
	@ResponseBody
	public JsonResult<String> handleException(JzCustomException ex) {
		logger.error("自定义异常", ex);
		
		JsonResult<String> jsonResult = new JsonResult<>();
		jsonResult.fail(StateCodeEnum.CODE_COMMON_SYSTEM_EXCEPTION.getCode(), ex.getMessage());
		return jsonResult;
	}*/
	
	/*@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public JsonResult<String> handlerException(HttpMessageNotReadableException e) {
		logger.error("服务器未能理解请求!", e);
		
		JsonResult<String> jsonResult = new JsonResult<>();
		jsonResult.fail(StateCodeEnum.CODE_COMMON_BAD_REQUEST);
		return jsonResult;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
	public JsonResult<String> handlerException(HttpRequestMethodNotSupportedException e) {
		logger.error("禁止访问!", e);
		
		JsonResult<String> jsonResult = new JsonResult<>();
		jsonResult.fail(StateCodeEnum.CODE_COMMON_METHOD_NOT_ALLOWED);
		return jsonResult;
	}*/
	
}
