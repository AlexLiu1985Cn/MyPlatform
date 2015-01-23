package com.efun.platform.http.request.bean;

/**
 * 发送验证码
 * @author Jesse
 *
 */
public class AccountBindPhoneSendVcodeRequest extends BaseRequestBean{
	private String phone;
	private String uid;
	private String fromType;
	public AccountBindPhoneSendVcodeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AccountBindPhoneSendVcodeRequest(String phone, String uid,
			String fromType) {
		super();
		this.phone = phone;
		this.uid = uid;
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
	public String getFromType() {
		return fromType;
	}
	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	
}
