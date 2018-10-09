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

	public static <T> JsonResult<T> success() {
		return success(null);
	}

	public static <T> JsonResult<T> success(T data) {
		return success(StateCodeEnum.CODE_COMMON_SUCCESS.getMessage(), data);
	}

	public static <T> JsonResult<T> success(String message, T data) {
		return instance(StateCodeEnum.CODE_COMMON_SUCCESS.getCode(), message, data);
	}

	public static <T> JsonResult<T> fail(StateCodeEnum stateCodeEnum) {
		return fail(stateCodeEnum.getCode(), stateCodeEnum.getMessage());
	}

	public static <T> JsonResult<T> fail(int code, String message) {
		return instance(code, message, null);
	}

	public static <T> JsonResult<T> instance(int code, String message, T data) {
		JsonResult<T> jsonResult = new JsonResult<>();
		jsonResult.setCode(code);
		jsonResult.setMessage(message);
		jsonResult.setData(data);
		
		return jsonResult;
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
