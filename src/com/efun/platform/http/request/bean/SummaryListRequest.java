package com.efun.platform.http.request.bean;

import com.efun.platform.utils.GameToPlatformParamsSaveUtil;

/**
 * 资讯-资讯列表
 * @author Jesse
 *
 */
public class SummaryListRequest extends BaseRequestBean{
	private String platform;
	private String currentPage;
	private String pageSize;
	private String newsType;
	private String gameCode;
	
	public SummaryListRequest() {
		super();
		// TODO Auto-generated constructor stub
		this.gameCode = GameToPlatformParamsSaveUtil.getInstanse().getGameCode();
	}
	
	public SummaryListRequest( String platform,
			String currentPage, String pageSize, String newsType) {
		super();
		this.platform = platform;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.newsType = newsType;
		this.gameCode = GameToPlatformParamsSaveUtil.getInstanse().getGameCode();
	}

	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
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
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
}
