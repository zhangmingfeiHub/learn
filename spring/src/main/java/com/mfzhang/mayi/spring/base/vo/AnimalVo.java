/**
 * 
 * @author mingfei.z 2018年4月24日 下午10:27:16
 */
package com.mfzhang.mayi.spring.base.vo;

import java.io.Serializable;

/**
 * 
 * @author mingfei.z
 */
public class AnimalVo implements Serializable {

	/** @author mingfei.z */
	private static final long serialVersionUID = -5218627298936888867L;

	private Integer id;
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
