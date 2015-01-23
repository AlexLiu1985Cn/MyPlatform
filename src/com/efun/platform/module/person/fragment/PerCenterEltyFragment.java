package com.efun.platform.module.person.fragment;

import java.util.HashMap;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunLogUtil;
import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.FrameTabContainer;
import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_dimens;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.IPlatController;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.IPlatRequest;
import com.efun.platform.http.request.bean.AccountLoginRequest;
import com.efun.platform.http.request.bean.AccountUpdateRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.response.bean.AccountUpdateResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.account.activity.BindPhoneActivity;
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.base.BaseFragment;
import com.efun.platform.module.base.impl.OnRequestListener;
import com.efun.platform.module.base.impl.OnUpdateUserListener;
import com.efun.platform.module.utils.IntentUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.LoginPlatform;
import com.efun.platform.utils.Const.Web;
import com.efun.platform.widget.ConsProgressBar;
import com.efun.platform.widget.scroll.PullScrollView;
/**
 * 个人中心页面
 * @author Jesse
 *
 */
public class PerCenterEltyFragment extends BaseFragment implements PullScrollView.OnTurnListener{
	private PullScrollView mPullScrollView;
	private ConsProgressBar mConsProgressBar;
	private ImageView mPullHeaderView;
	private RelativeLayout mLayout;
	private ImageView mHeader,mRecharge,mPhoneIcon;
	private TextView mUserName,mUserAccount,mUserLevel,mPhoneStatus;
	private TextView mUpper,mCredit,mSpePower,mPhone;
	private LinearLayout mPhoneLayout,mCreditLayout,mSpePowerLayout,mVipLayout;
	private User mUser;
	private View dialogView,lineView;


