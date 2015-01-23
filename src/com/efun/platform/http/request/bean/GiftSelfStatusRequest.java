package com.efun.platform.http.request.bean;

import com.efun.platform.utils.Const.HttpParam;

/**
 * 客服 - 客服回复- 问题详情
 * @author Jesse
 *
 */
public class GiftSelfStatusRequest extends BaseRequestBean {
	private String fromType;
	private String uid;
	private String type;
	private String handleType;
	private String area;
	
	public GiftSelfStatusRequest(String uid,String handleType) {
		super();
		this.fromType = HttpParam.APP;
		this.uid = uid;
		this.area = HttpParam.PLATFORM_AREA;
		this.handleType = handleType;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHandleType() {
		return handleType;
	}
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	
}
