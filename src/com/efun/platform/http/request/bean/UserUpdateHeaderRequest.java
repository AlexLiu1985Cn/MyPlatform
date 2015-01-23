package com.efun.platform.http.request.bean;

import com.efun.platform.utils.Const.HttpParam;

/**
 * 玩家 - 上传头像
 * @author Jesse
 *
 */
public class UserUpdateHeaderRequest extends BaseRequestBean{
	private String uid;
	private String encodeFile;
	private String fileName;
	private String platform;
	
	public UserUpdateHeaderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserUpdateHeaderRequest(String uid, String encodeFile,String fileName) {
		super();
		this.uid = uid;
		this.encodeFile = encodeFile;
		this.fileName = fileName;
		this.platform = HttpParam.PLATFORM_AREA;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEncodeFile() {
		return encodeFile;
	}
	public void setEncodeFile(String encodeFile) {
		this.encodeFile = encodeFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
}
