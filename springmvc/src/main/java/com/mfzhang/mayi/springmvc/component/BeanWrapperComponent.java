/**
 * 
 * @author mingfei.z 2018年9月19日 下午10:55:09
 */
package com.mfzhang.mayi.springmvc.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

import com.mfzhang.mayi.springmvc.vo.User;

/**
 * 
 * @author mingfei.z
 */
@Component
public class BeanWrapperComponent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public User queryUserInfoOne(int type) {
		
		User user = new User();
		
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);
		beanWrapper.setPropertyValue("username", "xiao.zhang");
		beanWrapper.setPropertyValue("password", "123456");
		
		if (type == 1)
			return user;
		
		beanWrapper.setPropertyValue("username", "xiao.zhang2");
		beanWrapper.setPropertyValue("password", "1234562");
		
		return user;
	}
	
}
