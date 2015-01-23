package com.efun.platform.module.utils;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.AccountLoginRequest;
import com.efun.platform.http.request.bean.FloatingButtonRequest;
import com.efun.platform.http.response.bean.AccountResponse;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.base.impl.OnToastClickListener;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.Key;

public class UserUtils {
	/**
	 * 是否已经登陆
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		if (EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse()
				.getUser().getUid())) {
			return false;
		}
		return true;
	}

	/**
	 * 登出
	 */
	public static void logout(Context context) {
		GameToPlatformParamsSaveUtil.getInstanse().setUid(null);
		GameToPlatformParamsSaveUtil.getInstanse().setUser(null);
		Store.clearByClazz(context, AccountResponse.class);
		Store.clearByClazz(context, AccountLoginRequest.class);
	}

	// /**
	// * 需要登陆方可操作
	// * @param context
	// */
	// public static void needLogin(Context context,OnLoginFinishListener
	// onLoginFinishListener){
	// if(!isLogin()){
	// GameToPlatformParamsSaveUtil.getInstanse().setOnEfunListener(onLoginFinishListener);
	// Intent it = new Intent(context,LoginActivity.class);
	// context.startActivity(it);
	// }else{
	// onLoginFinishListener.loginCompleted(false);
	// }
	// }

	// /**
	// * 需要登陆方可操作
	// * @param context
	// */
	// public static void needLogin(final Context context,String message,final
	// OnLoginFinishListener onLoginFinishListener){
	// if(!isLogin()){
	// ViewUtils.createLoginWaitingDialog(context, message, new
	// OnToastClickListener() {
	// @Override
	// public void onClick() {
	// GameToPlatformParamsSaveUtil.getInstanse().setOnEfunListener(onLoginFinishListener);
	// Intent it = new Intent(context,LoginActivity.class);
	// context.startActivity(it);
	// }
	// }, new Handler());
	// }else{
	// onLoginFinishListener.loginCompleted(false);
	// }
	// }

	// /**
	// * 需要登陆方可操作
	// * @param context
	// */
	// public static void changeUser(Context context,OnLoginFinishListener
	// onLoginFinishListener){
	// GameToPlatformParamsSaveUtil.getInstanse().setOnEfunListener(onLoginFinishListener);
	// Intent it = new Intent(context,LoginActivity.class);
	// Store.saveHistoryLogin(context, new String[]{"username","password"}, new
	// String[]{"",""});
	// context.startActivity(it);
	// }

	// /**
	// * Tab中需要登陆方可操作
	// * @param context
	// */
	// public static void needLoginInTag(Context context,int requestCode,int
	// resultCode,OnLoginFinishListener onLoginFinishListener){
	// if(!isLogin()){
	// GameToPlatformParamsSaveUtil.getInstanse().setOnEfunListener(onLoginFinishListener);
	// Intent it = new Intent(context,LoginActivity.class);
	// Bundle bundle = new Bundle();
	// bundle.putBoolean(Key.BOOLEAN_KEY, true);
	// bundle.putInt(Key.INTEGER_KEY, resultCode);
	// it.putExtra(Const.DATA_KEY, bundle);
	// ((Activity)context).startActivityForResult(it, requestCode);
	// }else{
	// onLoginFinishListener.loginCompleted(false);
	// }
	// }

