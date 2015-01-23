package com.efun.platform.module.summary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.SummaryHomeRequest;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.SummaryHomeResponse;
import com.efun.platform.module.base.ElasticityFragment;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.summary.activity.SummaryListActivity;
import com.efun.platform.module.summary.adapter.BannerAdapter;
import com.efun.platform.module.summary.bean.SummaryHomeBean;
import com.efun.platform.module.summary.view.SummCommContainer;
import com.efun.platform.module.summary.view.SummaryContainer;
import com.efun.platform.module.utils.IntentUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.Web;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.widget.ZoomPointContainer;
import com.efun.platform.widget.list.XListView.IXListViewListener;

/**
 * 资讯首页
 * 
 * @author Jesse
 * 
 */
public class SmryEltyFragment extends ElasticityFragment {
	/**
	 * 广告位{@link ViewPager} 
	 */
	private ViewPager mBannerViewPager;
	/**
	 * 适配器{@link BannerAdapter}
	 */
	private BannerAdapter mBannerAdapter;
	
	private ZoomPointContainer mZoomPointContainer;
	/**
	 * 资讯列表 {@link SummaryContainer}
	 */
	private SummCommContainer mNewsSummary,mGuidsSummary;
	/**
	 * 更多按钮
	 */
	private TextView moreBlue;

	@Override
	public View[] addHeaderViews() {
		View view = ViewUtils.createView(getActivity(), E_layout.efun_pd_summary_content);
		return new View[] { view };
	}

	@Override
	public void init(Bundle bundle) {
		mBannerViewPager = (ViewPager) mViews[0].findViewById(E_id.pager_view);
		mZoomPointContainer = (ZoomPointContainer) mViews[0].findViewById(E_id.zoom_point_parent);
		mGuidsSummary = (SummCommContainer) mViews[0].findViewById(E_id.summary_guids);
		mNewsSummary = (SummCommContainer) mViews[0].findViewById(E_id.summary_news);
		moreBlue = (TextView) mViews[0].findViewById(E_id.more_blue);

		mListView.setPullLoadEnable(true);
		mListView.setPullLoadEnable(false);
		
		mGuidsSummary.setItemLayout(E_layout.efun_pd_summary_list_item_2);
		mNewsSummary.setItemLayout(E_layout.efun_pd_summary_list_item_2);
		
		moreBlue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//游戏资讯more按钮处理事件，跳转到资讯页面
				Intent it = new Intent(getActivity(), SummaryListActivity.class);
				startActivity(it);
			}
		});

		mListView.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				requestWithoutDialog(needRequestDataBean());
			}
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType,baseResponse);
		if (requestType==IPlatformRequest.REQ_SUMMARY_HOME){
			mListView.stopRefresh();
			SummaryHomeResponse response = (SummaryHomeResponse)baseResponse;
			final SummaryHomeBean bean = response.getSummaryHomeBean();
			
			mNewsSummary.loadedData(bean.getmNewsArray());
			mNewsSummary.setOnEfunItemClickListener(new OnEfunItemClickListener() {
				@Override
				public void onItemClick(int position) {
					IntentUtils.go2Web(getActivity(), Web.WEB_GO_SUMMARY,
							bean.getmNewsArray().get(position));
				}
			});
			mGuidsSummary.loadedData(bean.getmGuidesArray());
			mGuidsSummary.setOnEfunItemClickListener(new OnEfunItemClickListener() {
				@Override
				public void onItemClick(int position) {
					IntentUtils.go2Web(getActivity(), Web.WEB_GO_SUMMARY,
							bean.getmGuidesArray().get(position));
				}
			});
			
			if(mBannerAdapter==null){
				mBannerAdapter = new BannerAdapter(getActivity());
			}
			mBannerAdapter.refresh(bean.getmNoticesArray());
			mBannerAdapter.setOnEfunItemClickListener(new OnEfunItemClickListener() {
				@Override
				public void onItemClick(int position) {
//					TrackingUtils.track(TrackingUtils.ACTION_BANNER, bean.getBannerArray().get(position).getTitle());
					IntentUtils.go2Web(getActivity(), Web.WEB_GO_SUMMARY,
							bean.getmNoticesArray().get(position));
					Log.i("efun", "position:"+position);
				}
			});
			mBannerViewPager.setAdapter(mBannerAdapter);
			mBannerViewPager.setCurrentItem(0);
			
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					mBannerViewPager.setCurrentItem(1);
				}
				}, ZoomPointContainer.DELAYED_TIME);
				
			mZoomPointContainer.setCount(bean.getmNoticesArray().size()/2);
			mZoomPointContainer.setItemLayout(E_layout.efun_pd_point);
			mZoomPointContainer.onInvalidate(0);
			mZoomPointContainer.setViewPager(mBannerViewPager);
		}
	}

	@Override
	public boolean needRequestData() {
		return true;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		SummaryHomeRequest homeRequest = new SummaryHomeRequest(getActivity(),
				HttpParam.PLATFORM, HttpParam.PLATFORM_AREA, 
				"small", "",GameToPlatformParamsSaveUtil.getInstanse().getGameCode());
		homeRequest.setReqType(IPlatformRequest.REQ_SUMMARY_HOME);
		return new BaseRequestBean[] { homeRequest };
	}

	@Override
	public BaseAdapter adapter() {
		return null;
	}
}
