package com.mfzhang.mayi.common.constant;

/**
 * 
 * 
 * @author mingfei.z
 */
public class CommonConstants {

	/** 用户权限token key */
	public static final String TOKEN_KEY = "auth_token";
	
	/**
	 * rabbit 相关常量
	 * 
	 * @author mingfei.z
	 */
	public static final class Rabbit {
		/** 队列名称：test_pro_con */
		public static final String QUEUE_NAME_test_pro_con = "test_pro_con";
		
		/** 交换器名称：ex_log */
		public static final String EXCHANGE_NAME_ex_log = "ex_log";
	}
	
	/**
	 * 编码格式 常量
	 * 
	 * @author mingfei.z
	 */
	public static final class CodeFormat {
		/** 编码格式：UTF */
		public static final String FORMAT_UTF8 = "UTF-8";
	}
	
	/**
	 * rabbitmq 虚拟host
	 * 
	 * @author mingfei.z
	 */
	public static final class VHost {
		/** 编码格式：UTF */
		public static final String HOST_V_LEARN = "/v-learn";
	}
	
	
	
}
