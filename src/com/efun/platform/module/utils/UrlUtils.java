package com.efun.platform.module.utils;

import java.util.HashMap;

import android.content.Context;

import com.efun.core.task.EfunCommandCallBack;
import com.efun.core.task.command.abstracts.EfunCommand;
import com.efun.core.tools.EfunStringUtil;
import com.efun.core.url.EfunDynamicUrl;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;

public class UrlUtils {
	public static final String PLATFORM_PRE_KEY = "efunPlatformPreferredUrl";
	public static final String PLATFORM_PRE_WEB_KEY = "efunPlatformWebPreferredUrl";
	public static final String GAME_PRE_KEY = "efunGamePreferredUrl";
	public static final String GAME_SPA_KEY = "efunGameSpareUrl";
	public static final String FB_PRE_KEY = "efunFbPreferredUrl";
	public static final String FB_SPA_KEY = "efunFbSpareUrl";
	public static final String ADS_PRE_KEY = "efunAdsPreferredUrl";
	public static final String ADS_SPA_KEY = "efunAdsSpareUrl";
	public static final String LOGIN_PRE_KEY = "efunLoginPreferredUrl";
	public static final String LOGIN_SPA_KEY = "efunLoginSpareUrl";
	public static final String PAY_PRE_KEY = "efunPayPreferredUrl";
	public static final String PAY_SPA_KEY = "efunPaySpareUrl";
	
	public static void initUrl(final Context context){
		final String[] urlKey = new String[]{
				PLATFORM_PRE_KEY,
				PLATFORM_PRE_WEB_KEY,
				GAME_PRE_KEY,
				GAME_SPA_KEY,
				FB_PRE_KEY,
				FB_SPA_KEY,
				ADS_PRE_KEY,
				ADS_SPA_KEY,
				LOGIN_PRE_KEY,
				LOGIN_SPA_KEY,
				PAY_PRE_KEY,
				PAY_SPA_KEY};
		final String[] defaultValues = new String[]{
				context.getString(E_string.efun_pd_url_base),
				context.getString(E_string.efun_pd_url_web_base),
				context.getString(E_string.efun_pd_url_game_base),
				context.getString(E_string.efun_pd_url_game_base_spa),
				context.getString(E_string.efun_pd_url_fb_base),
				context.getString(E_string.efun_pd_url_fb_base_spa),
				context.getString(E_string.efun_pd_url_ads_base),
				context.getString(E_string.efun_pd_url_ads_base_spa),
				context.getString(E_string.efun_pd_url_login_base),
				context.getString(E_string.efun_pd_url_login_base_spa),
				context.getString(E_string.efun_pd_url_pay_base),
				context.getString(E_string.efun_pd_url_pay_base_spa)};
		EfunDynamicUrl.initDynamicUrl(context,
				context.getString(E_string.efun_pd_sdk_download_efunVersionCode),
				context.getString(E_string.efun_pd_sdk_downloadtw_efunVersionCode),
				context.getString(E_string.efun_pd_sdk_download_efunDomainInventory),
				context.getString(E_string.efun_pd_sdk_downloadtw_efunDomainInventory),
				new EfunCommandCallBack() {
					@Override
					public void cmdCallBack(EfunCommand arg0) {
						String[] urls = EfunDynamicUrl.getDynamicUrls(context, urlKey, defaultValues);
						HashMap<String, String> urlMaps = new HashMap<String, String>();
						for (int i = 0; i < defaultValues.length; i++) {
							urlMaps.put(urlKey[i], urls[i]);
						}
						GameToPlatformParamsSaveUtil.getInstanse().setIPlatUrlMaps(urlMaps);
					}
				});
	}
	
	public static String checkUrl(Context context,String key,String url){
		if(EfunStringUtil.isEmpty(key)){
			throw new NullPointerException("UrlUtils:checkUrl 的 key 是null");
		}
		if(EfunStringUtil.isEmpty(url)){
			if(key.equals(PLATFORM_PRE_KEY)){
				return context.getString(E_string.efun_pd_url_base);
			}else if(key.equals(PLATFORM_PRE_WEB_KEY)){
				return context.getString(E_string.efun_pd_url_web_base);
			}else if(key.equals(GAME_PRE_KEY)){
				return context.getString(E_string.efun_pd_url_game_base);
			}else if(key.equals(GAME_SPA_KEY)){
				return context.getString(E_string.efun_pd_url_game_base_spa);
			}else if(key.equals(FB_PRE_KEY)){
				return context.getString(E_string.efun_pd_url_fb_base);
			}else if(key.equals(FB_SPA_KEY)){
				return context.getString(E_string.efun_pd_url_fb_base_spa);
			}else if(key.equals(ADS_PRE_KEY)){
				return context.getString(E_string.efun_pd_url_ads_base);
			}else if(key.equals(ADS_SPA_KEY)){
				return context.getString(E_string.efun_pd_url_ads_base_spa);
			}else if(key.equals(LOGIN_PRE_KEY)){
				return context.getString(E_string.efun_pd_url_login_base);
			}else if(key.equals(LOGIN_SPA_KEY)){
				return context.getString(E_string.efun_pd_url_login_base_spa);
			}else if(key.equals(PAY_PRE_KEY)){
				return context.getString(E_string.efun_pd_url_pay_base);
			}else if(key.equals(PAY_SPA_KEY)){
				return context.getString(E_string.efun_pd_url_pay_base_spa);
			}else{
				return "";
			}
		}
		return url;
	}

	

}
