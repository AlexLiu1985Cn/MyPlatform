package com.efun.platform.http.request.bean;

import android.content.Context;

import com.efun.core.tools.EfunLocalUtil;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
/**
 * 登陆(内嵌平台的获取用户信息)
 * @author Jesse
 *
 */
public class AccountLoginRequest extends BaseRequestBean{
	private String uid;//用户id
	private String platform;//平台标识 tw
//	private String jsonp;//
//	private String loginType;//登陆类型 只可填以下参数 efun, fb, evart, gamer, google
	private String timestamp;//时间戳
	private String sign;//签名
	private String gameCode;
	private String fromType;
	
	public AccountLoginRequest(Context context) {
		super();
		this.platform = HttpParam.PLATFORM_AREA;
//		this.jsonp = HttpParam.JSONP;
		this.gameCode = GameToPlatformParamsSaveUtil.getInstanse().getGameCode();
		this.fromType = HttpParam.APP;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
		
}
