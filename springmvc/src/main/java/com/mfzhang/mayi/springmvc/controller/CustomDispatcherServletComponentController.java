/**
 * 
 * @author mingfei.z 2018年10月2日 下午3:29:52
 */
package com.mfzhang.mayi.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("/custom")
public class CustomDispatcherServletComponentController {

	/**
	 * 测试自定义 处理器映射器
	 * 
	 * @return
	 * @author mingfei.z
	 */
	@RequestMapping("/handler-mapping")
	public ModelAndView customHM() {
		
		ModelAndView mv = new ModelAndView("customHM");
		return mv;
	}

	/**
	 * 测试自定义 处理器映射器
	 * 
	 * @return
	 * @author mingfei.z
	 */
	@RequestMapping("/handler-adapter")
	public ModelAndView customHA() {
		
		ModelAndView mv = new ModelAndView("customHA");
		return mv;
	}
	
}
