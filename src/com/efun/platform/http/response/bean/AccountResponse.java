package com.efun.platform.http.response.bean;

import org.json.JSONObject;

import android.text.TextUtils;

import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.AccountLoginRequest;
import com.efun.platform.http.request.bean.AccountRegisterRequest;
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.utils.PushUtils;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.LoginPlatform;
/**
 * 账号
 * @author Jesse
 *
 */
public class AccountResponse extends BaseResponseBean {
	private static final long serialVersionUID = 1L;
	/**
	 * 玩家信息 {@link User}
	 */
	private User userInfoBean;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject = (JSONObject) object;
		userInfoBean = new User();
		userInfoBean.setCode(jsonObject.optString("code"));
		userInfoBean.setMessage(jsonObject.optString("message"));
		if (jsonObject.has("uid")) {
			userInfoBean.setAccessToken(jsonObject.optString("accessToken"));
			userInfoBean.setAccountName(jsonObject.optString("accountName"));
			userInfoBean.setAddress(jsonObject.optString("address"));
			userInfoBean.setAreaDesc(jsonObject.optString("areaDesc"));
			userInfoBean.setAuth_code(jsonObject.optString("auth_code"));
			userInfoBean.setAuthed(jsonObject.optString("authed"));
			userInfoBean.setCreatedTime(jsonObject.optLong("createdTime"));
			userInfoBean.setCurrentExp(jsonObject.optInt("currentExp"));
			userInfoBean.setEmail(jsonObject.optString("email"));
			userInfoBean.setExpPercentage(jsonObject.optInt("expPercentage"));
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
			userInfoBean.setModifiedTime(jsonObject.optLong("modifiedTime"));
			userInfoBean.setPassword(jsonObject.optString("password"));
			userInfoBean.setPrivilege(jsonObject.optString("privilege"));
			userInfoBean.setRango(jsonObject.optString("rango"));
			userInfoBean.setRangoLevel(jsonObject.optString("rangoLevel"));
			userInfoBean.setSex(jsonObject.optString("sex"));
			userInfoBean.setSign(jsonObject.optString("sign"));
			userInfoBean.setThirdId(jsonObject.optString("thirdId"));
			userInfoBean.setTimestamp(jsonObject.optString("timestamp"));
			userInfoBean.setUid(jsonObject.optLong("uid"));
			userInfoBean.setUrlParamString(jsonObject.optString("urlParamString"));
			userInfoBean.setUsername(jsonObject.optString("username"));
			userInfoBean.setPhone(jsonObject.optString("phone"));
			userInfoBean.setIsAuthPhone(jsonObject.optString("isAuthPhone"));
			
//			int reqType = getRequestBean().getReqType();
//			if(reqType==IPlatformRequest.REQ_ACCOUNT_LOGIN
//					|| reqType==IPlatformRequest.REQ_ACCOUNT_REGISTER){
//				
//				PushUtils.initPushWhenLog(getContext());
//				
//				String mUid = "";
//				String mUsername= "";
//				String mPassword= "";
//				String mLoginType= "";
//				String mThirdId= "";
//				String mApps = "";
//				if(reqType==IPlatformRequest.REQ_ACCOUNT_LOGIN){
//					AccountLoginRequest bean = (AccountLoginRequest)getRequestBean();
//					mUid = userInfoBean.getUid();					
//					if(TextUtils.isEmpty(bean.getUsername())){
//						mUsername = "";
//					}else{						
//						mUsername= bean.getUsername().trim();
//					}
//					if(TextUtils.isEmpty(bean.getPassword())){
//						mPassword = "";
//					}else{						
//						mPassword= bean.getPassword().trim();
//					}
//					mLoginType= GameToPlatformParamsSaveUtil.getInstanse().getLoginType();
//					mThirdId= bean.getUid();
//					mApps = bean.getApps();
//				}else{
//					AccountRegisterRequest bean = (AccountRegisterRequest)getRequestBean();
//					mUid = userInfoBean.getUid();
//					mUsername= bean.getUserName().trim();
//					mPassword= bean.getPassword().trim();
//					mLoginType= LoginPlatform.EFUN;
//					mThirdId= "";
//					mApps = "";
//				}
//				
//				if(mLoginType.equals(LoginPlatform.EFUN)){
//					Store.saveHistoryLogin(getContext(), new String[]{"username","password"}, new String[]{mUsername,mPassword});
//				}
//				
//				String[] etagKeys = new String[]{"uid"};
//				String[] etagValues = new String[]{mUid};
//				boolean flag = Store.changeNotify(getContext(),
//						etagKeys,etagValues,AccountResponse.class);
//				if(flag){
//					Store.saveResponseByClazz(getContext(), AccountResponse.class,getResponseJson2String());
//					etagKeys = new String[]{"uid","username","password","loginType","thirdId","apps"};
//					etagValues = new String[]{mUid,mUsername,mPassword,mLoginType,mThirdId,mApps};
//					Store.saveRequestByClazz(getContext(),
//							etagKeys, 
//							etagValues, 
//							AccountLoginRequest.class);
//				}
//			}
		}
	}
	

	public ResultBean getResultBean() {
		return userInfoBean;
	}

}
