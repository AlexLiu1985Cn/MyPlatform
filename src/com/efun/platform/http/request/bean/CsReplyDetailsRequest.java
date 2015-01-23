package com.efun.platform.http.request.bean;
/**
 * 客服 - 客服回复- 问题详情
 * @author Jesse
 *
 */
public class CsReplyDetailsRequest extends BaseRequestBean {
	private String tgppid;
	private String uid;

	public CsReplyDetailsRequest(String tgppid) {
		super();
		this.tgppid = tgppid;
	}

	public String getTgppid() {
		return tgppid;
	}

	public void setTgppid(String tgppid) {
		this.tgppid = tgppid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
