package com.efun.platform.module.utils;

import com.efun.core.tools.EfunStringUtil;

public class TextToolUtils {

	/**
	 * String 定義字符大小范圍
	 * @param mStr
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static boolean isStringLimited(String mStr,int minLength,int maxLength){
		if(EfunStringUtil.isEmpty(mStr)){
			return false;
		}
		if(mStr.length() >= minLength && mStr.length() < maxLength){
			return true;
		}else{			
			return false;
		}
		
	}
	/**
	 * 以字母打頭的限制
	 * @param mStr
	 * @return
	 */
	public static boolean isStringLimited(String mStr){
		if(EfunStringUtil.isEmpty(mStr)){
			return false;
		}
		char[] mchars = mStr.toCharArray();
		if((mchars[0] > 64 && mchars[0] < 91) || (mchars[0] > 96 && mchars[0] < 123)){
			return true;
		}else{
			return false;
		}
		
	}
}
