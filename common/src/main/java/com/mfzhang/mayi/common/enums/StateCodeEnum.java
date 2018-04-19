/**
 * 
 * @author mingfei.z 2018年3月12日 下午11:28:35
 */
package com.mfzhang.mayi.common.enums;

/**
 * 
 * @author mingfei.z
 */
public enum StateCodeEnum {

	CODE_COMMON_SUCCESS(200, "成功"),
	
	CODE_COMMON_SYSTEM_ERROR(500, "系统错误"),
	
	CODE_TIPS_PARAM_ERROR(6001, "参数错误"),
	CODE_TIPS_NONE_DATA(6001, "未查询到数据");
	
	private int code;
	private String message;
	
	StateCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
