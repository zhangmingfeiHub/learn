/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:36:41
 */
package com.mfzhang.mayi.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.mfzhang.mayi.springmvc.component.PropertyComponent;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;

/**
 * 
 * Web上下文（子上下文）配置类
 * 
 * @author mingfei.z
 */
@Configuration
@ComponentScan(basePackageClasses = Mark.class, useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
//@EnableWebMvc
@PropertySources(value = {
		@PropertySource(value = "classpath:system.properties")
})
public class MvcConfig extends WebMvcConfigurationSupport {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment env;
	
	/*@Bean
	public PropertyComponent propertyComponent() {
		Integer age = env.getProperty("test.property.age", Integer.class);
		logger.info("age: " + age);
		return new PropertyComponent();
	}*/
	
}
