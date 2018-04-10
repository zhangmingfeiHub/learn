/**
 * 
 * @author mingfei.z 2018年4月11日 上午12:15:26
 */
package com.mfzhang.mayi.spring.aop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.spring.aop.service.AopService;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
@RequestMapping("/aop")
@Controller
public class AopController {

	private static final Logger logger = LoggerFactory.getLogger(AopController.class);
	
	@Autowired
	private AopService aopService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result<List<UserInfoVo>> list() {
		return Result.success(aopService.list());
	}
	
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public String get(@PathVariable("userId") Integer userId, Model model) {
		model.addAttribute("userInfo", aopService.get(userId));
		
		return "userInfoDetail";
	}
	
}
