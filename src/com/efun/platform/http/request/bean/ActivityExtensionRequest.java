package com.efun.platform.http.request.bean;


/**
 * 好康 - 领取免费点数
 * @author Jesse
 *
 */
public class ActivityExtensionRequest extends BaseRequestBean{
	private String uid;
	private String device;
	private String area;
	public ActivityExtensionRequest() {
		super();
	}
	public ActivityExtensionRequest(String device, String area) {
		super();
		this.device = device;
		this.area = area;
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
	
}
