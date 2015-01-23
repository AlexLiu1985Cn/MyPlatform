package com.efun.platform.http.request.bean;


/**
 * 好康 - 领取免费点数 - 选礼包
 * @author Jesse
 *
 */
public class ActivityExtensionGiftRequest extends BaseRequestBean{
	private String uid;
	private String device;
	private String area;
	private String gameCode;
	private String goodsid;
	private String taskCode;
	
	public ActivityExtensionGiftRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ActivityExtensionGiftRequest( String device, String area,
			String gameCode, String goodsid, String taskCode) {
		super();
		this.device = device;
		this.area = area;
		this.gameCode = gameCode;
		this.goodsid = goodsid;
		this.taskCode = taskCode;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
}
