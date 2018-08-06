/**
 * 
 * @author mingfei.z 2018年7月31日 下午6:52:32
 */
package com.mfzhang.mayi.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mfzhang.mayi.common.domain.JsonResult;
import com.mfzhang.mayi.common.enums.StateCodeEnum;
import com.mfzhang.mayi.common.exception.CustomException;

/**
 * 
 * @author mingfei.z
 */
@ControllerAdvice
public class ExceptionHandlerController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult<String> handlerException(Exception e) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		logger.info("系统异常", e);
		
		jsonResult.fail(StateCodeEnum.CODE_COMMON_SYSTEM_ERROR);
		return jsonResult;
	}
	
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "")
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public JsonResult<String> handlerException(HttpRequestMethodNotSupportedException e) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		logger.info("Method Not Allowed", e);
		
		jsonResult.fail(StateCodeEnum.CODE_COMMON_METHOD_NOT_ALLOWED);
		return jsonResult;
	}

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public JsonResult<String> handlerException(CustomException e) {
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		logger.info(e.getExMsg() + ": " + e.getMessage(), e);
		
		jsonResult.fail(StateCodeEnum.CODE_COMMON_SYSTEM_ERROR.getCode(), e.getExMsg() + ": " + e.getMessage());
		return jsonResult;
	}
	
}
