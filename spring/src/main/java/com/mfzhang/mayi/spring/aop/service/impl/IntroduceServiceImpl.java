/**
 * 
 * @author mingfei.z 2018年4月16日 下午7:42:19
 */
package com.mfzhang.mayi.spring.aop.service.impl;

import org.springframework.stereotype.Service;

import com.mfzhang.mayi.spring.aop.service.IntroduceService;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
@Service
public class IntroduceServiceImpl implements IntroduceService {

	@Override
	public void add(UserInfoVo userInfoVo) {
		System.err.println(" --- add userInfo --- ");
	}

}
