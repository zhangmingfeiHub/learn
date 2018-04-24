/**
 * 
 * @author mingfei.z 2018年4月24日 下午7:07:59
 */
package com.mfzhang.mayi.spring.aop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfzhang.mayi.common.util.JsonUtils;
import com.mfzhang.mayi.spring.aop.service.AopIndicatorService;
import com.mfzhang.mayi.spring.aop.vo.Student;
import com.mfzhang.mayi.spring.aop.vo.UserInfoVo;

/**
 * 
 * @author mingfei.z
 */
@Service
public class AopIndicatorServiceImpl implements AopIndicatorService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public UserInfoVo get(Integer id) {
		
		List<UserInfoVo> list = createData();
		UserInfoVo vo = list.stream().filter(ui -> ui.getUserId().equals(id)).findFirst().get();
		
		logger.info("AopIndicatorService get, param={}, result={}", id.toString(), JsonUtils.writeValueAsString(vo));
		return vo;
	}
	
	private List<UserInfoVo> createData() {
		List<UserInfoVo> list = new ArrayList<>();
		
		for (int i=1; i<=20; i++) {
			UserInfoVo ui = new UserInfoVo();
			ui.setUserId(i);
			ui.setUserName("name-" + (i+1));
			
			list.add(ui);
		}
		
		return list;
	}

	@Override
	public void add(UserInfoVo ui) {
		
		logger.info("AopIndicatorService add, param={}", JsonUtils.writeValueAsString(ui));
		
	}

	@Override
	public void update(UserInfoVo ui) {
		logger.info("AopIndicatorService update, param={}", JsonUtils.writeValueAsString(ui));
	}

	@Override
	public void delete(Integer id) {
		logger.info("AopIndicatorService delete, param={}", id);
	}
	
	@Override
	public void add(Student stu) {
		logger.info("AopIndicatorService add student, param={}", JsonUtils.writeValueAsString(stu));
	}
	
	@Override
	public void update(Student stu) {
		logger.info("AopIndicatorService update student, param={}", JsonUtils.writeValueAsString(stu));
	}

}
