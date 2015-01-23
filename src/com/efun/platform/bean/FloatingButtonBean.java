package com.efun.platform.bean;

import java.util.ArrayList;

import com.efun.platform.module.BaseDataBean;
import com.efunfloat.game.window.bean.FloatItemBean;

public class FloatingButtonBean extends BaseDataBean {
	private String code;
	private String message;
	private String cacheDate;
	private String cacheMS;
	private ArrayList<FloatItemBean> buttons;
	
	public FloatingButtonBean() {
		super();
		buttons = new ArrayList<FloatItemBean>();
	}
	
	public FloatingButtonBean(String code, String message, String cacheDate,
			String cacheMS, ArrayList<FloatItemBean> buttons) {
		super();
		this.code = code;
		this.message = message;
		this.cacheDate = cacheDate;
		this.cacheMS = cacheMS;
		this.buttons = buttons;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<FloatItemBean> getButtons() {
		return buttons;
	}
	public void setButtons(ArrayList<FloatItemBean> buttons) {
		this.buttons = buttons;
	}

	public String getCacheDate() {
		return cacheDate;
	}

	public void setCacheDate(String cacheDate) {
		this.cacheDate = cacheDate;
	}

	public String getCacheMS() {
		return cacheMS;
	}

	public void setCacheMS(String cacheMS) {
		this.cacheMS = cacheMS;
	}
	
}
