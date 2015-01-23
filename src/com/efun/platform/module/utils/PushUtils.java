package com.efun.platform.module.utils;

import android.content.Context;

import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;

public class PushUtils {
	
//	public static void initPushWhenStart(Context context){
//		if(Store.installed(context)){
//			EfunPushManager.getInstance().saveInstallTime(context,System.currentTimeMillis());
//		}
//		String  preUrl = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.GAME_PRE_KEY,context);
//		String  spareUrl = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.GAME_SPA_KEY,context);
//		EfunPushManager.getInstance().setGameCode(context, HttpParam.PLATFORM_CODE);
//		EfunPushManager.getInstance().setPreUrl(context, preUrl);
//		EfunPushManager.getInstance().setSpareUrl(context, spareUrl);
//		EfunPushManager.getInstance().setNotifitionIcon(context,"efun_pd_app_name");
//		EfunPushManager.getInstance().setPushTimer(context);
//	}
//	
//	public static void initPushWhenLog(Context context){
//		EfunPushManager.getInstance().saveLastLoginTime(context, System.currentTimeMillis());
//	}
}
