package com.mainiway.cloudcut.common.beans;

import java.io.Serializable;

public class BaseParm implements Serializable{
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 用户登录token
	 */
	private String accessToken;
	/**
	 * 当前页，从1开始
	 */
	private Integer currentPage = 1;
	/**
	 * 每页条数
	 */
	private Integer pageSize = 10;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "BaseVo [version=" + version + ", accessToken=" + accessToken
				+ "]";
	} 
	
	
}
