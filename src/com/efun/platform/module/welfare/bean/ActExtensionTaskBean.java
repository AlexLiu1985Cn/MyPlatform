package com.efun.platform.module.welfare.bean;

import com.efun.platform.module.BaseDataBean;
/**
 * 单个活动任务
 * @author Jesse
 *
 */
public class ActExtensionTaskBean extends BaseDataBean {
	private String condiTions;
	private String currentState;
	private String descripTion;
	private String flag;
	private String rewardCode;
	private String rewardName;
	private String rewardNum;
	private String roderId;
	private String statisticalUrl;
	private String taskCode;
	private String taskUrl;
	private String taskTitle;

	public ActExtensionTaskBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getCondiTions() {
		return condiTions;
	}

	public void setCondiTions(String condiTions) {
		this.condiTions = condiTions;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getDescripTion() {
		return descripTion;
	}

	public void setDescripTion(String descripTion) {
		this.descripTion = descripTion;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRewardCode() {
		return rewardCode;
	}

	public void setRewardCode(String rewardCode) {
		this.rewardCode = rewardCode;
	}

	public String getRewardName() {
		return rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}

	public String getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(String rewardNum) {
		this.rewardNum = rewardNum;
	}

	public String getRoderId() {
		return roderId;
	}

	public void setRoderId(String roderId) {
		this.roderId = roderId;
	}

	public String getStatisticalUrl() {
		return statisticalUrl;
	}

	public void setStatisticalUrl(String statisticalUrl) {
		this.statisticalUrl = statisticalUrl;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}


}
