/**
 * 
 * @author mingfei.z 2018年9月17日 下午10:55:10
 */
package com.mfzhang.mayi.springmvc.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class AwareComponent implements ApplicationContextAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ApplicationContext applicationContext;
	
	/**
	 * 
	 * 
	 * @author mingfei.z
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("设置applicationContext");
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public String getContent() {
		String applicationName = applicationContext.getApplicationName();
		return applicationName;
	}

}
