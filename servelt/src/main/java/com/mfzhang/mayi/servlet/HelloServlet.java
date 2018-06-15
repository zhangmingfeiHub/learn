/**
 * 
 * @author mingfei.z 2018年6月15日 下午3:39:17
 */
package com.mfzhang.mayi.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfzhang.mayi.common.util.JsonUtils;

/**
 * servlet
 * 
 * 容器启动时调用该sevlet的init方法
 * 
 * @author mingfei.z
 */
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2449295885529870711L;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void init() throws ServletException {
		logger.info("===HelloServlet 初始化");
		ServletConfig servletConfig = getServletConfig();
		logger.info("===HelloServlet 初始化，servletConfig={}", JsonUtils.writeValueAsString(servletConfig));
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		logger.info("===HelloServlet service");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("===HelloServlet doGet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("===HelloServlet doPost");
		doGet(req, resp);
	}
	
}
