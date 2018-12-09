/**
 * 
 * @author mingfei.z 2018年10月22日 下午10:56:36
 */
package com.mfzhang.mayi.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("/hw")
public class HelloController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/say/{st}")
	@ResponseBody
	public String say(@PathVariable String st) {
		logger.info("say {}, do something", st);
		return "say, " + st;
	}
	
}
