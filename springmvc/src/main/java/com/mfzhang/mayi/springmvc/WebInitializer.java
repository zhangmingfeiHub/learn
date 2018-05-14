/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:57:56
 */
package com.mfzhang.mayi.springmvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 应用初始化类
 * 
 * @author mingfei.z
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 
	 * @author mingfei.z
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	/**
	 * 
	 * @author mingfei.z
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MvcConfig.class };
	}

	/**
	 * 
	 * @author mingfei.z
	 * @return
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
