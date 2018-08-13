/**
 * 
 * @author mingfei.z 2018年8月13日 下午11:41:09
 */
package com.mfzhang.mayi.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.dubbo.service.DubboProviderService;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("/demo")
public class ConsumerController {

	@Autowired
	private DubboProviderService dubboProviderService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return dubboProviderService.say("hello");
	}
	
}
