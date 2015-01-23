package com.efun.platform.http.request.bean;

import android.content.Context;

import com.efun.core.tools.EfunLocalUtil;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
/**
 * 注册
 * @author Jesse
 *
 */
public class AccountRegisterRequest extends BaseRequestBean{
	private String userName;
	private String password;
	private String email;
	private String area;
	private String device;
	private String timestamp;
	private String advertiser;
	private String mac;
	private String imei;
	private String androidid;
	private String ip;
	private String systemVersion;
	private String deviceType;
	private String partner;
	private String language;
	private String region;
	
	public AccountRegisterRequest(Context context, String userName, String password, String email) {
		super();
		
		this.timestamp = System.currentTimeMillis()+"";
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.advertiser = GameToPlatformParamsSaveUtil.getInstanse().getAdvertiser();
		this.mac = EfunLocalUtil.getLocalMacAddress(context);
		this.imei = EfunLocalUtil.getLocalImeiAddress(context);
		this.androidid = EfunLocalUtil.getLocalAndroidId(context);
		this.ip = EfunLocalUtil.getLocalIpAddress(context);
		this.systemVersion = EfunLocalUtil.getOsVersion();
		this.deviceType = EfunLocalUtil.getDeviceType();
		this.language = Const.HttpParam.LANGUAGE;
		this.region = Const.HttpParam.REGION;
		this.partner = GameToPlatformParamsSaveUtil.getInstanse().getPartner();
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}	
}
	
