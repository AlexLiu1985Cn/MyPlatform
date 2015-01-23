package com.efun.platform.http.response.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.efun.platform.bean.FloatingButtonBean;
import com.efun.platform.bean.FloatingItemBean;
import com.efun.platform.module.cs.bean.CsGainGameBean;
import com.efun.platform.module.cs.bean.CsGainGameItemBean;
import com.efun.platform.module.cs.bean.CsGainRoleBean;
import com.efun.platform.module.cs.bean.CsGainRoleItemBean;
import com.efunfloat.game.window.bean.FloatItemBean;
/**
 * 悬浮按钮
 * @author itxuxxey
 *
 */
public class FloatingButtonResponse extends BaseResponseBean {

	private FloatingButtonBean response;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject = (JSONObject) object;
		response = new FloatingButtonBean();
		response.setCode(jsonObject.optString("code"));
		response.setMessage(jsonObject.optString("message"));
		response.setCacheDate(jsonObject.optString("cacheDate"));
		response.setCacheMS(jsonObject.optString("cacheMS"));
		ArrayList<FloatItemBean> buttonList = new ArrayList<FloatItemBean>();
		if(jsonObject.has("buttons")){
			JSONArray jsonArray = jsonObject.optJSONArray("buttons");
			FloatItemBean bean = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.optJSONObject(i);
				bean = new FloatItemBean();
				bean.setItemType(jsonObject.optString("type"));
				bean.setItemName(jsonObject.optString("name"));
				buttonList.add(bean);
			}
		}
		response.setButtons(buttonList);
	}

	public FloatingButtonBean getFloatingButtonBean() {
		return response;
	}
}
