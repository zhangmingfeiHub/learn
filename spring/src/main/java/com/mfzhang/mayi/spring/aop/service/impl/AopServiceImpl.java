/**
 * 
 * @author mingfei.z 2018年4月11日 上午12:21:06
 */
package com.mfzhang.mayi.spring.aop.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.enums.StateCodeEnum;
import com.mfzhang.mayi.common.util.JsonUtils;
import com.mfzhang.mayi.spring.aop.service.AopService;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
@Service
public class AopServiceImpl implements AopService {

	private static final Logger logger = LoggerFactory.getLogger(AopServiceImpl.class);
	
	/**
	 * 
	 * @author mingfei.z
	 * @return
	 */
	@Override
	public List<UserInfoVo> list() {

		System.err.println("--- service list ---");
		return createData();
	}
	
	/**
	 * 
	 * @author mingfei.z
	 * @param userId
	 * @return
	 */
	@Override
	public UserInfoVo get(Integer userId) {
		System.err.println("--- service get ---");
		List<UserInfoVo> list = createData();
		
		if (!CollectionUtils.isEmpty(list)) {
			UserInfoVo userInfoVo = list.stream().filter(u -> u.getUserId().equals(userId)).findFirst().get();
			return userInfoVo;
		}
		
		return null;
	}
	
	private List<UserInfoVo> createData() {
		List<UserInfoVo> list = new ArrayList<>();
		
		UserInfoVo u1 = new UserInfoVo();
		u1.setUserId(100);
		u1.setUserName("u1-100");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		u1.setBirth(calendar.getTime());

		UserInfoVo u2 = new UserInfoVo();
		u2.setUserId(200);
		u2.setUserName("u2-200");
		u2.setBirth(Calendar.getInstance().getTime());
		
		list.add(u1);
		list.add(u2);
		
		return list;
	}
	
	/**
	 * 
	 * @author mingfei.z
	 * @param userInfoVo
	 */
	@Override
	public void addUserInfo1(UserInfoVo userInfoVo) {
		
		logger.info("添加用户信息，入参={}", JsonUtils.writeValueAsString(userInfoVo));
		
	}
	
	/**
	 * 
	 * @author mingfei.z
	 * @param userInfoVo
	 * @param flag
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Result<Boolean> addUserInfo2(UserInfoVo userInfoVo, Integer flag) {
		if (null == flag || flag.intValue() < 0 || flag.intValue() > 1) {
			return Result.fail(StateCodeEnum.CODE_TIPS_PARAM_ERROR);
		}
		
		logger.info("添加用户信息，入参：flag={}，userInfoVo={}", flag.toString(), JsonUtils.writeValueAsString(userInfoVo));
		
		if (flag.intValue() == 0) {
			logger.info("添加用户信息，情况1");
		} else {
			logger.info("添加用户信息，情况2");
		}
		
		return Result.success(true);
	}

}
