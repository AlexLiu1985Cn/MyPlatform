package com.efun.platform.module.account.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.AccountBindPhoneRequest;
import com.efun.platform.http.request.bean.AccountBindPhoneSendVcodeRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.response.bean.AccountBindPhoneResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.base.FixedActivity;
import com.efun.platform.module.base.impl.OnUpdateUserListener;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.widget.TitleView;
/**
 * 绑定手机
 * @author Jesse
 *
 */
public class BindPhoneActivity extends FixedActivity{
	
	private TextView mGetVerifyCode,mFinishVerify;
	private EditText mPhone,mCode;
	private String mPhoneNum, mCodeNum;
	private User mUserInfoBean;
	@Override
	public boolean needRequestData() {
		return false;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		return null;
	}

	@Override
	public void init(Bundle bundle) {
		mUserInfoBean = (User) bundle.get(Const.BEAN_KEY);
		mPhone = (EditText) findViewById(E_id.edit_1);
		mCode= (EditText) findViewById(E_id.edit_2);
		mGetVerifyCode = (TextView) findViewById(E_id.text_1);
		mFinishVerify = (TextView) findViewById(E_id.text_2);
		mGetVerifyCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPhoneNum = mPhone.getText().toString();
				if(EfunStringUtil.isEmpty(mPhoneNum)){
					ToastUtils.toast(mContext, E_string.efun_pd_toast_empty_phone);
					return;
				}
				requestWithDialog(sendVCodeRequest(),getString(E_string.efun_pd_bind_phone_intro));
			}
		});
		mFinishVerify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCodeNum = mCode.getText().toString();
				if(EfunStringUtil.isEmpty(mPhoneNum)){
					ToastUtils.toast(mContext, E_string.efun_pd_toast_error_phone);
					return;
				}
				if(EfunStringUtil.isEmpty(mCodeNum)){
					ToastUtils.toast(mContext, E_string.efun_pd_toast_empty_vcode);
					return;
				}
				TrackingUtils.track(TrackingUtils.ACTION_ACCOUNT, TrackingUtils.NAME_ACCOUNT_BIND_PHONE);
				requestWithDialog(bindPhoneRequest(),getString(E_string.efun_pd_bind_phone_intro));
			}
		});
	}

	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType,baseResponse);
		if(requestType==IPlatformRequest.REQ_ACCOUNT_BIND_PHONE_SEND_VCODE){
			AccountBindPhoneResponse response = (AccountBindPhoneResponse)baseResponse;
			ResultBean result = response.getResultBean();
			ToastUtils.toast(mContext, result.getMessage());
		}
		if(requestType==IPlatformRequest.REQ_ACCOUNT_BIND_PHONE){
			AccountBindPhoneResponse response = (AccountBindPhoneResponse)baseResponse;
			ResultBean result = response.getResultBean();
			ToastUtils.toast(mContext, result.getMessage());
			if(result.getCode().equals(HttpParam.RESULT_1000)){
				mUserInfoBean.setPhone(HttpParam.RESULT_1000);
				((OnUpdateUserListener)GameToPlatformParamsSaveUtil.getInstanse().getOnEfunListener()).onUpdate(mUserInfoBean);
				finish();
			}
		}
		
	}
	
	@Override
	public ViewGroup[] needShowLoading() {
		return null;
	}


	@Override
	public int LayoutId() {
		return E_layout.efun_pd_bind_phone;
	}
	
	private BaseRequestBean[] sendVCodeRequest(){
		AccountBindPhoneSendVcodeRequest sendVCodeRequest = new AccountBindPhoneSendVcodeRequest(mPhoneNum,GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid(),HttpParam.APP);
		sendVCodeRequest.setReqType(IPlatformRequest.REQ_ACCOUNT_BIND_PHONE_SEND_VCODE);
		return new BaseRequestBean[]{sendVCodeRequest};
	}
	private BaseRequestBean[] bindPhoneRequest(){
		AccountBindPhoneRequest bindPhoneRequest = new AccountBindPhoneRequest(mPhoneNum,GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid(),mCodeNum,GameToPlatformParamsSaveUtil.getInstanse().getUser().getSign(),GameToPlatformParamsSaveUtil.getInstanse().getUser().getTimestamp(),HttpParam.PLATFORM_AREA,HttpParam.APP);
		bindPhoneRequest.setReqType(IPlatformRequest.REQ_ACCOUNT_BIND_PHONE);
		return new BaseRequestBean[]{bindPhoneRequest};
	}

	@Override
	public void initTitle(TitleView titleView) {
		titleView.setTitleRightStatus(View.GONE);
		titleView.setTitleCenterRes(E_string.efun_pd_bind_phone, false);
	}

}
