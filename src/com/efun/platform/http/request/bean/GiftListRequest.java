package com.efun.platform.http.request.bean;

import com.efun.platform.utils.GameToPlatformParamsSaveUtil;


/**
 * 好康 - 礼包 - 礼包列表
 * @author Jesse
 *
 */
public class GiftListRequest extends BaseRequestBean{
	/**
	 * 平臺標識
	 */
	private String platform;
	/**
	 * 當前请求页码
	 */
	private String currentPage;
	/**
	 * 每页显示的条数
	 */
	private String pageSize;
	/**
	 * 用戶ID
	 */
	private String uid;
	/**
	 * 当前游戏gameCode
	 */
	private String gameCode;
	
	public GiftListRequest() {
		super();
		this.gameCode = GameToPlatformParamsSaveUtil.getInstanse().getGameCode();
		// TODO Auto-generated constructor stub
	}

	public GiftListRequest(String platform, String currentPage,
			String pageSize) {
		super();
		this.platform = platform;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	

}
