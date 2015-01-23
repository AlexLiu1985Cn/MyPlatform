package com.efun.platform.module.welfare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.AccountBindPhoneRequest;
import com.efun.platform.http.request.bean.AccountBindPhoneSendVcodeRequest;
import com.efun.platform.http.request.bean.ActivityExtensionDownloadRequest;
import com.efun.platform.http.request.bean.ActivityExtensionGiftRequest;
import com.efun.platform.http.request.bean.ActivityExtensionRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.GiftSelfStatusRequest;
import com.efun.platform.http.response.bean.AccountBindPhoneResponse;
import com.efun.platform.http.response.bean.ActivityExtensionDownloadResponse;
import com.efun.platform.http.response.bean.ActivityExtensionGiftResponse;
import com.efun.platform.http.response.bean.ActivityExtensionResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.GiftSelfStatusResponse;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.PopWindow;
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.base.FixedActivity;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.game.bean.GameItemBean;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.module.utils.PopUtils;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.module.welfare.bean.ActExtensionBean;
import com.efun.platform.module.welfare.bean.ActExtensionDownloadBean;
import com.efun.platform.module.welfare.bean.ActExtensionGiftBean;
import com.efun.platform.module.welfare.bean.ActExtensionGiftGetBean;
import com.efun.platform.module.welfare.bean.GiftSelfStatusBean;
import com.efun.platform.module.welfare.view.TaskContainer;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.widget.RoundCornerTextView;
import com.efun.platform.widget.TitleView;
import com.efun.platform.widget.list.XListView;
/**
 * 领取点卡奖励
 * @author Jesse
 */
public class ActExtensionActivity extends FixedActivity{
	/**
	 * 任务未完成
	 */
	private final String GIFT_GET_UNCOMPLETED = "0";
	/**
	 * 任务已领取
	 */
	private final String GIFT_GET_GETTED = "2";
	
	private HeaderViewHolder mHeaderViewHolder;
	private FooterViewHolder mFooterViewHolder;
	private BodyViewHolder mBodyViewHolder;
	private PopWindow mAwardsPopWindow;
	private XListView mListView;
	private View mFooterView;
	private String mPhoneNum, mCodeNum;
	private User mUser;
	private LinearLayout childView;
	private TitleView mTitleView;
	@Override
	public boolean needRequestData() {
		return true;
	}
	@Override
	public BaseRequestBean[] needRequestDataBean() {
		ActivityExtensionRequest request = new ActivityExtensionRequest(HttpParam.PHONE, HttpParam.PLATFORM_AREA);
		request.setReqType(IPlatformRequest.REQ_ACT_EXTENSION);
		return new BaseRequestBean[]{request};
	}
	@Override
	public void init(Bundle bundle) {
		childView = (LinearLayout) findViewById(E_id.contaier_linear_1);
		View view = ViewUtils.createView(mContext, E_layout.efun_pd_welfare_act_extension_content);
		mListView = (XListView)findViewById(E_id.list);
		mListView.setPullRefreshEnable(false);
		mListView.setPullLoadEnable(false);
		mListView.addHeaderView(view);
		mListView.setAdapter(null);
		mUser = GameToPlatformParamsSaveUtil.getInstanse().getUser();
		initHeader(view);
		initBody(view);
		initFooter();
	}
	@Override
	public ViewGroup[] needShowLoading() {
		return new ViewGroup[]{(ViewGroup)childView};
	}
	@Override
	public int LayoutId() {
		return E_layout.efun_pd_welfare_act_extension;
	}
	
	
	private BaseRequestBean[] createDownloadRequest(){
		ActivityExtensionDownloadRequest request = new ActivityExtensionDownloadRequest(
				mContext, mGameItemBean.getGameCode(), GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid());
		request.setReqType(IPlatformRequest.REQ_ACT_EXTENSION_DOWNLOAD);
		return new BaseRequestBean[]{request};
	}
	
