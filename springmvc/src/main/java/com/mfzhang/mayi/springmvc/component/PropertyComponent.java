/**
 * 
 * @author mingfei.z 2018年9月18日 下午10:56:55
 */
package com.mfzhang.mayi.springmvc.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class PropertyComponent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AwareEnvironmentComponent awareEnvironmentComponent;
	@Autowired
	private Environment env;
	
	public PropertyComponent() {
		logger.info("init...");
	}
	
	public String getContent() {
		
		if (null != env)
			return env.getProperty("test.property.username", String.class);
		
		if (null != awareEnvironmentComponent)
			return awareEnvironmentComponent.getContent();
		
		return "";
	}
	
}
