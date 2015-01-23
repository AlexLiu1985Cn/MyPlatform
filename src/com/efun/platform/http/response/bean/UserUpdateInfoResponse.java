package com.efun.platform.http.response.bean;

import org.json.JSONObject;

import com.efun.platform.module.person.bean.UserUpdateBean;
/**
 * 玩家 - 更新玩家信息
 * @author Jesse
 *
 */
public class UserUpdateInfoResponse extends BaseResponseBean{
	private static final long serialVersionUID = 1L;
	/**
	 * 更新玩家信息 {@link UserUpdateBean}
	 */
	private UserUpdateBean mResponse ;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject= (JSONObject) object;
		mResponse = new UserUpdateBean();
		mResponse.setCode(jsonObject.optString("code"));
	}
	public UserUpdateBean getUserUpdateBean() {
		return mResponse;
	}
}
