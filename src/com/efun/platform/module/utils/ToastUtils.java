package com.efun.platform.module.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	public static void toast(Context context,String msg){
		if(context!=null){
			Toast.makeText(context, msg+"", Toast.LENGTH_SHORT).show();
		}
	}
	public static void toast(Context context,int msg){
		if(context!=null){
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}
	public static void toast(Context context,int msg,int time){
		if(context!=null){
			if(time==Toast.LENGTH_LONG){
				Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
