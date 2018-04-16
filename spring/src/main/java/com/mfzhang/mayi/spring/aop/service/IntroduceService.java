/**
 * 
 * @author mingfei.z 2018年4月16日 下午7:41:10
 */
package com.mfzhang.mayi.spring.aop.service;

import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
public interface IntroduceService {

	void add(UserInfoVo userInfoVo);
	
	void delete(Integer userId);
	
}
