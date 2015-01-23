package com.efun.platform.module.summary.bean;

import java.util.ArrayList;

import com.efun.platform.module.BaseDataBean;
/**
 * 首页信息
 * @author Jesse
 *
 */
public class SummaryHomeBean extends BaseDataBean{
	private String gameEtag;
	private String newsEtag;
	private String picEtag;
	private ArrayList<SummaryItemBean> mNewsArray = new ArrayList<SummaryItemBean>();
	private ArrayList<SummaryItemBean> mGuidesArray = new ArrayList<SummaryItemBean>();
	private ArrayList<SummaryItemBean> mNoticesArray = new ArrayList<SummaryItemBean>();
	private ArrayList<EventGameBean> mGameArray  = new ArrayList<EventGameBean>();
	private ArrayList<BannerItemBean> mBannerArray= new ArrayList<BannerItemBean>();
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
	public ArrayList<EventGameBean> getGameArray() {
		return mGameArray;
	}
	public void setGameArray(ArrayList<EventGameBean> mGameArray) {
		this.mGameArray = mGameArray;
	}
	public ArrayList<BannerItemBean> getBannerArray() {
		return mBannerArray;
	}
	public void setBannerArray(ArrayList<BannerItemBean> mBannerArray) {
		this.mBannerArray = mBannerArray;
	}
	public ArrayList<SummaryItemBean> getmNewsArray() {
		return mNewsArray;
	}
	public void setmNewsArray(ArrayList<SummaryItemBean> mNewsArray) {
		this.mNewsArray = mNewsArray;
	}
	public ArrayList<SummaryItemBean> getmGuidesArray() {
		return mGuidesArray;
	}
	public void setmGuidesArray(ArrayList<SummaryItemBean> mGuidesArray) {
		this.mGuidesArray = mGuidesArray;
	}
	public ArrayList<SummaryItemBean> getmNoticesArray() {
		return mNoticesArray;
	}
	public void setmNoticesArray(ArrayList<SummaryItemBean> mNoticesArray) {
		this.mNoticesArray = mNoticesArray;
	}
	
}
