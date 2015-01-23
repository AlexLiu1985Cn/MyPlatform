package com.efun.platform.utils;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;

import com.efun.core.tools.EfunLogUtil;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.base.impl.OnEfunListener;
import com.efun.platform.module.utils.UrlUtils;
import com.efun.platform.status.IPlatStatus;

public class GameToPlatformParamsSaveUtil {
	
	private static GameToPlatformParamsSaveUtil instanse;
	private String uid;//用户id
	private String userName;//用户名
	private String gameCode;//游戏编码
	private String serverCode;//服务器编码
	private String roleId;//角色ID
	private String efunRole;//角色名
	private String roleLevel;//角色等级
	private String remark;//拓展参数
	private String creditId;//储值时的唯一标识
	private String sign;//登录签名
	private String timestamp;//登录时间戳
	private String loginType;//登錄類型
	private User user;
	private ArrayList<Activity> activitys;
	private int TabType;//导航栏类型
	private boolean rechargeBtnFlag;//储值按钮标识
	private String plateFormOnline;//在线时长
	
	/**
	 * 状态
	 */
	private IPlatStatus status;
	
	private String advertiser = "efunapp$$efunapp_twap";
	private String partner = "efun";
	

	private GameToPlatformParamsSaveUtil(){
		activitys = new ArrayList<Activity>();
		rechargeBtnFlag = false;
	}

	public static GameToPlatformParamsSaveUtil  getInstanse(){		
		if(instanse == null){
			instanse = new GameToPlatformParamsSaveUtil();
		}
		return instanse;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getEfunRole() {
		return efunRole;
	}

	public void setEfunRole(String efunRole) {
		this.efunRole = efunRole;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public int getTabType() {
		return TabType;
	}

	public void setTabType(int tabType) {
		TabType = tabType;
	}

	public boolean isRechargeBtnFlag() {
		return rechargeBtnFlag;
	}

	public void setRechargeBtnFlag(boolean rechargeBtnFlag) {
		this.rechargeBtnFlag = rechargeBtnFlag;
	}



	private OnEfunListener mOnEfunListener;
	public OnEfunListener getOnEfunListener() {
		return mOnEfunListener;
	}
	public void setOnEfunListener(OnEfunListener onEfunListener) {
		this.mOnEfunListener = onEfunListener;
	}
	
	private HashMap<String, String> iPlatUrlMaps = new HashMap<String, String>();
	public String getIPlatUrlByKey(String key,Context context) {
		String url = iPlatUrlMaps.get(key)==null?"":iPlatUrlMaps.get(key);
		return UrlUtils.checkUrl(context, key, url);
	}
	public void setIPlatUrlMaps(HashMap<String, String> iPlatUrlMaps) {
		this.iPlatUrlMaps = iPlatUrlMaps;
	}
	
	public String getAdvertiser() {
		return advertiser;
	}
	public void setAdvertiser(String advertiser) {
		this.advertiser = advertiser;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}

	public IPlatStatus getIPlatStatus() {
		return status;
	}
	public void setIPlatStatus(IPlatStatus status) {
		this.status = status;
	}
	
	private boolean newStatusOfGiftSelf;
	public boolean isNewStatusOfGiftSelf() {
		return newStatusOfGiftSelf;
	}

	public void setNewStatusOfGiftSelf(boolean newStatusOfGiftSelf) {
		this.newStatusOfGiftSelf = newStatusOfGiftSelf;
	}
	
	public void addActivity(Activity activity){
		EfunLogUtil.logE("add------>activity:"+activity.toString());
		activitys.add(activity);
	}
	
	public String getPlateFormOnline() {
		return plateFormOnline;
	}

	public void setPlateFormOnline(String plateFormOnline) {
		this.plateFormOnline = plateFormOnline;
	}

	public void removeActivity(Activity activity){
		if(activitys.size() != 0){
			for(int i = 0 ; i < activitys.size(); i++){
				if(activity.equals(activitys.get(i))){
					EfunLogUtil.logE("remove------>activity:"+activity.toString());
					EfunLogUtil.logE("list.size():"+activitys.size());
					activitys.remove(i);
				}
			}					
		}
	}

	public ArrayList<Activity> getActivitys() {
		return activitys;
	}
	
}
