/**
 * 
 * @author mingfei.z 2018年4月16日 下午10:52:59
 */
package com.mfzhang.mayi.spring.aop.service.impl;

import com.mfzhang.mayi.spring.aop.service.IntroduceService;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
public class IntroduceServiceImplV2 implements IntroduceService {

	/**
	 * 
	 * @author mingfei.z
	 * @param userInfoVo
	 */
	@Override
	public void add(UserInfoVo userInfoVo) {
		System.err.println(" --- add userInfo V2 --- ");
	}
	
	/**
	 * 
	 * @author mingfei.z
	 * @param userId
	 */
	@Override
	public void delete(Integer userId) {
		System.err.println(" --- delete userInfo V2 --- ");
	}

}
