/**
 * 
 * @author mingfei.z 2018年4月19日 下午9:50:46
 */
package com.mfzhang.mayi.spring.aop.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.enums.StateCodeEnum;
import com.mfzhang.mayi.common.util.JsonUtils;
import com.mfzhang.mayi.spring.aop.vo.UserInfoEditVo;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("/aopParams")
public class AopParamController {

	private static final Logger logger = LoggerFactory.getLogger(AopParamController.class);
	
	@AuthCheck
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result<UserInfoVo> getById(@PathVariable("id") Integer userId) {
		
		if (null == userId || userId.intValue() < 1) {
			return Result.fail(StateCodeEnum.CODE_TIPS_PARAM_ERROR);
		}
		logger.info("查询，入参={}", JsonUtils.writeValueAsString(userId));
		List<UserInfoVo> list = createData();
		if (CollectionUtils.isEmpty(list)) {
			return Result.fail(StateCodeEnum.CODE_TIPS_NONE_DATA);
		}
		
		UserInfoVo userInfoVo = list.stream().filter(ui -> ui.getUserId().equals(userId)).findFirst().get();
		logger.info("查询，入参={}，结果={}", JsonUtils.writeValueAsString(userId), JsonUtils.writeValueAsString(userInfoVo));
		return Result.success(userInfoVo);
	}

	@AuthCheck
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> addUserInfo(@RequestBody UserInfoEditVo editVo) {
		
		if (null == editVo) {
			return Result.fail(StateCodeEnum.CODE_TIPS_PARAM_ERROR);
		}
		
		logger.info("添加，入参={}", JsonUtils.writeValueAsString(editVo));
		return Result.success();
	}

	@AuthCheck
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public Result<Boolean> addUserInfo(@RequestParam(value = "userId", defaultValue = "0") Integer userId,
			@RequestParam(value = "userName", defaultValue = "000") String userName) {
		
		logger.info("添加，入参={}-{}", userId.toString(), userName);
		return Result.success();
	}
	
	private List<UserInfoVo> createData() {
		List<UserInfoVo> list = new ArrayList<>();
		
		for (int i=1; i<=10; i++) {
			UserInfoVo u1 = new UserInfoVo();
			u1.setUserId(100 + i);
			u1.setUserName("name" + (100 + i));
			
			list.add(u1);
		}
		
		return list;
	}
	
}
