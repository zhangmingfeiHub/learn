/**
 * 
 * @author mingfei.z 2018年4月10日 下午11:12:32
 */
package com.mfzhang.mayi.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 解析{@link EnableWebMvc}、{@link WebMvcConfigurationSupport}、 {@link WebMvcConfigurerAdapter}
 * 1、使用@EnableWebMvc 等于 继承WebMvcConfigurationSupport，但不能重写父类方法；
 * 2、继承WebMvcConfigurationSupport 可以不用再使用注解@EnableWebMvc，可根据需要重写父类方法；
 * 3、WebMvcConfigurerAdapter 已经在5.0版本作废掉了，WebMvcConfigurerAdapter是个抽象类，WebMvcConfigurationSupport 比
 * WebMvcConfigurerAdapter 支持的可自定义的配置更多更全；
 * @author mingfei.z
 */
@Configuration
@ComponentScan(basePackageClasses = Mark.class, useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
@EnableAspectJAutoProxy
// @EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * 配置视图解析器
	 * @author mingfei.z
	 * @param registry
	 */
	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		// TODO 待研究
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setViewClass(JstlView.class);
		registry.viewResolver(viewResolver);
	}
	
	/**
	 * 1、配置静态资源路由给容器（如tomcat）处理，不再由{@link DispatcherServlet} 处理
	 * 2、设置由 web容器处理静态资源 ，相当于 xml中的<mvc:default-servlet-handler/>
	 * @author mingfei.z
	 * @param configurer
	 */
	@Override
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * 资源访问处理器
	 * 可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容
	 * @author mingfei.z
	 * @param registry
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
	}
	
}
