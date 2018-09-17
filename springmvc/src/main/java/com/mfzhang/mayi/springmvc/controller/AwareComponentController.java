/**
 * 
 * @author mingfei.z 2018年9月17日 下午10:59:07
 */
package com.mfzhang.mayi.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.springmvc.component.AwareComponent;
import com.mfzhang.mayi.springmvc.component.AwareEnvironmentComponent;

/**
 * 
 * @author mingfei.z
 */
@RestController
@RequestMapping("/aware")
public class AwareComponentController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private AwareComponent awareComponent;
	@Autowired
	private AwareEnvironmentComponent awareEnvironmentComponent;
	
//	@RequestMapping("/appContext")
//	public Result<String> awareAppContext() {
//		
//		String content = awareComponent.getContent();
//		logger.info("applicationName: " + content);
//		
//		return Result.success(content);
//	}

	@RequestMapping("/awareEnvironment")
	public Result<String> awareEnvironment() {
		
		String content = awareEnvironmentComponent.getContent();
		logger.info("awareEnvironment content: " + content);
		
		return Result.success(content);
	}
	
}
