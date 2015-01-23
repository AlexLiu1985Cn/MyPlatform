package com.efun.platform.http.response.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.efun.platform.module.game.bean.GameItemBean;
import com.efun.platform.module.welfare.bean.ActExtensionBean;
import com.efun.platform.module.welfare.bean.ActExtensionGiftBean;
import com.efun.platform.module.welfare.bean.ActExtensionTaskBean;

/**
 * 好康 - 领取免费点数
 * @author Jesse
 *
 */
public class ActivityExtensionResponse extends BaseResponseBean{
	/**
	 * 任务，礼包列表 {@link ActExtensionBean}
	 */
	private ActExtensionBean response;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject = (JSONObject) object;
		response = new ActExtensionBean();
		response.setActivityCode(jsonObject.optString("activityCode"));
		response.setCode(jsonObject.optString("code"));
		response.setGameCode(jsonObject.optString("gameCode"));
		response.setCurrentState(jsonObject.optString("currentState"));
		response.setMessage(jsonObject.optString("message"));
		response.setContext(jsonObject.optString("context"));
		response.setGiftsAllCount(jsonObject.optInt("giftsAllCount"));
		response.setGiftsLastCount(jsonObject.optInt("giftsLastCount"));
		JSONObject gameObject = jsonObject.optJSONObject("gameInfo");
		GameItemBean game = new GameItemBean();
		game.setGameCode(gameObject.optString("gameCode"));
		game.setGameName(gameObject.optString("gameName"));
		game.setGameType(gameObject.optString("gameType"));
		game.setScore(gameObject.optInt("score"));
		game.setBigpic(gameObject.optString("bigpic"));
		game.setSmallpic(gameObject.optString("smallpic"));
		game.setUrl(gameObject.optString("url"));
		game.setLang(gameObject.optString("lang"));
		game.setGameDesc(gameObject.optString("gameDesc"));
		game.setAndroidDownload(gameObject.optString("androidDownload"));
		game.setIosDownload(gameObject.optString("iosDownload"));
		game.setPackageName(gameObject.optString("packageName"));
		game.setAndroidVersion(gameObject.optString("androidVersion"));
		game.setVideoUrl(gameObject.optString("videoUrl"));
		game.setPackageSize(gameObject.optString("packageSize"));
		game.setVersion(gameObject.optString("version"));
		game.setLike(gameObject.optInt("like"));
		response.setGameBean(game);
		
		JSONArray jsonArrayOfGift = jsonObject.optJSONArray("gifts");
		ArrayList<ActExtensionGiftBean> arrayOfGift = new ArrayList<ActExtensionGiftBean>();
		JSONObject jsonItemOfGift = null;
		ActExtensionGiftBean giftItemBean = null;
		for (int i = 0; i < jsonArrayOfGift.length(); i++) {
			jsonItemOfGift = jsonArrayOfGift.optJSONObject(i);
			giftItemBean = new ActExtensionGiftBean();
			giftItemBean.setId(jsonItemOfGift.optString("id"));
			giftItemBean.setGameCode(jsonItemOfGift.optString("gameCode"));
			giftItemBean.setGoodsName(jsonItemOfGift.optString("goodsName"));
			giftItemBean.setAwardDesc(jsonItemOfGift.optString("awardDesc"));
			giftItemBean.setAwardRule(jsonItemOfGift.optString("awardRule"));
			giftItemBean.setActiveStartTime(jsonItemOfGift.optString("activeStartTime"));
			giftItemBean.setActiveEndTime(jsonItemOfGift.optString("activeEndTime"));
			giftItemBean.setOrderno(jsonItemOfGift.optString("orderno"));
			arrayOfGift.add(giftItemBean);
		}
		response.setArrayOfGift(arrayOfGift);
		
		JSONArray jsonArrayOfTask= jsonObject.optJSONArray("tasks");
		ArrayList<ActExtensionTaskBean> arrayOfTask = new ArrayList<ActExtensionTaskBean>();
		JSONObject jsonItemOfTask = null;
		ActExtensionTaskBean taskItemBean = null;
		for (int i = 0; i < jsonArrayOfTask.length(); i++) {
			jsonItemOfTask = jsonArrayOfTask.optJSONObject(i);
			taskItemBean = new ActExtensionTaskBean();
			taskItemBean.setCondiTions(jsonItemOfTask.optString("condiTions"));
			taskItemBean.setCurrentState(jsonItemOfTask.optString("currentState"));
			taskItemBean.setDescripTion(jsonItemOfTask.optString("descripTion"));
			taskItemBean.setFlag(jsonItemOfTask.optString("flag"));
			taskItemBean.setRewardCode(jsonItemOfTask.optString("rewardCode"));
			taskItemBean.setRewardName(jsonItemOfTask.optString("rewardName"));
			taskItemBean.setRewardNum(jsonItemOfTask.optString("rewardNum"));
			taskItemBean.setRoderId(jsonItemOfTask.optString("roderId"));
			taskItemBean.setStatisticalUrl(jsonItemOfTask.optString("statisticalUrl"));
			taskItemBean.setTaskCode(jsonItemOfTask.optString("taskCode"));
			taskItemBean.setTaskTitle(jsonItemOfTask.optString("taskTitle"));
			taskItemBean.setTaskUrl(jsonItemOfTask.optString("taskUrl"));
			arrayOfTask.add(taskItemBean);
		}
		response.setArrayOfTask(arrayOfTask);
	}
	public ActExtensionBean getActExtensionBean() {
		return response;
	}
}
