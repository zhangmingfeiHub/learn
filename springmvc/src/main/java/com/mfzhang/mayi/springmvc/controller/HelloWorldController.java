/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:11:48
 */
package com.mfzhang.mayi.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.domain.JsonResult;

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
	
}
