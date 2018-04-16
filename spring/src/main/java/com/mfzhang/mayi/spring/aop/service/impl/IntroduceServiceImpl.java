/**
 * 
 * @author mingfei.z 2018年4月16日 下午7:42:19
 */
package com.mfzhang.mayi.spring.aop.service.impl;

import com.mfzhang.mayi.spring.aop.service.IntroduceService;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
public class IntroduceServiceImpl implements IntroduceService {

	@Override
	public void add(UserInfoVo userInfoVo) {
		System.err.println(" --- add userInfo --- ");
	}
	
	/**
	 * 
	 * @author mingfei.z
	 * @param userId
	 */
	@Override
	public void delete(Integer userId) {
		System.err.println(" --- delete userInfo --- ");
	}

}
