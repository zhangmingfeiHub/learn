package com.mfzhang.mayi.rabbitmq;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 应用启动时会调onStartup方法初始化servlet容器
 * 
 * @author mingfei.z
 */
public class WebInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);
	
	@Order(value = 10)
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("============servlet container init===============");
		
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
		springContext.register(WebConfig.class, WebMvcConfig.class);
		springContext.setServletContext(servletContext);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(springContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
