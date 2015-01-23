package com.efun.platform.module.summary.utils;

import android.widget.TextView;

import com.efun.platform.AndroidScape.E_color;

public class SummaryUtil {
	/**
	 * 修饰资讯头部
	 * @param view
	 * @param type
	 */
	public static void displaySummaryHeader(TextView view,int type){
		switch (type) {
		case 0:
			view.setText("NOTICE");
			view.setBackgroundResource(E_color.e_83d160);
			break;
		case 1:
			view.setText("NEWS");
			view.setBackgroundResource(E_color.e_eb4847);
			break;
		case 2:
			view.setText("GUIDE");
			view.setBackgroundResource(E_color.e_faca57);
			break;
		case 4:
			view.setText("EVENT");
			view.setBackgroundResource(E_color.e_8058bd);
			break;
		}
	}
}
