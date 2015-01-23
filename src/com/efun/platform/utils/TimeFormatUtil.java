package com.efun.platform.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_string;
/**
 * 时间转化帮助类
 * @author Jesse
 *
 */
public class TimeFormatUtil {
	/**
	 * yyyy-MM-dd hh:mm:ss.S这种格式的数据转化为yyyy-MM-dd格式的数据
	 * @param dateStr
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String  StringFormatDate(String dateStr){	
		if(EfunStringUtil.isEmpty(dateStr)){
			return null;
		}
		// 转换之前的格式  
        String temp1 = "yyyy-MM-dd hh:mm:ss.S";
        // 转换之后的格式  
        String temp2 = "yyyy-MM-dd";  
 
        DateFormat smp1 = new SimpleDateFormat(temp1); 
        DateFormat smp2 = new SimpleDateFormat(temp2);  
        Date d = null;  
        try {  
            // 把字符串的日期转换成一个新的日期对象  
            d = smp1.parse(dateStr);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  	
		return smp2.format(d);
	}
	/**
	 * yyyy-MM-dd hh:mm:ss这种格式的数据转化为yyyy-MM-dd格式的数据
	 * @param dateStr
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String  StringFormatDate2(String dateStr){	
		if(EfunStringUtil.isEmpty(dateStr)){
			return null;
		}
		// 转换之前的格式  
		String temp1 = "yyyy-MM-dd hh:mm:ss";
		// 转换之后的格式  
		String temp2 = "yyyy-MM-dd";  
		
		DateFormat smp1 = new SimpleDateFormat(temp1); 
		DateFormat smp2 = new SimpleDateFormat(temp2);  
		Date d = null;  
		try {  
			// 把字符串的日期转换成一个新的日期对象  
			d = smp1.parse(dateStr);  
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  	
		return smp2.format(d);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String LongFormatDate(long times){
		String temp = "yyyy-MM-dd";
		Date date = new Date(times);
		SimpleDateFormat format = new SimpleDateFormat(temp);		
		return format.format(date);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String LongFormatDate2(long times){
		String temp = "HH:mm";
		Date date = new Date(times);
		SimpleDateFormat format = new SimpleDateFormat(temp);		
		return format.format(date);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String LongFormatDate3(long times){
		String temp = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date(times);
		SimpleDateFormat format = new SimpleDateFormat(temp);		
		return format.format(date);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String LongFormatDate4(long times){
		String temp = "MM.dd";
		Date date = new Date(times);
		SimpleDateFormat format = new SimpleDateFormat(temp);		
		return format.format(date);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static long StringFormatLong(String dateStr){
		if(EfunStringUtil.isEmpty(dateStr)){
			return 0;
		}
		// 转换之前的格式  
        String temp = "yyyy-MM-dd HH:mm:ss.S"; 
        long millionSeconds = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(temp);                       
        try {
        	millionSeconds = sdf.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millionSeconds;
	}
	
	public static String TimeDifference(Context context,long oldTime){
		long timeDiff = System.currentTimeMillis() - oldTime;
		if(0<timeDiff && timeDiff< 1000 * 60){
			return context.getString(E_string.efun_pd_xlistview_time_now);
		}else if( 1000 * 60 <timeDiff && timeDiff<1000 * 60 * 60){
			return timeDiff/(1000 * 60)+context.getString(E_string.efun_pd_xlistview_time_minutes);
		}else {
			return LongFormatDate2(oldTime);
		}
	}
}
