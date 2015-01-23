package com.efun.platform.http.request.bean;
/**
 * 客服 - 客服回复- 问题列表
 * @author Jesse
 *
 */
public class CsReplyQuestionListRequest extends BaseRequestBean {
	private String fromType;
	private String uid;
	private String platform;
	private int currentPage;
	private String pageSize;

	public CsReplyQuestionListRequest(String fromType, String platform, int currentPage, String pageSize) {
		super();
		this.fromType = fromType;
		this.platform = platform;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