	private void queryGiftSelfStatus(){
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser()!=null && !GameToPlatformParamsSaveUtil.getInstanse().isNewStatusOfGiftSelf()){
			GiftSelfStatusRequest request = new GiftSelfStatusRequest(
					GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid(), 
					HttpParam.GIFT_STATUS_QUERY);
			request.setReqType(IPlatformRequest.REQ_GIFT_SELF_STATUS);
			requestWithoutDialog(new BaseRequestBean[]{request});
		}
	}
	
	private GameItemBean mGameItemBean;
	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType,baseResponse);
		if(requestType==IPlatformRequest.REQ_ACT_EXTENSION_DOWNLOAD){
			ActivityExtensionDownloadResponse response = (ActivityExtensionDownloadResponse)baseResponse;
			ActExtensionDownloadBean bean = response.getActExtensionDownloadBean();
			if(bean.getCode().equals(HttpParam.RESULT_1000) && !EfunStringUtil.isEmpty(bean.getParams())){
				String url = mGameItemBean.getAndroidDownload();
				if(mGameItemBean.getAndroidDownload().contains("referrer")){
					url = url + bean.getParams();
				}else{
					url = url + "&referrer=utm_source%3D%2520%26utm_medium%3D%2520" + bean.getParams();
				}
				AppUtils.download(mContext, url);
			}else{
				ToastUtils.toast(mContext, bean.getMessage());
			}
		}else if (requestType==IPlatformRequest.REQ_ACT_EXTENSION){
			ActivityExtensionResponse response = (ActivityExtensionResponse)baseResponse;
			final ActExtensionBean mActExtensionBean = response.getActExtensionBean();
			if(mActExtensionBean.getGiftsLastCount() == 0){
				mHeaderViewHolder.mGiftRobbled.setVisibility(View.VISIBLE);
			}else{
				mHeaderViewHolder.mGiftRobbled.setVisibility(View.GONE);
			}
			mGameItemBean = mActExtensionBean.getGameBean();
			ImageManager.displayImage(mGameItemBean.getSmallpic(), mHeaderViewHolder.mIcon,ImageManager.IMAGE_SQUARE);
			mHeaderViewHolder.mTitle.setText(mGameItemBean.getGameName());
			mHeaderViewHolder.mContent.setText(getString(E_string.efun_pd_game_size)+ mGameItemBean.getPackageSize());
			mHeaderViewHolder.mCategory.setColor(getResources().getColor(E_color.e_00aaeb));
			mHeaderViewHolder.mCategory.setText(mGameItemBean.getGameType());
			mHeaderViewHolder.mButton.setBackgroundResource(E_drawable.efun_pd_game_download_selector);
			mHeaderViewHolder.mText.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});

			mHeaderViewHolder.mButton.setBackgroundResource(E_drawable.efun_pd_game_download_selector);
			mHeaderViewHolder.mButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TrackingUtils.track(TrackingUtils.ACTION_WELFARE, mGameItemBean.getGameName()+TrackingUtils.NAME_GAME_DOWNLOAD);
					if(AppUtils.isGooglePayInstalled(mContext)){
						requestWithDialog(createDownloadRequest(), getString(E_string.efun_pd_loading_msg_commend));
					}else{
						ToastUtils.toast(mContext, getString(E_string.efun_pd_uninstalled_google_play));
					}
				}
			});
			
			mBodyViewHolder.mTaskLayout.loadedData(mActExtensionBean.getArrayOfTask());
			if(mAwardsPopWindow==null){
				if(mActExtensionBean.getArrayOfGift().size()==0){
					ToastUtils.toast(mContext, getString(E_string.efun_pd_toast_empty_gifts));
				}else{
					if(mActExtensionBean.getCurrentState().equals(GIFT_GET_GETTED)){
						mFooterViewHolder.mText.setText(E_string.efun_pd_gift_getted);
						mFooterView.setBackgroundResource(E_color.e_8e8e8e);
						mFooterView.setEnabled(false);
					}
					mFooterView.setVisibility(View.VISIBLE);
					mAwardsPopWindow = PopUtils.createChoseAwards(mContext, 
							mActExtensionBean.getArrayOfGift(),
							mFooterView, new OnEfunItemClickListener() {
								@Override
								public void onItemClick(int position) {
									if(mActExtensionBean.getCurrentState().equals(GIFT_GET_UNCOMPLETED)){
										ViewUtils.createToast(mContext, getString(E_string.efun_pd_gift_limit));
									}else{
										requestWithDialog(createRequest(mActExtensionBean.getArrayOfGift().get(position)),
												getString(E_string.efun_pd_loading_msg_commend));
									}
								}
							});
				}
			}
			mBodyViewHolder.mRuleIntro.setText(mActExtensionBean.getContext());
			queryGiftSelfStatus();
		}else if(requestType==IPlatformRequest.REQ_ACT_EXTENSION_GIFT){
			ActivityExtensionGiftResponse response  = (ActivityExtensionGiftResponse)baseResponse;
			ActExtensionGiftGetBean bean = response.getActExtensionGiftGetBean();
			if(bean.getCode().equals(HttpParam.RESULT_1000)){
				mFooterView.performClick();
				mFooterView.setBackgroundResource(E_color.e_8e8e8e);
				mFooterView.setEnabled(false);
			}
			ToastUtils.toast(mContext, bean.getMessage());
		}else if(requestType==IPlatformRequest.REQ_ACCOUNT_BIND_PHONE_SEND_VCODE){
			AccountBindPhoneResponse response = (AccountBindPhoneResponse) baseResponse;
			ResultBean result = response.getResultBean();
			ToastUtils.toast(mContext, result.getMessage());
		}else if(requestType==IPlatformRequest.REQ_ACCOUNT_BIND_PHONE){
			AccountBindPhoneResponse response = (AccountBindPhoneResponse) baseResponse;
			ResultBean result = response.getResultBean();
			ToastUtils.toast(mContext, result.getMessage());
			if(result.getCode().equals(HttpParam.RESULT_1000)){
				mUser.setPhone(mPhoneNum);
				GameToPlatformParamsSaveUtil.getInstanse().setUser(mUser);
				mBodyViewHolder.mAccountEdit.setText("");
				mBodyViewHolder.mCodeEdit.setText("");
//				mContext.finish();
			}
		}else if(requestType==IPlatformRequest.REQ_GIFT_SELF_STATUS){
			GiftSelfStatusResponse response = (GiftSelfStatusResponse)baseResponse;
			GiftSelfStatusBean mGiftSelfStatusBean = response.getGiftSelfStatusBean();
			if(mGiftSelfStatusBean.getCode().equals(HttpParam.RESULT_1000)){
				GameToPlatformParamsSaveUtil.getInstanse().setNewStatusOfGiftSelf(true);
				mTitleView.setTitleRightPointStatus(View.VISIBLE);
			}
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(GameToPlatformParamsSaveUtil.getInstanse().isNewStatusOfGiftSelf()){
			mTitleView.setTitleRightPointStatus(View.VISIBLE);
		}else{
			mTitleView.setTitleRightPointStatus(View.GONE);
		}
	}
	
	/**
	 * 初始化页面头部
	 */
	private void initHeader(View convertView){
		mHeaderViewHolder = new HeaderViewHolder();
		mHeaderViewHolder.mIcon = (ImageView) convertView.findViewById(E_id.item_icon);
		mHeaderViewHolder.mTitle = (TextView) convertView.findViewById(E_id.item_title);
		mHeaderViewHolder.mContent = (TextView) convertView.findViewById(E_id.item_content);
		mHeaderViewHolder.mCategory = (RoundCornerTextView) convertView.findViewById(E_id.round_text);
		mHeaderViewHolder.mButton = (ImageView) convertView.findViewById(E_id.item_button);
		mHeaderViewHolder.mText = (ImageView) convertView.findViewById(E_id.item_text);
		mHeaderViewHolder.mGiftRobbled = (ImageView) convertView.findViewById(E_id.gift_robble);
	}
	private void initBody(View convertView){
		mBodyViewHolder = new BodyViewHolder();
		mBodyViewHolder.mStepImage = (ImageView) convertView.findViewById(E_id.imageview);
		mBodyViewHolder.mGetCodeBtn= (TextView) convertView.findViewById(E_id.text_1);
		mBodyViewHolder.mGetAwardsBtn= (TextView) convertView.findViewById(E_id.text_2);
		mBodyViewHolder.mRuleIntro= (TextView) convertView.findViewById(E_id.text_3);
		mBodyViewHolder.mAccountEdit= (EditText) convertView.findViewById(E_id.edit_1);
		mBodyViewHolder.mCodeEdit= (EditText) convertView.findViewById(E_id.edit_2);
		mBodyViewHolder.mTaskLayout=(TaskContainer) convertView.findViewById(E_id.taskContainer);
		mBodyViewHolder.mTaskLayout.setItemLayout(E_layout.efun_pd_welfare_act_extension_task_list_item);
		
		mBodyViewHolder.mGetCodeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPhoneNum = mBodyViewHolder.mAccountEdit.getText().toString();
				if(EfunStringUtil.isEmpty(mPhoneNum)){
					ToastUtils.toast(mContext, E_string.efun_pd_toast_empty_phone);
					return;
				}
				requestWithDialog(sendVCodeRequest(),getString(E_string.efun_pd_bind_phone_intro));
			}
		});
		
		mBodyViewHolder.mGetAwardsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCodeNum = mBodyViewHolder.mCodeEdit.getText().toString();
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
	/**
	 * 初始化页面底部
	 */
	private void initFooter(){
		mFooterView = findViewById(E_id.layout_2);
		mFooterViewHolder = new FooterViewHolder();
		mFooterViewHolder.mIcon = (ImageView) mFooterView.findViewById(E_id.item_icon);
		mFooterViewHolder.mText = (TextView) mFooterView.findViewById(E_id.item_text);
		mFooterViewHolder.mIcon.setVisibility(View.GONE);
		mFooterViewHolder.mText.setText(E_string.efun_pd_get_awards);
		mFooterView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackingUtils.track(TrackingUtils.ACTION_WELFARE, TrackingUtils.NAME_WELFARE_EXTENSION_GET_REWARD);
				mAwardsPopWindow.showPopWindow(PopWindow.POP_WINDOW_CHOSE_AWARDS);
			}
		});
	}
	
	private BaseRequestBean[] createRequest(ActExtensionGiftBean gift){
		ActivityExtensionGiftRequest request = new ActivityExtensionGiftRequest(
				HttpParam.PHONE, HttpParam.PLATFORM_AREA, gift.getGameCode(), gift.getId(),"");
		request.setReqType(IPlatformRequest.REQ_ACT_EXTENSION_GIFT);
		return new BaseRequestBean[]{request};
	}
	/**
	 * 头部
	 */
	private static class HeaderViewHolder {
		public TextView mTitle, mContent;
		public RoundCornerTextView mCategory;
		public ImageView mIcon, mButton, mText,mGiftRobbled;
	}
	private static class BodyViewHolder{
		public ImageView mStepImage;
		public TextView mGetCodeBtn,mGetAwardsBtn,mRuleIntro;
		public EditText mAccountEdit,mCodeEdit;
		public TaskContainer mTaskLayout;
	}
	/**
	 * 脚部
	 */
	private static class FooterViewHolder {
		public TextView  mText;
		public ImageView mIcon;
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
		mTitleView = titleView;
		titleView.setTitleCenterRes(E_string.efun_pd_title_receive_awards, false);
		titleView.setTitleRightRes(E_drawable.efun_pd_menu_selector);
	}
	@Override
	public void onClickRight() {
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
			TrackingUtils.track(TrackingUtils.ACTION_EXTENSION, TrackingUtils.NAME_GIFT_SELF_CENTER);
			Intent it = new Intent(ActExtensionActivity.this,GiftSelfActivity.class);
			startActivity(it);
		}
		
//		UserUtils.needLogin(this, new OnLoginFinishListener() {
//			@Override
//			public void loginCompleted(boolean completed) {
//				TrackingUtils.track(TrackingUtils.ACTION_EXTENSION, TrackingUtils.NAME_GIFT_SELF_CENTER);
//				Intent it = new Intent(ActExtensionActivity.this,GiftSelfActivity.class);
//				startActivity(it);
//			}
//		});
	}

}
