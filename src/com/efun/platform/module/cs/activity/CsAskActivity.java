package com.efun.platform.module.cs.activity;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunLogUtil;
import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_array;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.AccountGetUserFBUidsByUidRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.CsAskRequest;
import com.efun.platform.http.request.bean.CsGainGameListRequest;
import com.efun.platform.http.request.bean.CsGainRoleListRequest;
import com.efun.platform.http.request.bean.CsGainServerListRequest;
import com.efun.platform.http.response.bean.AccountGetUserFBUidsByUidResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.CsAskResponse;
import com.efun.platform.http.response.bean.CsGainGameListResponse;
import com.efun.platform.http.response.bean.CsGainRoleListResponse;
import com.efun.platform.http.response.bean.CsGainServersListResponse;
import com.efun.platform.module.base.FixedActivity;
import com.efun.platform.module.cs.bean.CsGainFBRoleItemBean;
import com.efun.platform.module.cs.bean.CsGainGameBean;
import com.efun.platform.module.cs.bean.CsGainGameItemBean;
import com.efun.platform.module.cs.bean.CsGainRoleBean;
import com.efun.platform.module.cs.bean.CsGainRoleItemBean;
import com.efun.platform.module.cs.bean.CsGainServerBean;
import com.efun.platform.module.cs.bean.CsGainServerItemBean;
import com.efun.platform.module.cs.bean.CsGetFBUserInfosBean;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.LoginPlatform;
import com.efun.platform.widget.TitleView;
/**
 * 我要提问页面
 * @author Jesse
 *
 */
public class CsAskActivity extends FixedActivity{
	
	private RelativeLayout logExceptLayout, choseGameLayout, choseServerLayout, choseRoleLayout;
	private EditText descriptionEdit, emailEdit, phoneEdit;
	private TextView submitButton, questionTypeTxt, gamesTxt, serversTxt, rolesTxt;
	private ArrayList<CsGainGameItemBean> mGameInfos;
	private ArrayList<CsGainServerItemBean> mServerInfos;
	private ArrayList<CsGainRoleItemBean> mRoleInfos;
	private ArrayList<CsGainFBRoleItemBean> mFBRoleInfos;
	private ViewGroup[] groups = new ViewGroup[0];
	private String mCurrentGameCode;
	private String mCurrentServerCode;
	private String mCurrentRoleId;
	private String mCurrentGameUid;
	private String[] questionType;
	private String[] quetionTypeCode;
	private String mQuestionType;
	private String mFBUids;
	private String mCurrentUid;//fb当前使用的uid
	private int mFBCountRoles;//对应的角色个数（总数）
	private int mRealCountFBRoles = 0;//实际当前的个数
	private int mFBRoles;//FB对应的所有角色

	private static final int GAIN_GAMES = 0;
	private static final int GAIN_SERVERS = 1;
	private static final int GAIN_ROLE = 2;
	private static final int GAIN_ROLE_FB = 3;
	private int requestFlag = 99;
	
	private boolean serverClickFlag = false;
	
	private long serverOldTime = 0;
	private long roleOldTime = 0;

	private String content;
	private String phoneNum;
	private String email;

