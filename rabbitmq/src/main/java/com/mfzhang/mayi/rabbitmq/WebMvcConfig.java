/**
 * 
 * @author mingfei.z 2018年3月12日 下午11:08:38
 */
package com.mfzhang.mayi.rabbitmq;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * SpringMVC 配置
 * @author mingfei.z
 */
@Configuration
@ComponentScan(
		basePackageClasses = Mark.class, 
		useDefaultFilters = false, 
		// excludeFilters = @Filter(type = FilterType.ANNOTATION, value = Component.class),
		includeFilters = @Filter(Controller.class))
public class WebMvcConfig extends WebMvcConfigurationSupport {


	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
	
	/**
	 * 设置由 web容器处理静态资源 ，相当于 xml中的<mvc:default-servlet-handler/>
	 */
	@Override
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * 资源访问处理器
	 * 可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
	}
	
	
}
