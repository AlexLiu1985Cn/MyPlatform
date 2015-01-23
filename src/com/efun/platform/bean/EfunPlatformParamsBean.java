package com.efun.platform.bean;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.efun.core.tools.EfunStringUtil;

import android.text.TextUtils;
import android.util.Log;

public class EfunPlatformParamsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uid; // 用户的uid
	private String uname; // 用户登录时的用户名
	private String serverCode; // 服务器编码
	private String roleId; // 角色ID
	private String efunRole; // 角色名
	private String roleLevel; // 角色等级
	private String sign; // 登录签名
	private String timestamp; // 登录时间戳
	private String remark; // 拓展参数
	private String creditId; // 储值时的唯一标识
	private String loginType;//登錄類型
	public EfunPlatformParamsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EfunPlatformParamsBean(String uid, String uname, String serverCode,
			String roleId,String efunRole, String roleLevel,
			String sign, String timestamp, String remark, String creditId
			) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.serverCode = serverCode;
		this.roleId = roleId;
		this.efunRole = efunRole;
		this.roleLevel = roleLevel;
		this.sign = sign;
		this.timestamp = timestamp;
		this.remark = remark;
		this.creditId = creditId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getEfunRole() {
		return efunRole;
	}

	public void setEfunRole(String efunRole) {
		this.efunRole = efunRole;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void checkParams() {
		// 日志
		Field[] fields = EfunPlatformParamsBean.class.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].getName().equals("serialVersionUID")) {
				try {
					if (fields[i].get(this) != null) {
						if (EfunStringUtil.isEmpty(fields[i].get(this).toString())) {
							Log.e("efun", fields[i].getName() + "为空!");
						}
					}else{
						Log.e("efun", fields[i].getName()+"没有设置");
					}
				} catch (Exception e) {
				}
			}
		}
	}

}
