/**
 * 
 * @author mingfei.z 2018年4月19日 下午11:51:18
 */
package com.mfzhang.mayi.spring.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.enums.StateCodeEnum;
import com.mfzhang.mayi.common.exception.CustomException;

/**
 * 
 * @author mingfei.z
 */
@ControllerAdvice
public class AdviceController {

	public static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public Result<Object> handleException(CustomException e) {
		logger.error("exception: {}-{}", e.getMessage(), e.getExMsg());
		
		String message = e.getMessage() + (StringUtils.isEmpty(e.getExMsg()) ? "" : "-" + e.getExMsg());
		return Result.fail(StateCodeEnum.CODE_COMMON_SYSTEM_ERROR, message);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<Object> handleException(Exception e) {
		logger.error("exception: {}", e);
		
		return Result.fail(StateCodeEnum.CODE_COMMON_SYSTEM_ERROR);
	}

}
