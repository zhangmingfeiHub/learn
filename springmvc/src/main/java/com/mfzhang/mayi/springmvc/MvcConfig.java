/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:36:41
 */
package com.mfzhang.mayi.springmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.context.annotation.ComponentScan.Filter;

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
public class MvcConfig extends WebMvcConfigurationSupport {

	
	
}
