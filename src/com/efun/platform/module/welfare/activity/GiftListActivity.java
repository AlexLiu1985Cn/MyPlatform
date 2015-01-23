package com.efun.platform.module.welfare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.GiftListRequest;
import com.efun.platform.http.request.bean.GiftSelfStatusRequest;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.GiftListResponse;
import com.efun.platform.http.response.bean.GiftSelfStatusResponse;
import com.efun.platform.module.base.ElasticityActivity;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.utils.IntentUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.welfare.adapter.GiftAdapter;
import com.efun.platform.module.welfare.bean.GiftItemBean;
import com.efun.platform.module.welfare.bean.GiftSelfStatusBean;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.widget.TitleView;
import com.efun.platform.widget.list.XListView.IXListViewListener;
/**
 * 礼包 中心
 * @author Jesse
 */
public class GiftListActivity extends ElasticityActivity{
	
	private TitleView mTitleView;
	@Override
	public void onClickRight() {
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
			TrackingUtils.track(TrackingUtils.ACTION_GIFT, TrackingUtils.NAME_GIFT_SELF_CENTER);
			Intent it = new Intent(GiftListActivity.this,GiftSelfActivity.class);
			startActivity(it);
		}
//		UserUtils.needLogin(this, new OnLoginFinishListener() {
//			@Override
//			public void loginCompleted(boolean completed) {
//				TrackingUtils.track(TrackingUtils.ACTION_GIFT, TrackingUtils.NAME_GIFT_SELF_CENTER);
//				Intent it = new Intent(GiftListActivity.this,GiftSelfActivity.class);
//				startActivity(it);
//			}
//		});
	}
	private GiftAdapter mAdapter;
	private int currentPage = 1;
	private String pageSize = "10";
	@Override
	public View[] addHeaderViews() {
		return null;
	}

	@Override
	public void init(Bundle bundle) {
		mAdapter = new GiftAdapter(mContext);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(new GiftsXListViewListener());
		mListView.setOnItemClickListener(new ItemClickListener());
	}

	@Override
	public boolean needRequestData() {
		return true;
	}

	@Override
	public BaseAdapter adapter() {
		return mAdapter;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		GiftListRequest giftsRequest = new GiftListRequest(
				HttpParam.PLATFORM_AREA, currentPage + "", pageSize);
		giftsRequest.setReqType(IPlatformRequest.REQ_GIFT_LIST);
		return new BaseRequestBean[] { giftsRequest };
	}
	
	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType,baseResponse);
		if (requestType==IPlatformRequest.REQ_GIFT_LIST){
			GiftListResponse response = (GiftListResponse)baseResponse;
			GiftListRequest request = (GiftListRequest) response.getRequestBean();
			if(request.getCurrentPage().equals("1")){
				mAdapter.refresh(response.getGiftsBean());
				currentPage=1;
			}else{
				mAdapter.append(response.getGiftsBean());
				currentPage++;
			}
			if(response.getPageInfoBean().getPageIndex()==response.getPageInfoBean().getTotalPage()){
				mListView.setPullLoadEnable(false);
			}else{
				mListView.setPullLoadEnable(true);
			}
			queryGiftSelfStatus();
		}else if(requestType==IPlatformRequest.REQ_GIFT_SELF_STATUS){
			allow = false;
			GiftSelfStatusResponse response = (GiftSelfStatusResponse)baseResponse;
			GiftSelfStatusBean mGiftSelfStatusBean = response.getGiftSelfStatusBean();
			if(mGiftSelfStatusBean.getCode().equals(HttpParam.RESULT_1000)){
				GameToPlatformParamsSaveUtil.getInstanse().setNewStatusOfGiftSelf(true);
				mTitleView.setTitleRightPointStatus(View.VISIBLE);
			}
		}
	}
	private boolean allow = true;
	private void queryGiftSelfStatus(){
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser()!=null 
				&& !GameToPlatformParamsSaveUtil.getInstanse().isNewStatusOfGiftSelf() 
				&& allow){
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
	
	class GiftsXListViewListener implements IXListViewListener {
		public GiftsXListViewListener() {
		}

		@Override
		public void onRefresh() {
			requestWithoutDialog(new BaseRequestBean[]{createRequest(0)});
		}

		@Override
		public void onLoadMore() {
			requestWithoutDialog(new BaseRequestBean[]{createRequest(currentPage)});
		}

	}

	private GiftListRequest createRequest(int currentPage){
		++currentPage;
		GiftListRequest request = new GiftListRequest(
				HttpParam.PLATFORM_AREA, currentPage + "", pageSize);
		request.setReqType(IPlatformRequest.REQ_GIFT_LIST);
		return request;
	}

	class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			GiftItemBean bean = (GiftItemBean) parent.getAdapter().getItem(position);
			IntentUtils.goWithBean(mContext, GiftDetailActivity.class, bean);
		}

	}

	@Override
	public void initTitle(TitleView titleView) {
		mTitleView = titleView;
		titleView.setTitleCenterRes(E_string.efun_pd_title_gifts_downloads, false);
		titleView.setTitleRightRes(E_drawable.efun_pd_menu_selector);
	}

	
	
}
