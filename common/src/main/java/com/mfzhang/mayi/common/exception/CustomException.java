/**
 * 
 * @author mingfei.z 2018年4月22日 上午8:34:44
 */
package com.mfzhang.mayi.common.exception;

/**
 * 自定义异常类
 * @author mingfei.z
 */
public class CustomException extends RuntimeException {

	/** @author mingfei.z */
	private static final long serialVersionUID = -1978533630618674198L;
	/** 扩展异常类信息 */
	private String exMsg;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, String exMsg) {
		super(message);
		this.exMsg = exMsg;
	}

	/**
	 * @return the exMsg
	 */
	public String getExMsg() {
		return exMsg;
	}

	/**
	 * @param exMsg the exMsg to set
	 */
	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}
	
}
