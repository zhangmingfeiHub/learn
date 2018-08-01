/**
 * 
 * @author mingfei.z 2018年8月2日 上午5:35:55
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

import com.mfzhang.mayi.common.domain.JsonResult;
import com.mfzhang.mayi.common.exception.CustomException;

/**
 * @ResponseStatus 注解放在方法上：
 * 一、接口正常响应情况
 * 1、不指定value、reason属性，则浏览器显示响应header 中 status code 默认是 HttpStatus.INTERNAL_SERVER_ERROR，虽接口可以正常返回数据；
 * 2、只指定value属性并赋值，则浏览器显示响应header 中 status code 是指定的value属性值，虽接口可以正常返回数据；
 * 3、指定value、reason属性，并只赋值value属性值，则浏览器显示响应header 中 status code 是指定的value属性值，虽接口可以正常返回数据；
 * 
 * 4、指定value、reason属性，并只赋值value、reason属性值，则浏览器显示响应header 中 status code 是指定的value属性值，页面显示的不友好的指定的value属性的错误页面；
 * 4、指定value、reason属性，并只赋值value、reason属性值，并不和@ResponseBody注解一起使用，则浏览器显示响应header 中 status code 是指定的value属性值，页面显示的不友好的指定的value属性的错误页面；
 * 
 * 二、接口内部发生异常情况
 * 1、此情况则走异常处理方法
 * 
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("rs")
public class ResponseStatusTestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "case1", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case1(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case1 exception", e);
			throw new CustomException(e.getMessage(), "case1 exception");
		}
		
		jsonResult.success("hello, case1");
		return jsonResult;
	}
	
	@ResponseStatus
	@RequestMapping(value = "case2", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case2(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		int i = 1/a;
		
		jsonResult.success("hello, case2");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@RequestMapping(value = "case3", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case3(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		int i = 1/a;
		
		jsonResult.success("hello, case3");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "")
	@RequestMapping(value = "case4", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case4(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		int i = 1/a;
		
		jsonResult.success("hello, case4");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "你的请求错了哦case5")
	@RequestMapping(value = "case5", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case5(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		int i = 1/a;
		
		jsonResult.success("hello, case5");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "你的请求错了哦case6")
	@RequestMapping(value = "case6", method = RequestMethod.GET)
	public JsonResult<String> case6(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		int i = 1/a;
		
		jsonResult.success("hello, case6");
		return jsonResult;
	}
	
	@ResponseStatus
	@RequestMapping(value = "case7", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case7(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case7 exception={}", e);
			throw new CustomException(e.getMessage(), "case7 exception");
		}
		
		jsonResult.success("hello, case7");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
	@RequestMapping(value = "case8", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case8(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case8 exception={}", e);
			throw new CustomException(e.getMessage(), "case8 exception");
		}
		
		jsonResult.success("hello, case8");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT, reason = "")
	@RequestMapping(value = "case9", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case9(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case9 exception={}", e);
			throw new CustomException(e.getMessage(), "case9 exception");
		}
		
		jsonResult.success("hello, case9");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT, reason = "")
	@RequestMapping(value = "case10", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case10(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case10 exception={}", e);
			throw new CustomException(e.getMessage(), "case10 exception");
		}
		
		jsonResult.success("hello, case10");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT, reason = "过时了case11")
	@RequestMapping(value = "case11", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> case11(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case11 exception={}", e);
			throw new CustomException(e.getMessage(), "case11 exception");
		}
		
		jsonResult.success("hello, case11");
		return jsonResult;
	}

	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT, reason = "过时了case12")
	@RequestMapping(value = "case12", method = RequestMethod.GET)
	public JsonResult<String> case12(Integer a) {
		JsonResult<String> jsonResult = new JsonResult<>();
		
		try {
			
			int i = 1/a;
		} catch (Exception e) {
			logger.error("case12 exception={}", e);
			throw new CustomException(e.getMessage(), "case12 exception");
		}
		
		jsonResult.success("hello, case12");
		return jsonResult;
	}
	
}
