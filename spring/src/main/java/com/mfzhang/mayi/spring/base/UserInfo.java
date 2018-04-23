/**
 * 
 * @author mingfei.z 2018年4月23日 下午7:37:56
 */
package com.mfzhang.mayi.spring.base;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class UserInfo implements Serializable, BeanNameAware, BeanFactoryAware, ApplicationContextAware,
		BeanPostProcessor, InitializingBean, DisposableBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Address address;
	
	public UserInfo() {
		logger.info("UserInfo constructor, address: {}" , address);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6981093055944503745L;

	@Override
	public void destroy() throws Exception {
		logger.info("UserInfo initialize method: {}", "destroy()");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}-{}", "postProcessBeforeInitialization()", bean, beanName);
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("UserInfo initialize method: {}", "afterPropertiesSet()");
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}-{}", "postProcessAfterInitialization()", bean, beanName);
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}", "setApplicationContext()", applicationContext);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}", "setBeanFactory()", beanFactory);
	}

	@Override
	public void setBeanName(String name) {
		logger.info("UserInfo initialize method: {}，param：{}", "setBeanName()", name);
	}

}
