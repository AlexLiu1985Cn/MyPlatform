package com.efun.platform.module.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.efun.facebook.share.activity.EfunFacebookCommonUtil;
//import com.efun.facebook.share.self.FacebookSelfPluginImpl;
//import com.efun.facebook.share.self.FacebookSelfPluginImpl.FeedCallback;
import com.efun.google.share.entrance.EfunGoogleShare;
import com.efun.google.share.entrance.EfunGoogleShare.Type;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.utils.Const.Share;

public class ShareUtils {
	
	/**
	 * google+分享
	 * @param activity
	 */
	public static void shareGoogleJia(Activity activity,String text,String shareUrl){
		try {
			EfunGoogleShare.getInstance().share(activity, text,shareUrl,Type.NORMAL);
		} catch (Exception e) {
			ToastUtils.toast(activity, E_string.efun_pd_share_googlejia_error);
		}
	}
	
	/**
	 * google+分享在onActivityResult生命周期方法中调用
	 * @param activity
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public static void shareGoogleJiaOnActivityResult(Activity activity,int requestCode, int resultCode, Intent data){
		EfunGoogleShare.getInstance().onActivityResult(activity, requestCode, resultCode, data);
	}
	
	/**
	 * line分享
	 * @param context
	 * @param paramString	分享的內容
	 */
	public static void shareLine(Context context,String paramString){
		AppUtils.openLineAPP(context,Share.SHARE_LINESHARE_URL + paramString);
//		context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://line.naver.jp/R/msg/text/?" + paramString)));
	}
	
	/**
	 * facebook分享
	 * @param context
	 * @param link
	 * @param picture
	 * @param name
	 * @param caption
	 * @param description
	 */
//	public static void shareFacebook(final Context context,String link, String picture, String name, String caption, String description,FeedCallback callBack){
////		FacebookSelfPluginImpl.getInstance().feed(link, picture, name, caption, description, callBack);		
//	}
	
	public static void shareFacebook(Context context,String link, String picture, String name, String caption, String description){
		EfunFacebookCommonUtil.efunFacebookShare((Activity) context, link, picture, name, caption, description);
	}

}
