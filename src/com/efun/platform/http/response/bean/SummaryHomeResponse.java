package com.efun.platform.http.response.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.efun.platform.http.request.bean.SummaryHomeRequest;
import com.efun.platform.module.summary.bean.BannerItemBean;
import com.efun.platform.module.summary.bean.EventGameBean;
import com.efun.platform.module.summary.bean.SummaryHomeBean;
import com.efun.platform.module.summary.bean.SummaryItemBean;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.BeanType;
/**
 * 资讯 - 首页
 * @author Jesse
 *
 */
public class SummaryHomeResponse extends BaseResponseBean{
	/**
	 * 首页内容 {@link SummaryHomeBean}
	 */
	private SummaryHomeBean response;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject = (JSONObject) object;
		response = new SummaryHomeBean();
		response.setGameEtag(jsonObject.optString("gameEtag"));
		response.setNewsEtag(jsonObject.optString("newsEtag"));
		response.setPicEtag(jsonObject.optString("picEtag"));
		jsonObject = getValueFromLocal(jsonObject);
		JSONArray bannerArray = jsonObject.optJSONArray("picsInfo");
		ArrayList<BannerItemBean> banners = new ArrayList<BannerItemBean>();
		BannerItemBean banner = null;
		JSONObject picJson = null;
		for (int i = 0; i < bannerArray.length(); i++) {
			picJson = bannerArray.optJSONObject(i);
			banner = new BannerItemBean();
			banner.setPic(picJson.optString("pic"));
			banner.setUrl(picJson.optString("url"));
			banner.setTitle(picJson.optString("title"));
			banners.add(banner);
		}
		JSONArray gameArray = jsonObject.optJSONArray("gamesInfo");
		ArrayList<EventGameBean> games = new ArrayList<EventGameBean>();
		EventGameBean game = null;
		JSONObject gameJson = null;
		for (int i = 0; i < gameArray.length(); i++) {
			gameJson = gameArray.optJSONObject(i);
			game = new EventGameBean();
			game.setGameCode(gameJson.optString("gameCode"));
			game.setGameName(gameJson.optString("gameName"));
			game.setActGameCode(gameJson.optString("actGameCode"));
			game.setGameDesc(gameJson.optString("gameDesc"));
			game.setGameType(gameJson.optString("gameType"));
			game.setBigpic(gameJson.optString("bigpic"));
			game.setSmallpic(gameJson.optString("smallpic"));
			game.setUrl(gameJson.optString("url"));
			game.setLike(gameJson.optInt("like"));
			game.setAndroidDownload(gameJson.optString("androidDownload"));
			game.setAndroidVersion(gameJson.optString("androidVersion"));
			game.setPackageName(gameJson.optString("packageName"));
			game.setPackageSize(gameJson.optString("packageSize"));
			game.setVideoUrl(gameJson.optString("videoUrl"));
			game.setVersion(gameJson.optString("version"));
			game.setPic_display(gameJson.optString("pic_display"));
			games.add(game);
		}
		JSONArray noticesArray= jsonObject.optJSONArray("newsInfo");//公告
		ArrayList<SummaryItemBean> notices = new ArrayList<SummaryItemBean>();
		SummaryItemBean notice = null;
		JSONObject noticeJson = null;
		for (int i = 0; i < noticesArray.length(); i++) {
			noticeJson = noticesArray.optJSONObject(i);
			notice = new SummaryItemBean(BeanType.BEAN_SUMMARYITEMBEAN);
			notice.setTitle(noticeJson.optString("title"));
			notice.setType(noticeJson.optInt("type"));
			notice.setCrtime(noticeJson.optLong("crtime"));
			notice.setHtmlpathurl(noticeJson.optString("htmlpathurl"));
			notice.setGameCode(noticeJson.optString("gameCode"));
			notice.setGameName(noticeJson.optString("gameName"));
			notice.setIphoneUrl(noticeJson.optString("iphoneUrl")+"#"+noticeJson.optInt("type"));
			notices.add(notice);
		}
		//新聞newsList
		JSONArray newsArray= jsonObject.optJSONArray("newsList");
		ArrayList<SummaryItemBean> news = new ArrayList<SummaryItemBean>();
		SummaryItemBean mNew = null;
		JSONObject newJson = null;
		for (int i = 0; i < newsArray.length(); i++) {
			newJson = newsArray.optJSONObject(i);
			mNew = new SummaryItemBean(BeanType.BEAN_SUMMARYITEMBEAN);
			mNew.setTitle(newJson.optString("title"));
			mNew.setType(newJson.optInt("type"));
			mNew.setCrtime(newJson.optLong("crtime"));
			mNew.setHtmlpathurl(newJson.optString("htmlpathurl"));
			mNew.setGameCode(newJson.optString("gameCode"));
			mNew.setIphoneUrl(newJson.optString("iphoneUrl")+"#"+newJson.optInt("type"));
			news.add(mNew);
		}
		//攻略guideList
		JSONArray guideArray= jsonObject.optJSONArray("guideList");
		ArrayList<SummaryItemBean> guides = new ArrayList<SummaryItemBean>();
		SummaryItemBean guide = null;
		JSONObject guideJson = null;
		for (int i = 0; i < guideArray.length(); i++) {
			guideJson = guideArray.optJSONObject(i);
			guide = new SummaryItemBean(BeanType.BEAN_SUMMARYITEMBEAN);
			guide.setTitle(guideJson.optString("title"));
			guide.setType(guideJson.optInt("type"));
			guide.setCrtime(guideJson.optLong("crtime"));
			guide.setHtmlpathurl(guideJson.optString("htmlpathurl"));
			guide.setGameCode(guideJson.optString("gameCode"));
			guide.setIphoneUrl(guideJson.optString("iphoneUrl")+"#"+guideJson.optInt("type"));
			guides.add(guide);
		}
		
		response.setBannerArray(banners);
		response.setGameArray(games);
		response.setmNewsArray(news);
		response.setmGuidesArray(guides);
		response.setmNoticesArray(notices);
	}
	public SummaryHomeBean getSummaryHomeBean() {
		return response;
	}

	private JSONObject getValueFromLocal(JSONObject jsonObject) {
		String[] etagKeys = new String[] {"gameEtag", "newsEtag", "picEtag" };
		String[] etagValues=new String[] { response.getGameEtag(), response.getNewsEtag(),response.getPicEtag()};
		if (Store.changeNotify(getContext(),etagKeys ,etagValues, SummaryHomeResponse.class)) {
			Store.saveResponseByClazz(getContext(), SummaryHomeResponse.class,getResponseJson2String());
			Store.saveRequestByClazz(getContext(), etagKeys, etagValues, SummaryHomeRequest.class);
		}else{
			try {
				return new JSONObject(Store.getResponseByClazz(getContext(), SummaryHomeResponse.class));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
}
