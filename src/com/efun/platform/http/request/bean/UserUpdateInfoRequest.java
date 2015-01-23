package com.efun.platform.http.request.bean;

import com.efun.platform.utils.Const.HttpParam;

/**
 * 玩家 - 更新玩家信息
 * @author Jesse
 *
 */
public class UserUpdateInfoRequest extends BaseRequestBean{
	private String uid;
	private String icon;
	private String username;
	private String sex;
	private String address;
	private String areaDesc;
	private String fromType;
	public UserUpdateInfoRequest() {
		super();
	}
	public UserUpdateInfoRequest(String uid, String icon, String username,
			String sex, String address) {
		super();
		this.uid = uid;
		this.icon = icon;
		this.username = username;
		this.sex = sex;
		this.address = address;
		this.areaDesc = HttpParam.PLATFORM_AREA;
		this.fromType = HttpParam.APP;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	public String getFromType() {
		return fromType;
	}
	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
	
	
}
