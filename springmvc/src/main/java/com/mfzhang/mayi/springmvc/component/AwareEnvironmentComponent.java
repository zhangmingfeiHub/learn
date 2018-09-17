/**
 * 
 * @author mingfei.z 2018年9月17日 下午11:19:16
 */
package com.mfzhang.mayi.springmvc.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class AwareEnvironmentComponent implements EnvironmentAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Environment environment;
	
	@Autowired
	private EnvironmentCapableComponent environmentCapableComponent;
	
	/**
	 * 
	 * @author mingfei.z
	 * @param environment
	 */
	@Override
	public void setEnvironment(Environment environment) {
		logger.info("设置Environment");
		this.environment = environment;
	}
	
	public String getContent() {
		Environment environment2 = environmentCapableComponent.getEnvironment();
		
		if (null == environment2)
			logger.info("获取Environment为null");
		
		String property = environment.getProperty("os.name");
		return property;
	}

}
