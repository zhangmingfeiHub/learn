/**
 * 
 * @author mingfei.z 2018年7月31日 下午6:55:43
 */
package com.mfzhang.mayi.common.domain;

import java.io.Serializable;

import com.mfzhang.mayi.common.enums.StateCodeEnum;

/**
 * 
 * @author mingfei.z
 */
public class JsonResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 785900130752592387L;

	private int code;
	
	private String message;
	
	private T data;

	public void success() {
		success(null);
	}

	public void success(T data) {
		success(StateCodeEnum.CODE_COMMON_SUCCESS.getMessage(), data);
	}

	public void success(String message, T data) {
		instance(StateCodeEnum.CODE_COMMON_SUCCESS.getCode(), message, data);
	}

	public void fail(StateCodeEnum stateCodeEnum) {
		fail(stateCodeEnum.getCode(), stateCodeEnum.getMessage());
	}

	public void fail(int code, String message) {
		instance(code, message, null);
	}

	public void instance(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
