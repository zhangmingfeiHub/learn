/**
 * 
 * @author mingfei.z 2018年3月12日 下午11:24:12
 */
package com.mfzhang.mayi.common;

import com.mfzhang.mayi.common.enums.StateCodeEnum;

/**
 * 
 * @author mingfei.z
 */
public class Result<T> {

	private boolean ok;
	
	private int code;
	
	private String message;
	
	private T data;

	public Result(boolean ok, int code, String message, T data) {
		this.ok = ok;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public Result() {}
	
	public static <T> Result<T> success() {
		return success(StateCodeEnum.CODE_COMMON_SUCCESS);
	}

	public static <T> Result<T> success(StateCodeEnum stateCodeEnum) {
		return success(stateCodeEnum, null);
	}

	public static <T> Result<T> success(T data) {
		return success(StateCodeEnum.CODE_COMMON_SUCCESS, data);
	}

	public static <T> Result<T> success(StateCodeEnum stateCodeEnum, T data) {
		return new Result<T>(true, stateCodeEnum.getCode(), stateCodeEnum.getMessage(), data);
	}

	public static <T> Result<T> fail() {
		return fail(StateCodeEnum.CODE_COMMON_SUCCESS);
	}

	public static <T> Result<T> fail(StateCodeEnum stateCodeEnum) {
		return fail(stateCodeEnum, null);
	}

	public static <T> Result<T> fail(StateCodeEnum stateCodeEnum, T data) {
		return new Result<T>(false, stateCodeEnum.getCode(), stateCodeEnum.getMessage(), data);
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

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the ok
	 */
	public boolean isOk() {
		return ok;
	}

	/**
	 * @param ok the ok to set
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
