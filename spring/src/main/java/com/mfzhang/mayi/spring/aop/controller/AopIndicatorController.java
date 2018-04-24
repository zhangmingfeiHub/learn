/**
 * 
 * @author mingfei.z 2018年4月24日 下午7:05:00
 */
package com.mfzhang.mayi.spring.aop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.spring.aop.service.AopIndicatorService;
import com.mfzhang.mayi.spring.aop.vo.Student;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
@RequestMapping("/aopIndicator")
@Controller
public class AopIndicatorController {

	private final Logger Logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AopIndicatorService aopIndicatorService;
	
	@RequestMapping("/get/{id}")
	@ResponseBody
	public Result<UserInfoVo> get(@PathVariable Integer id) {
		UserInfoVo vo = aopIndicatorService.get(id);
		
		return Result.success(vo);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(@RequestBody UserInfoVo vo) {
		aopIndicatorService.add(vo);
		
		return Result.success();
	}

	@RequestMapping(value = "/addStu", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(@RequestBody Student stu) {
		aopIndicatorService.add(stu);
		
		return Result.success();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> update(@RequestBody UserInfoVo vo) {
		aopIndicatorService.update(vo);
		
		return Result.success();
	}

	@RequestMapping(value = "/updateStu", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> update(@RequestBody Student stu) {
		aopIndicatorService.update(stu);
		
		return Result.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result<Boolean> delete(@PathVariable Integer id) {
		aopIndicatorService.delete(id);
		
		return Result.success();
	}
	
}
