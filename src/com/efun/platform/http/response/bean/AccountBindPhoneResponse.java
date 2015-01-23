package com.efun.platform.http.response.bean;

import org.json.JSONObject;

import com.efun.platform.module.account.bean.ResultBean;
/**
 * 账号绑定
 * @author Jesse
 *
 */
public class AccountBindPhoneResponse extends BaseResponseBean {
	/**
	 * 账号相关处理结果{@link ResultBean}
	 */
	private ResultBean bindPhoneBean;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject = (JSONObject) object;
		bindPhoneBean = new ResultBean();
		bindPhoneBean.setCode(jsonObject.optString("code"));
		bindPhoneBean.setMessage(jsonObject.optString("message"));
	}

	public ResultBean getResultBean() {
		return bindPhoneBean;
	}

}