	@Override
	public void init(Bundle bundle) {
		logExceptLayout = (RelativeLayout) findViewById(E_id.cs_contaier_relative_1);
		choseGameLayout = (RelativeLayout) findViewById(E_id.contaier_relative_2);
		choseServerLayout = (RelativeLayout) findViewById(E_id.contaier_relative_3);
		choseRoleLayout = (RelativeLayout) findViewById(E_id.contaier_relative_4);

		questionTypeTxt = (TextView) findViewById(E_id.txt_question_type);
		gamesTxt = (TextView) findViewById(E_id.txt_games);
		serversTxt = (TextView) findViewById(E_id.txt_servers);
		rolesTxt = (TextView) findViewById(E_id.txt_roles);

		descriptionEdit = (EditText) findViewById(E_id.edit_1);
		emailEdit = (EditText) findViewById(E_id.edit_2);
		phoneEdit = (EditText) findViewById(E_id.edit_3);
		submitButton = (TextView) findViewById(E_id.commit);
		questionType = getResources().getStringArray(E_array.efun_pd_cs_params_ask);
		quetionTypeCode = getResources().getStringArray(E_array.efun_pd_cs_params_ask_code);
		mQuestionType = quetionTypeCode[0];

		logExceptLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				paramListDialog(questionType, mContext, new OnDialogSelect() {

					@Override
					public void onSelect(int postion) {
						mQuestionType = quetionTypeCode[postion];
						questionTypeTxt.setText(questionType[postion]);
					}
				});
			}
		});
		mCurrentGameCode = GameToPlatformParamsSaveUtil.getInstanse().getGameCode();
		requestFlag = GAIN_GAMES;
		requestWithoutDialog(needRequestDataBean());
		choseGameLayout.setClickable(false);
		choseGameLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				if (!EfunStringUtil.isEmpty(mQuestionType)) {
					requestFlag = GAIN_GAMES;
					requestWithoutDialog(needRequestDataBean());
				} else {
					ToastUtils.toast(mContext,getString(E_string.efun_pd_cs_hints_ask_select_question_type));
				}
			}
		});
		choseServerLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				Log.i("efun", "serverOldTime:"+serverOldTime);
				if(serverOldTime == 0L){
					serverOldTime = System.currentTimeMillis();
            	}else if((System.currentTimeMillis() - serverOldTime) < 1000){
            		serverOldTime = System.currentTimeMillis();
            		return;
            	}
				if((!EfunStringUtil.isEmpty(mCurrentGameCode) && !EfunStringUtil.isEmpty(mQuestionType) && mCurrentGameCode.equals(HttpParam.PLATFORM_CODE))){
//					ToastUtils.toast(mContext, getResources().getString(E_string.efun_pd_cs_hints_ask_select_game));
				}else if (!EfunStringUtil.isEmpty(mCurrentGameCode) && !EfunStringUtil.isEmpty(mQuestionType)) {
					serverClickFlag = true;
					requestFlag = GAIN_SERVERS;
					requestWithoutDialog(needRequestDataBean());
					rolesTxt.setText(getString(E_string.efun_pd_chose_role));
					mCurrentRoleId = "";
				} else {
					ToastUtils.toast(mContext, getResources().getString(E_string.efun_pd_cs_hints_ask_select_game));
				}
			}
		});
		choseRoleLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				if(roleOldTime == 0){
					roleOldTime = System.currentTimeMillis();
            	}else if((System.currentTimeMillis() - roleOldTime) < 1000){
            		roleOldTime = System.currentTimeMillis();
            		return;
            	}
				if((!EfunStringUtil.isEmpty(mCurrentGameCode) && !EfunStringUtil.isEmpty(mQuestionType) && mCurrentGameCode.equals(HttpParam.PLATFORM_CODE))){
//					ToastUtils.toast(mContext, getResources().getString(E_string.efun_pd_cs_hints_ask_select_server));
				}else if (!EfunStringUtil.isEmpty(mCurrentServerCode) && !EfunStringUtil.isEmpty(mCurrentGameCode) && !EfunStringUtil.isEmpty(mQuestionType)) {
					requestFlag = GAIN_ROLE;
					if(GameToPlatformParamsSaveUtil.getInstanse().getLoginType().equals(LoginPlatform.FACEBOOK)){
						mFBRoleInfos = new ArrayList<CsGainFBRoleItemBean>();
						mRealCountFBRoles = 0;
						requestFlag = GAIN_ROLE_FB;
						String[] FBUids = getFBUids();
						EfunLogUtil.logI("efun", "=====FBUids:"+FBUids);						
						if(FBUids != null){							
							mFBCountRoles = FBUids.length;
						}
						if(FBUids != null){
							if(FBUids.length != 0){								
								for(int i = 0 ; i < FBUids.length ; i++){
									if(!EfunStringUtil.isEmpty(FBUids[i])){
										mCurrentUid = FBUids[i];
										Log.i("efun", "mCurrentUid:"+mCurrentUid);
										requestWithoutDialog(needRequestDataBean());
									}
								}
							}else{
								mCurrentUid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
								requestWithoutDialog(needRequestDataBean());
//								ToastUtils.toast(mContext, getResources().getString(E_string.efun_pd_cs_hints_ask_select_server));
							}
						}else{
							mCurrentUid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
							requestWithoutDialog(needRequestDataBean());
//							ToastUtils.toast(mContext, getResources().getString(E_string.efun_pd_cs_hints_ask_select_server));
						}
					}else
						requestWithoutDialog(needRequestDataBean());
				} else {
					ToastUtils.toast(mContext, getResources().getString(E_string.efun_pd_cs_hints_ask_select_server));
				}
			}
		});
		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				content = descriptionEdit.getText().toString();
				phoneNum = phoneEdit.getText().toString();
				email = emailEdit.getText().toString();
				
				if(EfunStringUtil.isEmpty(mCurrentGameCode)){
					ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_toast_empty_game));
					return;
				}
				if(mCurrentGameCode.equals(HttpParam.PLATFORM_CODE)){
					mCurrentServerCode = HttpParam.PLATFORM_CODE+HttpParam.PLATFORM_APP_SERVERCODE;
					mCurrentRoleId = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
					mCurrentGameUid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
				}
				if(EfunStringUtil.isEmpty(mCurrentServerCode)){
					ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_toast_empty_server));
					return;
				}
				if(EfunStringUtil.isEmpty(mCurrentRoleId)){
					ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_toast_empty_role));
					return;
				}
				if(EfunStringUtil.isEmpty(content)){
					ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_toast_empty_content));
					return;
				}
				requestWithoutDialog(createQuestionRequest());
			}
		});
		//fb登录时请求
		if(!EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getLoginType())){
			if((GameToPlatformParamsSaveUtil.getInstanse().getLoginType()).equals(LoginPlatform.FACEBOOK)){			
				requestWithoutDialog(needRequestDataBean());
			}
		}
		
		if((!EfunStringUtil.isEmpty(mCurrentGameCode) && !EfunStringUtil.isEmpty(mQuestionType) && mCurrentGameCode.equals(HttpParam.PLATFORM_CODE))){
		}else if (!EfunStringUtil.isEmpty(mCurrentGameCode) && !EfunStringUtil.isEmpty(mQuestionType)) {
			requestFlag = GAIN_SERVERS;
			requestWithoutDialog(needRequestDataBean());
		}
		
	}
	
	private String[] getFBUids(){
		String[] keys = null;
		if(!EfunStringUtil.isEmpty(mFBUids) && !mFBUids.equals("{}")){
			try {
				JSONObject jsonObject = new JSONObject(mFBUids);
				Log.i("efun", "mFBUids:"+mFBUids);
				JSONArray jsonArray = jsonObject.optJSONArray(mCurrentGameCode);
				if(jsonArray != null){					
					keys = new String[jsonArray.length()];
					for(int i = 0 ; i < jsonArray.length() ; i++){
						jsonObject = jsonArray.optJSONObject(i);
						Iterator it = jsonObject.keys();
						while(it.hasNext()){
							keys[i] = (String) it.next();
						}
					}
				}
			} catch (JSONException e) {
				return null;
			}
		}
		return keys;
	}

	@Override
	public boolean needRequestData() {
		return false;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		if (requestFlag == GAIN_GAMES) {
			CsGainGameListRequest csRequest = new CsGainGameListRequest(Const.HttpParam.CS_DEPT_TW, Const.HttpParam.CS_GPID, Const.HttpParam.APP, Const.HttpParam.CS_PID);
			csRequest.setReqType(IPlatformRequest.REQ_CS_GAIN_GAMES);
			return new BaseRequestBean[] { csRequest };
		} else if (requestFlag == GAIN_SERVERS) {
			CsGainServerListRequest csRequest = new CsGainServerListRequest(mCurrentGameCode, HttpParam.CS_DEPT_TW, Const.HttpParam.APP);
			csRequest.setReqType(IPlatformRequest.REQ_CS_GAIN_SERVER);
			return new BaseRequestBean[] { csRequest };
		} else if (requestFlag == GAIN_ROLE) {
			CsGainRoleListRequest csRequest = new CsGainRoleListRequest(mCurrentGameCode, mCurrentServerCode);
			csRequest.setReqType(IPlatformRequest.REQ_CS_GAIN_ROLE);
			return new BaseRequestBean[] { csRequest };
		} else if (requestFlag == GAIN_ROLE_FB) {
			CsGainRoleListRequest csRequest = new CsGainRoleListRequest(mCurrentGameCode, mCurrentServerCode);
			csRequest.setUid(mCurrentUid);
			csRequest.setReqType(IPlatformRequest.REQ_CS_GAIN_ROLE);
			return new BaseRequestBean[] { csRequest };
		} 
		if(GameToPlatformParamsSaveUtil.getInstanse().getLoginType().equals(LoginPlatform.FACEBOOK)){	
			AccountGetUserFBUidsByUidRequest request = new AccountGetUserFBUidsByUidRequest();
			request.setReqType(IPlatformRequest.REQ_CS_GET_FB_USER_INFO);
			request.setGameCode(HttpParam.PLATFORM_CODE);
			request.setCrossdomain(false);
			request.setUserId(GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid());
			return new BaseRequestBean[] { request };
		}else{
			return null;
		}
	}

	// 参数选择列表对话框
	public void paramListDialog(final String[] params, Context mContext, final OnDialogSelect listener) {
		// 新建AlertDialog对话框
		new AlertDialog.Builder(mContext)
		// .setCancelable(false)
				.setItems(params, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (listener != null) {
							listener.onSelect(which);
						}
					}
				}).show();
	}

	private interface OnDialogSelect {
		public void onSelect(int postion);
	}
	
	@Override
	public void onFailure(int requestType) {
		super.onFailure(requestType);
		if (requestType == IPlatformRequest.REQ_CS_GAIN_ROLE) {
			if(GameToPlatformParamsSaveUtil.getInstanse().getLoginType().equals(LoginPlatform.FACEBOOK)){				
				mRealCountFBRoles++;
			}
		}
	}

	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType, baseResponse);
		if (requestType == IPlatformRequest.REQ_CS_GAIN_GAMES) {
			CsGainGameListResponse response = (CsGainGameListResponse) baseResponse;
			CsGainGameBean mCsGainGameBean = response.getCsGainGameBean();
			if(mCsGainGameBean.getCode().equals(HttpParam.RESULT_1000)){
				// 获取游戏列表信息
				mGameInfos = mCsGainGameBean.getGameList();
				String[] params = new String[mGameInfos.size()];
				// 获取游戏名
				for (int i = 0; i < mGameInfos.size(); i++) {
					params[i] = mGameInfos.get(i).getGameName();
				}
				if(!EfunStringUtil.isEmpty(mCurrentGameCode)){
					for(int i = 0 ; i < mGameInfos.size(); i++){
						if(mCurrentGameCode.equals(mGameInfos.get(i).getGameCode())){
							gamesTxt.setText(mGameInfos.get(i).getGameName());
							return;
						}
					}
				}
				if (params.length > 0) {
					paramListDialog(params, mContext, new OnDialogSelect() {
						@Override
						public void onSelect(int postion) {
							gamesTxt.setText(mGameInfos.get(postion).getGameName());
							mCurrentGameCode = mGameInfos.get(postion).getGameCode();
							if(mCurrentGameCode.equals(HttpParam.PLATFORM_CODE)){
								choseServerLayout.setClickable(false);
								choseRoleLayout.setClickable(false);
								choseServerLayout.setBackgroundResource(E_drawable.efun_pd_input_bg);
								choseRoleLayout.setBackgroundResource(E_drawable.efun_pd_input_bg);								
							}else{
								choseServerLayout.setClickable(true);
								choseRoleLayout.setClickable(true);
								choseServerLayout.setBackgroundResource(E_drawable.efun_pd_white_botton_selector);
								choseRoleLayout.setBackgroundResource(E_drawable.efun_pd_white_botton_selector);
							}
							serversTxt.setText(getString(E_string.efun_pd_chose_server));
							rolesTxt.setText(getString(E_string.efun_pd_chose_role));
							mCurrentServerCode = "";
							mCurrentRoleId = "";
						}
					});
				} else {
					ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_game));
				}
			}else{
				ToastUtils.toast(mContext, mCsGainGameBean.getMessage());
			}
			
		} else if (requestType == IPlatformRequest.REQ_CS_GAIN_SERVER) {
			CsGainServersListResponse response = (CsGainServersListResponse) baseResponse;
			CsGainServerBean mCsGainServerBean = response.getCsGainServerBean();
			if(mCsGainServerBean.getCode().equals(HttpParam.RESULT_200)){
				// 获取服务器列表信息
				mServerInfos = mCsGainServerBean.getServerList();
				
				if(!serverClickFlag){
					
					if(!EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getServerCode())){
						if(mServerInfos != null){							
							for(int i = 0; i < mServerInfos.size(); i++){
								if(GameToPlatformParamsSaveUtil.getInstanse().getServerCode().equals(mServerInfos.get(i).getServerCode())){
									serversTxt.setText(mServerInfos.get(i).getServerName());
									mCurrentServerCode = mServerInfos.get(i).getServerCode();
									requestFlag = GAIN_ROLE;
									requestWithoutDialog(needRequestDataBean());
									return;
								}
							}
							if(EfunStringUtil.isEmpty(mCurrentServerCode)){
								Log.e("efun", "ServerCode傳入有誤!"+GameToPlatformParamsSaveUtil.getInstanse().getServerCode());
								ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_server));
								return;
							}
						}else{
							ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_server));
							return;	
						}
					}else{
						ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_server));
						return;						
					}
					
				}else{
					String[] params = new String[mServerInfos.size()];
					// 获取服务器名
					for (int i = 0; i < mServerInfos.size(); i++) {
						params[i] = mServerInfos.get(i).getServerName();
					}												
					if (params.length > 0) {
						paramListDialog(params, mContext, new OnDialogSelect() {
							@Override
							public void onSelect(int postion) {
								serversTxt.setText(mServerInfos.get(postion).getServerName());
								mCurrentServerCode = mServerInfos.get(postion).getServerCode();
							}
						});
					} else {
						ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_server));
					}
				}				
			}else{
				ToastUtils.toast(mContext, mCsGainServerBean.getMessage());
			}

		} else if (requestType == IPlatformRequest.REQ_CS_GAIN_ROLE) {
			CsGainRoleListResponse response = (CsGainRoleListResponse) baseResponse;
			CsGainRoleBean mCsGainRoleBean = response.getCsGainRoleBean();
			if(mCsGainRoleBean.getCode().equals(HttpParam.RESULT_1000)){
				if(GameToPlatformParamsSaveUtil.getInstanse().getLoginType().equals(LoginPlatform.FACEBOOK)){				
					mRealCountFBRoles++;
					
					mRoleInfos = mCsGainRoleBean.getRoleList();
					for (int i = 0; i < mRoleInfos.size(); i++) {
							mFBRoleInfos.add(new CsGainFBRoleItemBean(mRoleInfos.get(i), mCurrentUid));
					}
					if(mRealCountFBRoles == mFBCountRoles || mFBCountRoles == 0 ){
						String[] roles = new String[mFBRoleInfos.size()];
						for(int i = 0 ; i < mFBRoleInfos.size(); i++){
							roles[i] = mFBRoleInfos.get(i).getBean().getName();
						}
						if (roles.length > 0) {
							if(roles.length == 1){
								//當只有一個角色的時候，直接自動填上
								rolesTxt.setText(mFBRoleInfos.get(0).getBean().getName());
								mCurrentRoleId = mFBRoleInfos.get(0).getBean().getRoleid();
								mCurrentGameUid = mFBRoleInfos.get(0).getGameFBUid();
							}else{								
								paramListDialog(roles, mContext, new OnDialogSelect() {
									
									@Override
									public void onSelect(int postion) {
										rolesTxt.setText(mFBRoleInfos.get(postion).getBean().getName());
										mCurrentRoleId = mFBRoleInfos.get(postion).getBean().getRoleid();
										mCurrentGameUid = mFBRoleInfos.get(postion).getGameFBUid();
									}
								});	
							}
						}else {
							ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_role));
						}
					}
					
				}else{					
					// 获取角色列表信息
					mRoleInfos = mCsGainRoleBean.getRoleList();
					String[] params = new String[mRoleInfos.size()];
					// 获取角色名
					for (int i = 0; i < mRoleInfos.size(); i++) {
						params[i] = mRoleInfos.get(i).getName();
					}
					if (params.length > 0) {
						if(params.length == 1){
							rolesTxt.setText(mRoleInfos.get(0).getName());
							mCurrentRoleId = mRoleInfos.get(0).getRoleid();
							mCurrentGameUid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
						}else{
							paramListDialog(params, mContext, new OnDialogSelect() {
								
								@Override
								public void onSelect(int postion) {
									rolesTxt.setText(mRoleInfos.get(postion).getName());
									mCurrentRoleId = mRoleInfos.get(postion).getRoleid();
									mCurrentGameUid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
								}
							});
						}
					} else {
						ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_error_empty_role));
					}
				}
			}else{
				ToastUtils.toast(mContext, mCsGainRoleBean.getMessage());
			}
			
		} else if (requestType == IPlatformRequest.REQ_CS_ASK) {
			CsAskResponse response = (CsAskResponse) baseResponse;
			String code = response.getResponse().getCode();
			if (!EfunStringUtil.isEmpty(code)) {
				if (code.equals(HttpParam.RESULT_1000)) {
					ToastUtils.toast(mContext, getString(E_string.efun_pd_cs_hints_ask_ok));
					finish();
				}
			}
		}
		if(requestType==IPlatformRequest.REQ_CS_GET_FB_USER_INFO){
			AccountGetUserFBUidsByUidResponse userFBUidsByUidResponse = (AccountGetUserFBUidsByUidResponse) baseResponse;
			CsGetFBUserInfosBean mFBUserInfo = userFBUidsByUidResponse.getResponse();
			if(mFBUserInfo.getCode().equals(HttpParam.RESULT_1000)){
				if(!EfunStringUtil.isEmpty(mFBUserInfo.getRelation())){
					mFBUids = mFBUserInfo.getRelation();
				}
			}
		}
	}	

	@Override
	public ViewGroup[] needShowLoading() {
		return null;
	}

	@Override
	public int LayoutId() {
		return E_layout.efun_pd_cs_ask;
	}

	@Override
	public void initTitle(TitleView titleView) {
		titleView.setTitleRightStatus(View.GONE);
		titleView.setTitleCenterRes(E_string.efun_pd_ask, false);
	}
	private BaseRequestBean[] createQuestionRequest(){
			int length = content.length();
			// 标题
			String title = null;
			if (length < 14) {
				title = content;
			} else {
				char[] strChar = content.toCharArray();
				char[] chars = new char[14];
				for (int i = 0; i < chars.length; i++) {
					chars[i] = strChar[i];
				}
				title = String.valueOf(chars);
			}
			CsAskRequest csRequest = new CsAskRequest(mQuestionType, HttpParam.PLATFORM_AREA, mCurrentGameCode, title, content, HttpParam.APP);
			csRequest.setServerCode(mCurrentServerCode);
			csRequest.setIsMobile("true");
			csRequest.setEmail(email);
			csRequest.setContactWay(phoneNum);
			csRequest.setRoleId(mCurrentRoleId);
			csRequest.setGameUid(mCurrentGameUid);
			csRequest.setReqType(IPlatformRequest.REQ_CS_ASK);
			return new BaseRequestBean[] { csRequest };
	}


}
