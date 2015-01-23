package com.efun.platform.module.welfare.bean;

import java.util.ArrayList;

import com.efun.platform.module.BaseDataBean;
import com.efun.platform.module.game.bean.GameItemBean;
/**
 * 推荐游戏
 * @author Jesse
 *
 */
public class ActExtensionBean extends BaseDataBean{
	private String code;
	private String gameCode;
	private String activityCode;
	private String message;
	private String currentState;
	private String context;
	private int giftsAllCount;
	private int giftsLastCount;
	
	
	private ArrayList<ActExtensionGiftBean> mArrayOfGift;
	private ArrayList<ActExtensionTaskBean> mArrayOfTask;
	private GameItemBean mGameBean;
	
	public ActExtensionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<ActExtensionGiftBean> getArrayOfGift() {
		return mArrayOfGift;
	}
	public void setArrayOfGift(ArrayList<ActExtensionGiftBean> mArrayOfGift) {
		this.mArrayOfGift = mArrayOfGift;
	}
	public ArrayList<ActExtensionTaskBean> getArrayOfTask() {
		return mArrayOfTask;
	}
	public void setArrayOfTask(ArrayList<ActExtensionTaskBean> mArrayOfTask) {
		this.mArrayOfTask = mArrayOfTask;
	}
	public GameItemBean getGameBean() {
		return mGameBean;
	}
	public void setGameBean(GameItemBean mGameBean) {
		this.mGameBean = mGameBean;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getGiftsAllCount() {
		return giftsAllCount;
	}
	public void setGiftsAllCount(int giftsAllCount) {
		this.giftsAllCount = giftsAllCount;
	}
	public int getGiftsLastCount() {
		return giftsLastCount;
	}
	public void setGiftsLastCount(int giftsLastCount) {
		this.giftsLastCount = giftsLastCount;
	}
	
}
