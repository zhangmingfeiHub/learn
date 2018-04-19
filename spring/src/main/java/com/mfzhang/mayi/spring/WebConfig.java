/**
 * 
 * @author mingfei.z 2018年4月10日 下午11:10:54
 */
package com.mfzhang.mayi.spring;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.mfzhang.mayi.spring.aop.v1.config.AopAspect;

/**
 * 1、{@link EnableAspectJAutoProxy} 启用自动代理功能
 * 
 * @author mingfei.z
 */
@Configuration
@ComponentScan(basePackageClasses = Mark.class, useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Component.class),
		@Filter(type = FilterType.ANNOTATION, value = Aspect.class)
}, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
//@EnableAspectJAutoProxy
@PropertySource("classpath:system.properties")
@ImportResource(locations = {"classpath:spring/application-context.xml"})
public class WebConfig {

	@Autowired
	private Environment env;
	
	/*@Bean
	public AopAspect aopAspect() {
		System.err.println("--- " + env.getProperty("project.env") + " ---");
		return new AopAspect();
	}*/
	
}
