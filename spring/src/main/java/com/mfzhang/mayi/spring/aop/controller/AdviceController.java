/**
 * 
 * @author mingfei.z 2018年4月19日 下午11:51:18
 */
package com.mfzhang.mayi.spring.aop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.enums.StateCodeEnum;

/**
 * 
 * @author mingfei.z
 */
@ControllerAdvice
public class AdviceController {

	public static final Logger logger = LoggerFactory.getLogger(AdviceController.class);
	
	@ExceptionHandler(Exception.class)
	public Result<Object> handleException(Exception e) {
		logger.info("exception: {}", e);
		
		return Result.fail(StateCodeEnum.CODE_COMMON_SYSTEM_ERROR);
	}
	
}
