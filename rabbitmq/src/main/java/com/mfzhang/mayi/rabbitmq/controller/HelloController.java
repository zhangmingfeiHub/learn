/**
 * 
 * @author mingfei.z 2018年3月12日 下午11:22:01
 */
package com.mfzhang.mayi.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping("/say")
	@ResponseBody
	public Result<String> sayHello() {
		
		for (int i=0; i<20; i++) {
			String message = "spring rabbitmq: " + (i+1);
			rabbitTemplate.convertAndSend(message);
		}
		
		return Result.success("hello springmvc");
	}
	
}
