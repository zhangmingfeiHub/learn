/**
 * 
 * @author mingfei.z 2018年9月17日 下午11:28:07
 */
package com.mfzhang.mayi.springmvc.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class EnvironmentCapableComponent implements EnvironmentCapable {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * @author mingfei.z
	 * @return
	 */
	@Override
	public Environment getEnvironment() {
		logger.info("获取Environment");
		return null;
	}

}