	/**
	 * 
	 * @param context
	 */
	public static void initUser(Context context) {
		String date = Store.getResponseByClazz(context, AccountResponse.class);
		if (!EfunStringUtil.isEmpty(date)) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(date);
				User userInfoBean = new User();
				userInfoBean
						.setAccessToken(jsonObject.optString("accessToken"));
				userInfoBean
						.setAccountName(jsonObject.optString("accountName"));
				userInfoBean.setAddress(jsonObject.optString("address"));
				userInfoBean.setAreaDesc(jsonObject.optString("areaDesc"));
				userInfoBean.setAuth_code(jsonObject.optString("auth_code"));
				userInfoBean.setAuthed(jsonObject.optString("authed"));
				userInfoBean.setCreatedTime(jsonObject.optLong("createdTime"));
				userInfoBean.setCurrentExp(jsonObject.optInt("currentExp"));
				userInfoBean.setEmail(jsonObject.optString("email"));
				userInfoBean.setExpPercentage(jsonObject
						.optInt("expPercentage"));
				userInfoBean.setGameCode(jsonObject.optString("gameCode"));
				userInfoBean.setGoldValue(jsonObject.optInt("goldValue"));
				userInfoBean.setGotGold(jsonObject.optString("gotGold"));
				userInfoBean.setIcon(jsonObject.optString("icon"));
				userInfoBean.setIp(jsonObject.optString("ip"));
				userInfoBean.setIsAccept(jsonObject.optString("isAccept"));
				userInfoBean.setIsVip(jsonObject.optString("isVip"));
				userInfoBean.setLevelupExp(jsonObject.optInt("levelupExp"));
				userInfoBean.setLoginType(jsonObject.optString("loginType"));
				userInfoBean.setMemberLevel(jsonObject.optInt("memberLevel"));
				userInfoBean
						.setModifiedTime(jsonObject.optLong("modifiedTime"));
				userInfoBean.setPassword(jsonObject.optString("password"));
				userInfoBean.setPrivilege(jsonObject.optString("privilege"));
				userInfoBean.setRango(jsonObject.optString("rango"));
				userInfoBean.setRangoLevel(jsonObject.optString("rangoLevel"));
				userInfoBean.setSex(jsonObject.optString("sex"));
				userInfoBean.setSign(jsonObject.optString("sign"));
				userInfoBean.setThirdId(jsonObject.optString("thirdId"));
				userInfoBean.setTimestamp(jsonObject.optString("timestamp"));
				userInfoBean.setUid(jsonObject.optLong("uid"));
				userInfoBean.setUrlParamString(jsonObject
						.optString("urlParamString"));
				userInfoBean.setUsername(jsonObject.optString("username"));
				userInfoBean.setPhone(jsonObject.optString("phone"));
				userInfoBean
						.setIsAuthPhone(jsonObject.optString("isAuthPhone"));
				GameToPlatformParamsSaveUtil.getInstanse()
						.setUser(userInfoBean);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上次登录信息请求类
	 * 
	 * @param context
	 * @return
	 */
	public static AccountLoginRequest createRequest(Context context) {
		// String[] etagKeys = new String[]{"uid","timestamp","sign"};
		// HashMap<String, String > paramsMap =
		// Store.createValuesByClazz(context, etagKeys,
		// AccountLoginRequest.class);
		// AccountLoginRequest request = new AccountLoginRequest(context);
		// request.setUid(paramsMap.get("uid"));
		// request.setTimestamp(paramsMap.get("timestamp"));
		// request.setSign(paramsMap.get("sign"));
		// request.setReqType(IPlatformRequest.REQ_ACCOUNT_LOGIN);
		AccountLoginRequest request = new AccountLoginRequest(context);
		request.setUid(GameToPlatformParamsSaveUtil.getInstanse().getUid());
		request.setTimestamp(GameToPlatformParamsSaveUtil.getInstanse()
				.getTimestamp());
		request.setSign(GameToPlatformParamsSaveUtil.getInstanse().getSign());
		request.setReqType(IPlatformRequest.REQ_ACCOUNT_LOGIN);
		return request;
	}

	public static FloatingButtonRequest createFloatBtnRequest(Context context) {
		TelephonyManager mTelephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String netFlag = mTelephonyManager.getSubscriberId();

		if (TextUtils.isEmpty(netFlag)) {
			netFlag = "111111111";
		}
		// 渠道包名
		String payFrom;
		try {
			payFrom = context.getResources().getString(
					E_string.efunChannelPayForm);
			if (EfunStringUtil.isEmpty(payFrom)) {
				payFrom = "efun";
			}
		} catch (Exception e) {
			payFrom = "efun";
		}
		FloatingButtonRequest request = new FloatingButtonRequest(context);
		request.setGameCode(GameToPlatformParamsSaveUtil.getInstanse().getGameCode());
		request.setIsNew(HttpParam.ISNEW);
		request.setNetFlag(netFlag);
		request.setPayFrom(payFrom);
		request.setPlateFormOnline(GameToPlatformParamsSaveUtil.getInstanse().getPlateFormOnline());
		request.setRoleLevel(GameToPlatformParamsSaveUtil.getInstanse().getRoleLevel());
		request.setUserId(GameToPlatformParamsSaveUtil.getInstanse().getUid());
		request.setReqType(IPlatformRequest.REQ_FLOAT_BTN);
		return request;
	}

}
