package com.efun.platform.module.welfare.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_array;
import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.AndroidScape.E_style;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.GiftKnockRequest;
import com.efun.platform.http.request.bean.GiftSelfStatusRequest;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.GiftKnockResponse;
import com.efun.platform.http.response.bean.GiftSelfStatusResponse;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.VPagerAdapter;
import com.efun.platform.module.base.FixedActivity;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.module.welfare.bean.GiftItemBean;
import com.efun.platform.module.welfare.bean.GiftKnockBean;
import com.efun.platform.module.welfare.bean.GiftSelfStatusBean;
import com.efun.platform.module.welfare.view.RoundProgress;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.widget.PagerTab;
import com.efun.platform.widget.RatingBarView;
import com.efun.platform.widget.TitleView;
/**
 * 礼包详情
 * @author Jesse
 *
 */
public class GiftDetailActivity extends FixedActivity{
	private String titleStr;
	private TitleView mTitleView;

	@Override
	public void onClickRight() {
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
			TrackingUtils.track(TrackingUtils.ACTION_GIFT_DETAIL, TrackingUtils.NAME_GIFT_SELF_CENTER);
			Intent it = new Intent(GiftDetailActivity.this,GiftSelfActivity.class);
			startActivity(it);
		}
//		UserUtils.needLogin(this, new OnLoginFinishListener() {
//			@Override
//			public void loginCompleted(boolean completed) {
//				TrackingUtils.track(TrackingUtils.ACTION_GIFT_DETAIL, TrackingUtils.NAME_GIFT_SELF_CENTER);
//				Intent it = new Intent(GiftDetailActivity.this,GiftSelfActivity.class);
//				startActivity(it);
//			}
//		});
	}
	
	private TextView giftContent, ruleIntro,gameName;
	private ImageView gameHeader;
	private PagerTab mPagerTabs;
	private ViewPager mViewPager;
	private View mBottom;
	private RoundProgress mRoundProgress;
	private GiftItemBean mGiftItemBean;
	@Override
	public ViewGroup[] needShowLoading() {
		return null;
	}
	
	@Override
	public boolean needRequestData() {
		return false;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		GiftKnockRequest knockRequest = new GiftKnockRequest(
				mGiftItemBean.getGameCode(),
				mGiftItemBean.getGoodsType(),
				HttpParam.PLATFORM_AREA,HttpParam.PHONE);
		knockRequest.setReqType(IPlatformRequest.REQ_GIFT_KNOCK);
		return new BaseRequestBean[]{knockRequest};
	}

	private ViewGroup[] createGroup(){
		ViewGroup[] groups = new ViewGroup[2];
		FrameLayout view  = null;
		for (int i = 0; i < groups.length; i++) {
			view = (FrameLayout) ViewUtils.createView(mContext, E_layout.efun_pd_welfare_gift_textview);
			if(i==0){
				giftContent = (TextView) view.findViewById(E_id.item_text);
			}else{
				ruleIntro = (TextView) view.findViewById(E_id.item_text);
			}
			groups[i] = view;
		}
		return groups;
	}
	
	@Override
	public void init(Bundle bundle) {
		if(bundle!=null){
			GiftItemBean bean = (GiftItemBean) bundle.getSerializable(Const.BEAN_KEY);
			titleStr = bean.getGoodsName();
			if(titleStr.contains("-")){
				int lastIndex = titleStr.lastIndexOf("-");
				titleStr = titleStr.substring(lastIndex+1, titleStr.length());
				if(EfunStringUtil.isEmpty(titleStr)){
					titleStr = bean.getGoodsType();
				}
			}
		}
		
		mPagerTabs = (PagerTab) findViewById(E_id.pager_view_tab);
		mViewPager = (ViewPager) findViewById(E_id.pager_view_v4);
		VPagerAdapter mAdapter = new VPagerAdapter(createGroup());
		mViewPager.setAdapter(mAdapter);

		mPagerTabs.setTab(E_layout.efun_pd_pager_tab_textview);
		mPagerTabs.setTabSelectedColor(E_color.e_5aa9ff);
		mPagerTabs.setTitles(E_array.efun_pd_welfare_gift_v_tab);
		mPagerTabs.setLine(E_drawable.efun_pd_blue_line_each);
		mPagerTabs.setPagerAdapter(mViewPager);
		mPagerTabs.setSelectedItem(mViewPager, 0);
		
		mBottom = findViewById(E_id.layout_1);
		mBottom.findViewById(E_id.item_icon).setVisibility(View.GONE);
		((TextView)mBottom.findViewById(E_id.item_text)).setText(E_string.efun_pd_rob);
		gameHeader = (ImageView) findViewById(E_id.item_icon);
		gameName = (TextView) findViewById(E_id.text_1);
		
		if(bundle!=null){
			mGiftItemBean = (GiftItemBean) bundle.getSerializable(Const.BEAN_KEY);
			ImageManager.displayImage(mGiftItemBean.getIcon(), gameHeader, ImageManager.IMAGE_SQUARE);
			gameName.setText(mGiftItemBean.getGameName());
			
			giftContent.setText(mGiftItemBean.getAwardDesc());
			ruleIntro.setText(mGiftItemBean.getAwardRule());
			
			mRoundProgress = (RoundProgress) findViewById(E_id.roundProgress);
			mRoundProgress.setGradientColors(getRoundProgressColors());
			mRoundProgress.setMaxProgress(mGiftItemBean.getTotal());
			mRoundProgress.setProgress(mGiftItemBean.getTotal()-mGiftItemBean.getHasUse());
			mRoundProgress.onInvalidate();
			
			if(mGiftItemBean.getUserHasGot()==1){
				mBottom.setEnabled(false);
				((TextView)mBottom.findViewById(E_id.item_text)).setText(E_string.efun_pd_robed);
				mBottom.setBackgroundResource(E_color.e_8e8e8e);
			}else{
				mBottom.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
//						UserUtils.needLogin(mContext,
//								new OnLoginFinishListener() {
//									@Override
//									public void loginCompleted(boolean completed) {
//										requestWithDialog(needRequestDataBean(), getString(E_string.efun_pd_loading_msg_commend));
//									}
//								});
						if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
							requestWithDialog(needRequestDataBean(), getString(E_string.efun_pd_loading_msg_commend));
						}
					}
				});
			}
		}
		queryGiftSelfStatus();
	}
	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType,baseResponse);
		if (requestType==IPlatformRequest.REQ_GIFT_KNOCK){
			GiftKnockResponse response = (GiftKnockResponse)baseResponse;
			GiftKnockBean bean = response.getGiftKnockBean();
			String serial = bean.getSerial();
			if(serial.equals(HttpParam.RESULT_1028)){
				ToastUtils.toast(mContext, getString(E_string.efun_pd_code_1028));
			}else if(serial.equals(HttpParam.RESULT_1029)){
				ToastUtils.toast(mContext, getString(E_string.efun_pd_code_1029));
			}else if(serial.equals(HttpParam.RESULT_1030)){
				ToastUtils.toast(mContext, getString(E_string.efun_pd_code_1030));
			}else if(serial.equals(HttpParam.RESULT_1031)){
				ToastUtils.toast(mContext, getString(E_string.efun_pd_code_1031));
				mBottom.setEnabled(false);
				mBottom.setBackgroundResource(E_color.e_8e8e8e);
				((TextView)mBottom.findViewById(E_id.item_text)).setText(E_string.efun_pd_robed);
			}else{
				mRoundProgress.setProgress(mGiftItemBean.getTotal()-mGiftItemBean.getHasUse()-1);
				mRoundProgress.onInvalidate();
				mBottom.setEnabled(false);
				mBottom.setBackgroundResource(E_color.e_8e8e8e);
				((TextView)mBottom.findViewById(E_id.item_text)).setText(E_string.efun_pd_robed);
//				ToastUtils.toast(mContext, getString(E_string.efun_pd_knock_code)+serial);
				showGradeDialog();
				queryGiftSelfStatus();
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
	private void queryGiftSelfStatus(){
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser()!=null && !GameToPlatformParamsSaveUtil.getInstanse().isNewStatusOfGiftSelf()){
			GiftSelfStatusRequest request = new GiftSelfStatusRequest(
					GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid(), 
					HttpParam.GIFT_STATUS_QUERY);
			request.setReqType(IPlatformRequest.REQ_GIFT_SELF_STATUS);
			requestWithoutDialog(new BaseRequestBean[]{request});
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
	@Override
	public int LayoutId() {
		return E_layout.efun_pd_welfare_gift;
	}
	
	private int[] getRoundProgressColors(){
		return new int[]{
				E_color.e_fff765,
				E_color.e_16c6ff,
				E_color.e_3bc62a
		};
	}

	@Override
	public void initTitle(TitleView titleView) {
		mTitleView = titleView;
		titleView.setTitleRightRes(E_drawable.efun_pd_menu_selector);
		titleView.setTitleCenterText(titleStr);
		titleView.setTitleBarBackgroundRes(E_drawable.efun_pd_welfare_gift_header);
		titleView.setTitleBottomLineStatus(View.GONE);
	}
	
	private void showGradeDialog() {
		final Dialog dialog = new Dialog(mContext, E_style.CS_Dialog);
		// 设置它的ContentView
		dialog.setContentView(E_layout.efun_pd_common_toast_dialog);
		TextView content = (TextView) dialog.findViewById(E_id.dialog_content);
		TextView sureBtn = (TextView) dialog.findViewById(E_id.dialog_btn_determine);
		TextView checkBtn = (TextView) dialog.findViewById(E_id.dialog_btn_check);
		content.setText(E_string.efun_pd_hint_gift_knocked);
		sureBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		checkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(GiftDetailActivity.this,GiftSelfActivity.class);
				startActivity(it);
				dialog.dismiss();
			}
		});
		dialog.show();
	}


	
}