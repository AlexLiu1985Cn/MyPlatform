package com.efun.platform.http.request.bean;


/**
 * 好康 - 活动列表
 * @author Jesse
 *
 */
public class ActivityListRequest extends BaseRequestBean{
	/**
	 * 当前页面
	 */
	private String currentPage;
	/**
	 * 每页数目
	 */
	private String pageSize;
	/**
	 * 是否为储值活动(必选)
	 */
	private String isPayactivity;
	
	public ActivityListRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ActivityListRequest(String currentPage, String pageSize, String isPayactivity) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.isPayactivity = isPayactivity;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getIsPayactivity() {
		return isPayactivity;
	}
	public void setIsPayactivity(String isPayactivity) {
		this.isPayactivity = isPayactivity;
	}

}
