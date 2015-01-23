package com.efun.platform.http.request.bean;
/**
 * 账号绑定
 * @author Jesse
 *
 */
public class AccountBindPhoneRequest extends BaseRequestBean{
	private String phone;
	private String uid;
	private String vcode;
	private String sign;
	private String timestamp;
	private String platform;
	private String fromType;
	
	public AccountBindPhoneRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountBindPhoneRequest(String phone, String uid, String vcode,
			String sign, String timestamp, String platform, String fromType) {
		super();
		this.phone = phone;
		this.uid = uid;
		this.vcode = vcode;
		this.sign = sign;
		this.timestamp = timestamp;
		this.platform = platform;
		this.fromType = fromType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
	
}
