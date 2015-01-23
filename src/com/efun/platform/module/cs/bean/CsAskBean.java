package com.efun.platform.module.cs.bean;

import com.efun.platform.module.BaseDataBean;
/**
 * 提问
 * @author Jesse
 *
 */
public class CsAskBean extends BaseDataBean {
	private String code;

	public CsAskBean() {
		super();
	}

	public CsAskBean(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
