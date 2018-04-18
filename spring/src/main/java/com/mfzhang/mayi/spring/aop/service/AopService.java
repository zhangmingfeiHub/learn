/**
 * 
 * @author mingfei.z 2018年4月11日 上午12:20:36
 */
package com.mfzhang.mayi.spring.aop.service;

import java.util.List;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
public interface AopService {

	List<UserInfoVo> list();
	
	UserInfoVo get(Integer userId);
	
	void addUserInfo(UserInfoVo userInfoVo);
	
	Result<Boolean> addUserInfo(UserInfoVo userInfoVo, Integer flag);
	
}