	@Override
	public void initViews() {
		mLayout = (RelativeLayout) childView.findViewById(E_id.contaier_relative_1);
		mPullScrollView = (PullScrollView) childView.findViewById(E_id.pullScrollView);
		mPullHeaderView = (ImageView) childView.findViewById(E_id.imageview);
		mPullScrollView.setHeader(mPullHeaderView);
		mPullScrollView.setOnTurnListener(this);
		mConsProgressBar = (ConsProgressBar) childView.findViewById(E_id.person_consProgressBar);
		
		
		mHeader= (ImageView) childView.findViewById(E_id.icon_1);
		mRecharge= (ImageView) childView.findViewById(E_id.icon_2);
		mPhoneIcon= (ImageView) childView.findViewById(E_id.icon_3);
		mUserName= (TextView) childView.findViewById(E_id.text_3);
		mUserAccount= (TextView) childView.findViewById(E_id.text_1);
		mUserLevel= (TextView) childView.findViewById(E_id.text_2);
		mPhoneStatus= (TextView) childView.findViewById(E_id.text_4);
		mUpper= (TextView) childView.findViewById(E_id.item_text);
		mCredit= (TextView) childView.findViewById(E_id.item_content);
		mSpePower= (TextView) childView.findViewById(E_id.item_title);
		mPhone= (TextView) childView.findViewById(E_id.item_category);
		lineView = (View)childView.findViewById(E_id.lineview);
		mPhoneLayout= (LinearLayout) childView.findViewById(E_id.contaier_linear_1);
		mCreditLayout= (LinearLayout) childView.findViewById(E_id.contaier_linear_2);
		mSpePowerLayout= (LinearLayout) childView.findViewById(E_id.contaier_linear_3);
		mVipLayout= (LinearLayout) childView.findViewById(E_id.contaier_linear_4);
		
		if(GameToPlatformParamsSaveUtil.getInstanse().isRechargeBtnFlag()){
			mRecharge.setVisibility(View.VISIBLE);
		}else{
			mRecharge.setVisibility(View.GONE);
		}
		//储值
		mRecharge.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				IntentUtils.go2ReChargeWeb(getActivity(), Web.WEB_GO_PERSON_RECHAR);
			}
		});
		
		mPhoneLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtils.goWithBeanForResult(getActivity(), BindPhoneActivity.class, mUser,new OnUpdateUserListener() {
					@Override
					public void onUpdate(User userInfo) {
						GameToPlatformParamsSaveUtil.getInstanse().setUser(userInfo);
					}
				});
			}
		});
		mCreditLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtils.go2Web(getActivity(), Web.WEB_GO_GOLDVALUE,null);
			}
		});
		mVipLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtils.go2Web(getActivity(), Web.WEB_GO_VIP,null);
			}
		});
	}
	

	@Override
	public void onResume() {
		super.onResume();
		displayUserInfo();
	}
	


	@Override
	public int LayoutId() {
		return E_layout.efun_pd_persion;
	}

	@Override
	public void onTurn() {
		// TODO Auto-generated method stub
		
	}
	private void showDialog(){
		dismissDialog();
		View loading = ViewUtils.createLoading(getActivity());
		dialogView = loading;
		mLayout.addView(loading, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}
	private void dismissDialog(){
		if(dialogView!=null){
			mLayout.removeView(dialogView);
		}
		dialogView = null;
	}
	public void displayUserInfo(){
		mUser = GameToPlatformParamsSaveUtil.getInstanse().getUser();
		if(mUser==null){
//			AccountLoginRequest loginRequest = UserUtils.createRequest(getActivity());
			showDialog();
		}else{
			if(GameToPlatformParamsSaveUtil.getInstanse().isRechargeBtnFlag()){
				mRecharge.setVisibility(View.VISIBLE);
			}else{
				mRecharge.setVisibility(View.GONE);
			}
			if(EfunStringUtil.isEmpty(mUser.getIcon())){
				if(mUser.getSex().equals(getString(E_string.efun_pd_sex_girl))){
					mHeader.setImageResource(E_drawable.efun_pd_default_user_girl_icon);
				}else{
					mHeader.setImageResource(E_drawable.efun_pd_default_user_boy_icon);
				}
			}else{
				ImageManager.displayImage(mUser.getIcon(), mHeader,ImageManager.IMAGE_SQUARE,getResources().getDimensionPixelSize(E_dimens.e_corners_radius_30));
			}
			
			if(EfunStringUtil.isEmpty(mUser.getUsername())){
				mUserName.setText(E_string.efun_pd_empty_nick);
			}else{
				mUserName.setText(mUser.getUsername());
			}
			
			if(mUser.getLoginType().equals(LoginPlatform.FACEBOOK)){
				mUserAccount.setText(E_string.efun_pd_hint_account_by_facebook);
			}else if(mUser.getLoginType().equals(LoginPlatform.BAHAMUT)){
				mUserAccount.setText(E_string.efun_pd_hint_account_by_bahamut);
			}else if(mUser.getLoginType().equals(LoginPlatform.GOOGLE)){
				mUserAccount.setText(E_string.efun_pd_hint_account_by_google);
			}else{
				mUserAccount.setText(mUser.getAccountName());				
			}
			
			mUserLevel.setText(getString(E_string.efun_pd_user_level)+mUser.getRango());
			mUpper.setText(mUser.getCurrentExp()+"/"+mUser.getLevelupExp());
			mCredit.setText(mUser.getGoldValue()+"");
			mSpePower.setText(mUser.getPrivilege());
			EfunLogUtil.logI("efun","mUser.toString():"+mUser.toString());
			EfunLogUtil.logI("efun","mUser.getExpPercentage():"+mUser.getExpPercentage());
			mConsProgressBar.setProgress(mUser.getExpPercentage()); 
			if(EfunStringUtil.isEmpty(mUser.getPhone())){
				mPhoneIcon.setBackgroundResource(E_drawable.efun_pd_persion_phone_normal);
				mPhoneLayout.setVisibility(View.VISIBLE);
				lineView.setVisibility(View.VISIBLE);
				mPhoneStatus.setTextColor(E_color.e_8e8e8e);
				mPhoneStatus.setText(E_string.efun_pd_phone_bind_uncompleted);
			}else{
				mPhoneIcon.setBackgroundResource(E_drawable.efun_pd_persion_phone_focus);
				mPhoneLayout.setVisibility(View.GONE);
				lineView.setVisibility(View.GONE);
				mPhoneStatus.setTextColor(E_color.e_b45b3e);
				mPhoneStatus.setText(E_string.efun_pd_phone_bind_completed);
			}
			//Vip item的显示与否
			if(!(mUser.getIsVip()).equals("0")){
				mVipLayout.setVisibility(View.GONE);
			}else{
				mVipLayout.setVisibility(View.VISIBLE);
			}
			dismissDialog();
			if(!EfunStringUtil.isEmpty(mUser.getGotGold()) && !mUser.getGotGold().equals("0") && !mUser.getGotGold().equals("null")){
				ViewUtils.createToast(getActivity(), getString(E_string.efun_pd_credit)+" + "+mUser.getGotGold());
			}
		}
	}


	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onFailure(int requestType) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTimeout(int requestType) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initDatas() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onNoData(int requestType, String noDataNotify) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void updatePersionInfo(){
		if(allow){
			allow = false;
			IPlatController mController = new IPlatController(getActivity());
			mController.executeAll(null, createIPlatRequests(needRequestBean()), new OnRequestListener() {
				@Override
				public void onSuccess(int requestType, BaseResponseBean baseResponse) {
					if(requestType==IPlatformRequest.REQ_ACCOUNT_UPDATE){
						AccountUpdateResponse response = (AccountUpdateResponse) baseResponse;
						ResultBean result = response.getResultBean();		
						if(result.getCode().equals(HttpParam.RESULT_1000)){
							UserUtils.initUser(getActivity());
						}
					}
					allow = true;
				}
				
				@Override
				public void onFailure(int requestType) {
					allow = true;
				}

				@Override
				public void onTimeout(int requestType) {
					allow = true;
				}

				@Override
				public void onNoData(int requestType, String noDataNotify) {
					// TODO Auto-generated method stub
					allow = true;
				}
			});
		}
		
	}
	private boolean allow = true;
	private IPlatRequest[] createIPlatRequests(BaseRequestBean[] requests){
		IPlatRequest[] mRequests = new IPlatRequest[requests.length];
		for (int i = 0; i < mRequests.length; i++) {
			mRequests[i] = new IPlatRequest(getActivity()).setRequestBean(requests[i]);
		}
		return mRequests;
	}
	private BaseRequestBean[] needRequestBean(){
		String[] etagKeys = new String[]{"uid","username","password","loginType","thirdId","apps"};
		HashMap<String, String > paramsMap = Store.createValuesByClazz(getActivity(), etagKeys, AccountLoginRequest.class);
		AccountUpdateRequest updateRequest = new AccountUpdateRequest(getActivity());
		updateRequest.setLoginType(paramsMap.get("loginType"));
		updateRequest.setThirdId(paramsMap.get("thirdId"));
		updateRequest.setApps(paramsMap.get("apps"));
		updateRequest.setPfArea(HttpParam.PLATFORM_AREA);
		updateRequest.setDevice(HttpParam.PHONE);
		updateRequest.setReqType(IPlatformRequest.REQ_ACCOUNT_UPDATE);
		return null;
	}

}
