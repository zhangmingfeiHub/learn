/**
 * 
 * @author mingfei.z 2018年3月12日 下午11:22:01
 */
package com.mfzhang.mayi.rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("hello")
public class HelloController {

	@RequestMapping("/say")
	@ResponseBody
	public Result<String> sayHello() {
		return Result.success("hello springmvc");
	}
	
}
