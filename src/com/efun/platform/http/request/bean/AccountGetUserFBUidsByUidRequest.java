package com.efun.platform.http.request.bean;

import android.content.Context;

import com.efun.core.tools.EfunLocalUtil;
import com.efun.platform.utils.Const;
/**
 * 登陆
 * @author Jesse
 *
 */
public class AccountGetUserFBUidsByUidRequest extends BaseRequestBean{
	private String userId;
	private String gameCode;
	private boolean crossdomain;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public boolean isCrossdomain() {
		return crossdomain;
	}

	public void setCrossdomain(boolean crossdomain) {
		this.crossdomain = crossdomain;
	}

	public AccountGetUserFBUidsByUidRequest(String userId, String gameCode) {
		super();
		this.userId = userId;
		this.gameCode = gameCode;
	}

	public AccountGetUserFBUidsByUidRequest() {
		super();
	}
	
	
}
