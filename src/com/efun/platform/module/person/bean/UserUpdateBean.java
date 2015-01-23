package com.efun.platform.module.person.bean;

import com.efun.platform.module.BaseDataBean;

/**
 * 更新玩家信息
 * @author Jesse
 *
 */
public class UserUpdateBean extends BaseDataBean{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	
	public UserUpdateBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserUpdateBean(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
