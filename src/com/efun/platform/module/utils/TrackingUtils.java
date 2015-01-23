package com.efun.platform.module.utils;

import android.app.Activity;
import android.content.Context;

import com.efun.ads.bean.AdsHttpParams;
import com.efun.ads.call.EfunAdsPlatform;
import com.efun.ads.call.EfunGoogleAnalytics;
import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;

public class TrackingUtils {
	private static final String EVENT = "独立APP";
	
	public static final String ACTION_TAB="底部按钮-Tab";
	public static final String ACTION_BANNER="Banner";
	public static final String ACTION_GAME="游戏-Game";
	public static final String ACTION_WELFARE = "好康-Welfare";
	public static final String ACTION_ACCOUNT = "账户-Account";
	public static final String ACTION_WELFARE_ACTIVITY = "好康-活动-Activity";
	public static final String ACTION_APP = "APP";
	public static final String ACTION_EXTENSION = "领取点卡奖励";
	public static final String ACTION_GIFT = "礼包中心";
	public static final String ACTION_GIFT_DETAIL = "礼包详情";
	public static final String ACTION_CS = "客服";
	
	public static final String NAME_TAB_SUMMARY="资讯";
	public static final String NAME_TAB_GAME="游戏";
	public static final String NAME_TAB_WELFARE="好康";
	public static final String NAME_TAB_SELF="我";
	public static final String NAME_TAB_CS="客服";
	
	public static final String NAME_BANNER_SUMMARY="首页Banner";
	
	public static final String NAME_GAME_DOWNLOAD="下载";
	public static final String NAME_GAME_UPDATE="更新";
	public static final String NAME_GAME_START="启动";
	
	public static final String NAME_APP_INSTALLED="安裝";
	public static final String NAME_APP_START="启动";
	public static final String NAME_APP_START_UNIQUE="启动_排重";
	public static final String NAME_APP_SCAN="扫描";
	public static final String NAME_APP_SETTING="设置";
	
	public static final String NAME_GIFT_SELF_CENTER="我的序列号中心";
	
	
	
	public static final String NAME_WELFARE_EXTENSION_GET_REWARD="领取奖励";
	
	public static final String NAME_WELFARE_EXTENSION="领取免费点数";
	public static final String NAME_WELFARE_ACTIVITY="活动";
	public static final String NAME_WELFARE_GIFT="礼包中心";
	public static final String NAME_WELFARE_KNOCK_EGG="砸蛋";
	
	public static final String NAME_ACCOUNT_BIND_PHONE ="绑定手机";
	
	public static final String NAME_CS_ASK ="我要提问";
	public static final String NAME_CS_QUESTION ="常见问题";
	public static final String NAME_CS_REPLY ="客服回复";
	
	public static void init(Context context){
		EfunGoogleAnalytics.startNewSession(context, context.getString(E_string.efun_pd_tracking_id));
	}
	/**
	 * 谷歌分析追踪
	 * @param action
	 * @param name
	 */
	public static void track(String action,String name){
		EfunGoogleAnalytics.trackEvent(EVENT, action, name);
	}
	/**
	 * 一段时间追踪一次
	 * @param context
	 * @param etagKeys
	 * @param etagValues
	 * @param action
	 * @param name
	 * @param betweenTime
	 * @param clazz
	 */
	public static void trackSingle(Context context,String[] etagKeys,String[] etagValues,String action,String name,long betweenTime,Class<? extends Object> clazz){
		String oldTime = Store.getSimpleInfoByClazz(context, etagKeys[0], clazz);
		if(!EfunStringUtil.isEmpty(oldTime)){			
			if(System.currentTimeMillis() - Long.parseLong(oldTime) > betweenTime){
				Store.saveSimpleInfoByClazz(context, etagKeys, etagValues, clazz);
				TrackingUtils.track(action, name);
			}
		}else{
			Store.saveSimpleInfoByClazz(context, etagKeys, etagValues, clazz);
			TrackingUtils.track(action, name);
		}	
	}
	/**
	 * 一段时间追踪一下启动数（排重作用）
	 * @param context
	 * @param action
	 * @param name
	 * @param betweenTime
	 * @param clazz
	 */
	public static void trackSingle(Context context,String action,String name,long betweenTime,Class<? extends Object> clazz){
		String[] etagKeys = new String[]{"USER_START_APP"};
		String[] etagValues = new String[]{System.currentTimeMillis()+""};
		String oldTime = Store.getSimpleInfoByClazz(context, etagKeys[0], clazz);
		if(!EfunStringUtil.isEmpty(oldTime)){			
			if(System.currentTimeMillis() - Long.parseLong(oldTime) > betweenTime){
				Store.saveSimpleInfoByClazz(context, etagKeys, etagValues, clazz);
				TrackingUtils.track(action, name);
			}
		}else{
			Store.saveSimpleInfoByClazz(context, etagKeys, etagValues, clazz);
			TrackingUtils.track(action, name);
		}	
	}
	
	public static void destory(){
		EfunGoogleAnalytics.stopSession();
	}
	
	public static void initAds(Activity activity){
		AdsHttpParams params = new AdsHttpParams();
		params.setPreferredUrl(GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.ADS_PRE_KEY,activity));
		params.setSpareUrl(GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.ADS_SPA_KEY,activity));
		params.setAdvertiser(GameToPlatformParamsSaveUtil.getInstanse().getAdvertiser());
		params.setPartner(GameToPlatformParamsSaveUtil.getInstanse().getPartner());
		params.setGameCode(HttpParam.PLATFORM_CODE);//gameCode
		params.setAppKey(HttpParam.PLATFORM_APP_KEY);//秘钥
		params.setAppPlatform(HttpParam.PLATFORM_APP_PLATFORM);//平台标识
		EfunAdsPlatform.initEfunAdsS2S(activity, params, true);
	}
	
}
