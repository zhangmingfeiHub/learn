/**
 * 
 * @author mingfei.z 2018年4月11日 上午12:19:02
 */
package com.mfzhang.mayi.spring.aop.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mingfei.z
 */
public class UserInfoVo implements Serializable {

	/** @author mingfei.z */
	private static final long serialVersionUID = -1760193211676983104L;

	private Integer userId;
	private String userName;
	private Date birth;

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

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * @param birth
	 *            the birth to set
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

}
