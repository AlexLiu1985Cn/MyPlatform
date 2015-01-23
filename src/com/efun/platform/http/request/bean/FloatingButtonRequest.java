package com.efun.platform.http.request.bean;

import android.content.Context;

import com.efun.core.tools.EfunLocalUtil;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
/**
 * 悬浮按钮请求
 * @author itxuxxey
 *
 */
 
public class FloatingButtonRequest extends BaseRequestBean{
	//http://game.efuntw.com/game_openFloatButton.shtml?gameCode=tkfy&flag=platformButton&versionCode=0&plateFormOnline=11763439_0_0-11763439&payFrom=efun&roleLevel=23&isNew=1
	//http://game.efuntw.com/game_openFloatButton.shtml?gameCode=whcl&flag=platformStore&netFlag=460077684170125&versionCode=2.3.0&userId=1002176614&payFrom=efun&roleLevel=1
	private String gameCode;//游戏gameCode
	private String flag;//按钮标识
	private String versionCode;//版本名
	private String plateFormOnline;//游戏在线时长
	private String payFrom;//渠道标识
	private String roleLevel;//角色等级
	private String netFlag;//网络标识
	private String isNew;//是否是新方法
	private String userId;//用户ID
	
	public FloatingButtonRequest(Context context) {
		super();
		this.flag = HttpParam.PLATFORMBUTTON;
		this.versionCode = AppUtils.getAppVersionName(context);
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getPlateFormOnline() {
		return plateFormOnline;
	}

	public void setPlateFormOnline(String plateFormOnline) {
		this.plateFormOnline = plateFormOnline;
	}

	public String getPayFrom() {
		return payFrom;
	}

	public void setPayFrom(String payFrom) {
		this.payFrom = payFrom;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getNetFlag() {
		return netFlag;
	}

	public void setNetFlag(String netFlag) {
		this.netFlag = netFlag;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
