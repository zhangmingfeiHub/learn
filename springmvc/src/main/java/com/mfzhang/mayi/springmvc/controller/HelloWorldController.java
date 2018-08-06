/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:11:48
 */
package com.mfzhang.mayi.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.domain.JsonResult;
import com.mfzhang.mayi.common.exception.CustomException;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("hw")
public class HelloWorldController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "say", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> sayHello(@RequestParam("sth") String sth) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		int i = 1/0;
		
		jsonResult.success("hello, " + sth);
		return jsonResult;
	}

	@RequestMapping(value = "hot", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> hot(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			int i = 1/a;
		} catch (Exception e) {
			logger.error("hot 时异常", e);
			throw new CustomException(e.getMessage(), "hot 时异常");
		}
		
		jsonResult.success("hello, it's very hot.");
		return jsonResult;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "错了哦")
	@RequestMapping(value = "cool", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> cool(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			int i = 1/a;
		} catch (Exception e) {
			logger.error("cool 时异常", e);
			throw new CustomException(e.getMessage(), "cool 时异常");
		}
		
		jsonResult.success("hello, it's very cool.");
		return jsonResult;
	}
	
	
}
