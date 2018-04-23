/**
 * 
 * @author mingfei.z 2018年4月23日 下午7:44:36
 */
package com.mfzhang.mayi.spring.base;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1107803603971933721L;
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
