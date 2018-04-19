/**
 * 
 * @author mingfei.z 2018年4月19日 下午10:01:15
 */
package com.mfzhang.mayi.spring.aop.vo;

import java.io.Serializable;

/**
 * 
 * @author mingfei.z
 */
public class UserInfoEditVo implements Serializable {

	/** @author mingfei.z */
	private static final long serialVersionUID = 3738910318796919091L;

	private Integer userId;
	private String userName;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
