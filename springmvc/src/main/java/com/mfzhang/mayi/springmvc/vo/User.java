/**
 * 
 * @author mingfei.z 2018年9月19日 下午10:56:54
 */
package com.mfzhang.mayi.springmvc.vo;

import java.io.Serializable;

/**
 * 
 * @author mingfei.z
 */
public class User implements Serializable {

	/** @author mingfei.z */
	private static final long serialVersionUID = 7345640470470400701L;
	
	private String username;
	
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
