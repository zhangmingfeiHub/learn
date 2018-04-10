/**
 * 
 * @author mingfei.z 2018年4月10日 下午10:24:53
 */
package com.mfzhang.mayi.spring;

import javax.servlet.ServletContainerInitializer;

import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * 1、servlet3.0 容器（如tomcat）启动后会，容器会在类路径下查找实现了 {@link ServletContainerInitializer}接口的类；
 * 2、spring提供了一个实现{@link SpringServletContainerInitializer}；
 * 3、spring提供的实现又会去查找{@link WebApplicationInitializer} 接口的实现类；
 * 4、spring引入了{@link WebApplicationInitializer} 接口的基础实现 {@link AbstractAnnotationConfigDispatcherServletInitializer}；
 * 5、{@link AbstractDispatcherServletInitializer} 对应于:
 * public class MyWebAppInitializer implements WebApplicationInitializer {
 *
 *    &#064;Override
 *    public void onStartup(ServletContext container) {
 *      XmlWebApplicationContext appContext = new XmlWebApplicationContext();
 *      appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
 *
 *      ServletRegistration.Dynamic dispatcher =
 *        container.addServlet("dispatcher", new DispatcherServlet(appContext));
 *      dispatcher.setLoadOnStartup(1);
 *      dispatcher.addMapping("/");
 *    }
 *
 * }
 * 6、{@link AbstractAnnotationConfigDispatcherServletInitializer} 对应于：
 * public class MyWebAppInitializer implements WebApplicationInitializer {
 *
 *    &#064;Override
 *    public void onStartup(ServletContext container) {
 *      // Create the 'root' Spring application context
 *      AnnotationConfigWebApplicationContext rootContext =
 *        new AnnotationConfigWebApplicationContext();
 *      rootContext.register(AppConfig.class);
 *
 *      // Manage the lifecycle of the root application context
 *      container.addListener(new ContextLoaderListener(rootContext));
 *
 *      // Create the dispatcher servlet's Spring application context
 *      AnnotationConfigWebApplicationContext dispatcherContext =
 *        new AnnotationConfigWebApplicationContext();
 *      dispatcherContext.register(DispatcherConfig.class);
 *
 *      // Register and map the dispatcher servlet
 *      ServletRegistration.Dynamic dispatcher =
 *        container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
 *      dispatcher.setLoadOnStartup(1);
 *      dispatcher.addMapping("/");
 *    }
 *
 * }
 * 7、{@link ContextLoaderListener} 该监听器的作用是启动web容器时（如tomcat），自动加载装配ApplicationContext（应用上下文，根上下文）的配置信息；
 * 8、{@link DispatcherServlet} 创建的是子上下文；
 * 9、spring自身存在父子上下文的概念，首先要了解什么是上下文，可以理解为bean的容器，
 * ContextLoaderListener创建根上下文（父上下文），DispatcherServlet创建子上下文，子上下文可以访问父上下文中的bean,反之，则不行。
 * 一般情况下controller相关的bean或者对应的注解扫描配置在这个子上下文中，而service、dao的bean或者对应的注解扫描配置在根上下文中；
 * 
 * @author mingfei.z
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 从根上下文配置类加载配置，供{@link ContextLoaderListener}用
	 * @author mingfei.z
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/**
	 * 子上下文，{@link DispatcherServlet} 创建的上下文
	 * @author mingfei.z
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	/**
	 * {@link DispatcherServlet} 的映射
	 * @author mingfei.z
	 * @return
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{ "/" };
	}

}
