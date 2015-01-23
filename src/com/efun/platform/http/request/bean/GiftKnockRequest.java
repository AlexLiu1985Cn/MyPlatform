package com.efun.platform.http.request.bean;

/**
 * 好康 - 礼包 - 抢礼包
 * @author Jesse
 *
 */
public class GiftKnockRequest extends BaseRequestBean{
	private String uid;
	private String gameCode;
	private String giftType;
	private String platForm;
	private String device;
	public GiftKnockRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GiftKnockRequest(String gameCode, String giftType,
			String platForm,String device) {
		super();
		this.gameCode = gameCode;
		this.giftType = giftType;
		this.platForm = platForm;
		this.device = device;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getGiftType() {
		return giftType;
	}
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	public String getPlatForm() {
		return platForm;
	}
	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}
	
	
}
