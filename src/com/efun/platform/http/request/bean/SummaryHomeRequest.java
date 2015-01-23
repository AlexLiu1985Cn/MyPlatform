package com.efun.platform.http.request.bean;

import java.util.HashMap;

import android.content.Context;

import com.efun.platform.utils.Store;
/**
 * 资讯 - 首页
 * @author Jesse
 *
 */
public class SummaryHomeRequest extends BaseRequestBean{
	private String version;
	private String platform;
	private String picSize;
	private String partner;
	private String gameCode;
	private String gameEtag;
	private String newsEtag;
	private String picEtag;
	
	public SummaryHomeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SummaryHomeRequest(Context context ,String version, String platform, String picSize,
			String partner,String gameCode) {
		super();
		this.version = version;
		this.platform = platform;
		this.picSize = picSize;
		this.partner = partner;
		this.gameCode = gameCode;
		HashMap<String, String> etagValues = Store.createValuesByClazz(
				context, new String[]{"gameEtag","newsEtag","picEtag"}, SummaryHomeRequest.class);
		this.gameEtag = etagValues.get("gameEtag");
		this.newsEtag  = etagValues.get("newsEtag");
		this.picEtag =  etagValues.get("picEtag");
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getPicSize() {
		return picSize;
	}
	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getGameEtag() {
		return gameEtag;
	}
	public void setGameEtag(String gameEtag) {
		this.gameEtag = gameEtag;
	}
	public String getNewsEtag() {
		return newsEtag;
	}
	public void setNewsEtag(String newsEtag) {
		this.newsEtag = newsEtag;
	}
	public String getPicEtag() {
		return picEtag;
	}
	public void setPicEtag(String picEtag) {
		this.picEtag = picEtag;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	
}
