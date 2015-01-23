package com.efun.platform.utils;

import java.util.Locale;

import android.content.Context;

public class GetSystemConfigUtil {
	
	public static String getPhoneLanguage(Context context){
		
		//检测手机语言
	    Locale locale = context.getResources().getConfiguration().locale;
	    String language = locale.getLanguage();
	    String country = locale.getCountry();
	    String languageType = null;
	    if(language.equals("zh")){
	    	if(country.equals("CN")){
	    		languageType = language+"_CH";//简中
	    	}else{
	    		languageType = language+"_HK";//繁中	  
	    	}
	    }else if(language.equals("en")){//美国英语
	    	languageType = language+"_US";
	    }else if(language.equals("vi")){//越南语
	    	languageType = language+"_VN";
	    }else if(language.equals("th")){//泰语
	    	languageType = language+"_TH";
	    }else if(language.equals("ja")){//日语
	    	languageType = language+"_JP";
	    }else if(language.equals("pt")){//葡萄牙语
	    	languageType = language+"_PT";
	    }else if(language.equals("ar")){//阿拉伯语
	    	languageType = language+"_AE";
	    }else if(language.equals("ko")){//韩语
	    	languageType = language+"_KR";
	    }else if(language.equals("es")){//西班牙语
	    	languageType = language+"_ES";
	    }else{
	    	languageType = language+"_"+country;
	    }
		return languageType;
	}

}
