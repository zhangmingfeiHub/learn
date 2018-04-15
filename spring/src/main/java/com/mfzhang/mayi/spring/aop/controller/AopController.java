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
import com.mfzhang.mayi.common.util.JsonUtils;
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
	
	/**
	 * 
	 * 如果没有加 {@link ResponseBody} 注解，则SpringMVC会把请求url当做逻辑视图来解析，
	 * 如：该方法如果没有@ResponseBody 注解，则/spring/WEB-INF/view/aop/list.jsp，浏览器会报错404
	 * 
	 * @author mingfei.z
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result<List<UserInfoVo>> list() {
		return Result.success(aopService.list());
	}
	
	/**
	 * 1、jsp页面不显示model中值，解决办法：<%@ page isELIgnored="false" %>
	 * isELIgnored="true"，则JSP中的表达式被当成字符串处理；
	 * isELIgnored="false"，isELIgnored属性用来指定是否忽略EL；
	 * 
	 * @author mingfei.z
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public String get(@PathVariable("userId") Integer userId, Model model) {
		logger.info("获取用户信息，入参={}", userId);
		UserInfoVo userInfoVo = aopService.get(userId);
		model.addAttribute("userInfo", userInfoVo);
		
		logger.info("获取用户信息，入参={}，返回结果={}", userId, JsonUtils.writeValueAsString(userInfoVo));
		return "userInfoDetail";
	}
	
}
