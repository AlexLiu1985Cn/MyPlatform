package com.efun.platform.http.response.bean;

import org.json.JSONObject;

import android.text.TextUtils;

import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.AccountLoginRequest;
import com.efun.platform.http.request.bean.AccountRegisterRequest;
import com.efun.platform.http.request.bean.AccountUpdateRequest;
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.utils.PushUtils;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.LoginPlatform;
/**
 * 账号
 * @author Jesse
 *
 */
public class AccountUpdateResponse extends BaseResponseBean {
	private static final long serialVersionUID = 1L;
	/**
	 * 玩家信息 {@link User}
	 */
	private User userInfoBean;
	@Override
	public void setValues(Object object) {
		JSONObject jsonObject = (JSONObject) object;
		userInfoBean = new User();
		userInfoBean.setCode(jsonObject.optString("code"));
		userInfoBean.setMessage(jsonObject.optString("message"));
		if (jsonObject.has("uid")&& userInfoBean.getCode().equals(HttpParam.RESULT_1000)) {
			String accessToken = jsonObject.optString("accessToken");
			long mUid = jsonObject.optLong("uid");
			
			String[] etagKeys = new String[]{"accessToken"};
			String[] etagValues = new String[]{accessToken};
			boolean flag = Store.changeNotify(getContext(),
					etagKeys,etagValues,AccountUpdateResponse.class);
			if(flag){
				Store.saveResponseByClazz(getContext(), AccountUpdateResponse.class,getResponseJson2String());
				etagKeys = new String[]{"accessToken"};
				etagValues = new String[]{accessToken};
				Store.saveRequestByClazz(getContext(),
						etagKeys, 
						etagValues, 
						AccountUpdateRequest.class);
			}
			
			etagKeys = new String[]{"uid"};
			etagValues = new String[]{mUid+""};
			flag = Store.changeNotify(getContext(),
					etagKeys,etagValues,AccountResponse.class);
			if(!flag){
				Store.saveResponseByClazz(getContext(), AccountResponse.class,getResponseJson2String());
			}
		}
	}
	

	public ResultBean getResultBean() {
		return userInfoBean;
	}

}
