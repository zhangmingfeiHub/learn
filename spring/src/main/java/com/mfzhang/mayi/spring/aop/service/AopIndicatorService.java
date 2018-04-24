/**
 * 
 * @author mingfei.z 2018年4月24日 下午7:07:21
 */
package com.mfzhang.mayi.spring.aop.service;

import com.mfzhang.mayi.spring.aop.vo.Student;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
public interface AopIndicatorService {

	UserInfoVo get(Integer id);
	
	void add(UserInfoVo ui);
	
	void update(UserInfoVo ui);
	
	void delete(Integer id);
	
	void add(Student stu);
	
	void update(Student stu);
	
}
