package com.efun.platform.bean;

import com.efun.platform.module.BaseDataBean;

public class FloatingItemBean extends BaseDataBean {
	private String name;
	private String type;
	
	public FloatingItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FloatingItemBean(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
