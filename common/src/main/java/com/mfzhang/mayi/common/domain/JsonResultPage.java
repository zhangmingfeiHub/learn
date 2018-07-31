/**
 * 
 * @author mingfei.z 2018年7月31日 下午6:57:15
 */
package com.mfzhang.mayi.common.domain;

import java.io.Serializable;

/**
 * 
 * @author mingfei.z
 */
public class JsonResultPage<T> extends JsonResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4031736731273813950L;
	
	private int pageNo = 1; // 页码
	
	private int pageSize = 10; // 每页记录数
	
	private int total; // 总记录数
	
	private int pageCount; // 总页数
	
	public JsonResultPage() { }

	public JsonResultPage(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}
	
	public JsonResultPage(int pageNo, int pageSize, int total) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		
		calculate();
	}
	
	private void calculate() {
		if (this.total < 1) 
			this.pageCount = 0;
		
		if ((total % pageSize) == 0) 
			this.pageCount = this.total / this.pageSize;
		else {
			this.pageCount = (this.total / this.pageSize) + 1;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取每页第一条记录下标
	 * 
	 * @return
	 * @author mingfei.z
	 */
	public int getStartIndex() {
		return (pageNo - 1) * pageSize;
	}
	
}
