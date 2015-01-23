package com.efun.platform.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_array;
import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.GameCommendListRequest;
import com.efun.platform.http.request.bean.GameDetailRequest;
import com.efun.platform.http.request.bean.GameNewsRequest;
import com.efun.platform.http.request.bean.GamePraiseRequest;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.GameCommendListResponse;
import com.efun.platform.http.response.bean.GameDetailResponse;
import com.efun.platform.http.response.bean.GameNewsResponse;
import com.efun.platform.http.response.bean.GamePraiseResponse;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.VPagerAdapter;
import com.efun.platform.module.base.FixedActivity;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.base.impl.OnUpdateListener;
import com.efun.platform.module.game.adapter.GameCommendAdapter;
import com.efun.platform.module.game.adapter.GameSummaryAdapter;
import com.efun.platform.module.game.bean.GameCommendItemBean;
import com.efun.platform.module.game.bean.GameDetailBean;
import com.efun.platform.module.game.bean.GameItemBean;
import com.efun.platform.module.game.bean.GameNewsBean;
import com.efun.platform.module.game.bean.GamePraiseBean;
import com.efun.platform.module.game.view.GamePictureList;
import com.efun.platform.module.game.view.GameVPager;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.module.utils.IntentUtils;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.AppStatus;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.RequestCode;
import com.efun.platform.utils.Const.ResultCode;
import com.efun.platform.utils.Const.Web;
import com.efun.platform.widget.PagerTab;
import com.efun.platform.widget.RoundCornerTextView;
import com.efun.platform.widget.TitleView;
import com.efun.platform.widget.list.XListView;

/**
 * 游戏详情页面
 * @author Jesse
 *
 */
public class GameDetailActivity extends FixedActivity {
	private String gameTitle;

	@Override
	public void initTitle(TitleView titleView) {
		titleView.setTitleRightStatus(View.GONE);
		titleView.setTitleCenterText(gameTitle);
	}

	private final int PAGER_TAB_COUNT = 3;
	private GameItemBean mGameItemBean;
	private ViewGroup[] groups = new ViewGroup[PAGER_TAB_COUNT];
	private ImageView headerIcon, zanIcon, vedioIcon;
	private TextView gameVersionCode, gameSize;
	private PagerTab mPagerTabs;
	private GameVPager mViewPager;
	private RoundCornerTextView mCategory;
	private TextView gameIntroDetail;
	private GamePictureList mSwitchV;
	private XListView listCommend, listSummary;
	private String gameCode;
	private GameSummaryAdapter mGameSummaryAdapter;
	private GameCommendAdapter mGameCommendAdapter;
	private boolean screenOrientation;
	
	@Override
	public ViewGroup[] needShowLoading() {
		// 游戏详情
		ViewGroup groupDetail = new FrameLayout(mContext);

		View headerDetail = ViewUtils.createView(mContext, E_layout.efun_pd_game_detail_tab_particular);
		mSwitchV = (GamePictureList) headerDetail.findViewById(E_id.gamePictureList);
		gameIntroDetail = (TextView) headerDetail.findViewById(E_id.item_text);
		
		XListView listDetail = ViewUtils.createListView(mContext);
		listDetail.addHeaderView(headerDetail);
		listDetail.setAdapter(null);
		LinearLayout containerDetail = new LinearLayout(mContext);
		containerDetail.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		containerDetail.addView(listDetail, params);

		View v = ViewUtils.createBottom(mContext);
		ImageView iconDetail = (ImageView) v.findViewById(E_id.item_icon);
		TextView textDetail = (TextView) v.findViewById(E_id.item_text);
		
		if(mGameItemBean!=null){
			switch (mGameItemBean.getStatus()) {
			case AppStatus.DOWNLOAD:
				iconDetail.setBackgroundResource(E_drawable.efun_pd_download_selector);
				textDetail.setText(E_string.efun_pd_download_en);
				break;
			case AppStatus.START:
				iconDetail.setBackgroundResource(E_drawable.efun_pd_start_selector);
				textDetail.setText(E_string.efun_pd_start_en);
				break;
			case AppStatus.UPDATE:
				iconDetail.setBackgroundResource(E_drawable.efun_pd_update_selector);
				textDetail.setText(E_string.efun_pd_update_en);
				break;
			}
		}else{
			iconDetail.setBackgroundResource(E_drawable.efun_pd_download_selector);
			textDetail.setText(E_string.efun_pd_download_en);
		}
		containerDetail.addView(v);
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mGameItemBean!=null){
					switch (mGameItemBean.getStatus()) {
					case AppStatus.START:
						AppUtils.startApp(mContext, mGameItemBean.getPackageName());
						break;
					case AppStatus.UPDATE:
					case AppStatus.DOWNLOAD:
						AppUtils.download(mContext, mGameItemBean.getAndroidDownload());
						break;
					}
				}
				//游戏详情页面的下载事件
			}
		});

		groupDetail.addView(containerDetail, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		groups[0] = groupDetail;

		// 游戏资讯
		ViewGroup groupSummary = new FrameLayout(mContext);
		mGameSummaryAdapter = new GameSummaryAdapter(mContext);
		listSummary = ViewUtils.createListView(mContext);
		listSummary.setAdapter(mGameSummaryAdapter);
		listSummary.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(parent.getAdapter().getItem(position)!=null){
					GameNewsBean bean = (GameNewsBean) parent.getAdapter().getItem(position);
					IntentUtils.go2Web(mContext, Web.WEB_GO_GAME, bean);
				}
				
			}
		});
		groupSummary.addView(listSummary, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		groups[1] = groupSummary;

		// 玩家评论
		ViewGroup groupCommend = new FrameLayout(mContext);
		mGameCommendAdapter = new GameCommendAdapter(mContext);
		listCommend = ViewUtils.createListView(mContext);
		View margin = ViewUtils.createMargin(mContext);
		margin.setBackgroundColor(getResources().getColor(E_color.e_efeff4));
		listCommend.addHeaderView(margin);
		listCommend.setAdapter(mGameCommendAdapter);
		listCommend.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//玩家评论各个子项点击处理事件

			}
		});
		containerDetail = new LinearLayout(mContext);
		containerDetail.setOrientation(LinearLayout.VERTICAL);
		params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		containerDetail.addView(listCommend, params);

		commendBottom = ViewUtils.createBottom(mContext);
		iconDetail = (ImageView) commendBottom.findViewById(E_id.item_icon);
		textDetail = (TextView) commendBottom.findViewById(E_id.item_text);
		iconDetail.setBackgroundResource(E_drawable.efun_pd_commend_selector);
		textDetail.setText(E_string.efun_pd_i_wanna_commend);
		containerDetail.addView(commendBottom);
		//评论按钮
		commendBottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtils.goWithBeanForResult(mContext, GameCommendActivity.class, mGameItemBean,RequestCode.REQ_GAME_COMMEND);
				commendBottom.setVisibility(View.INVISIBLE);
			}
		});

		groupCommend.addView(containerDetail, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		groups[2] = groupCommend;
		return groups;
	}

	private View commendBottom;

//	private View createBottom() {
//		return mLayoutInflater.inflate(E_layout.efun_pd_game_detail_tab_bottom,
//				null);
//	}

	@Override
	public void onResume() {
		super.onResume();
		if (commendBottom != null) {
			if (commendBottom.getVisibility() == View.INVISIBLE) {
				commendBottom.setVisibility(View.VISIBLE);
			}
		}
	}



	@Override
	public void onSuccess(int requestType, BaseResponseBean baseResponse) {
		super.onSuccess(requestType,baseResponse);
		if (requestType==IPlatformRequest.REQ_GAME_DETAIL) {
			GameDetailResponse response = (GameDetailResponse)baseResponse;
			GameDetailBean bean = response.getGameDetailBean();
			mSwitchV.setScreenOrientation(screenOrientation);
			mSwitchV.loadedData(bean.getBiggroup());
			mSwitchV.setOnEfunItemClickListener(new OnEfunItemClickListener() {
				@Override
				public void onItemClick(int position) {
				}
			});
			
			gameIntroDetail.setText(bean.getDescription());//设置游戏介绍内容
		} else if (requestType==IPlatformRequest.REQ_GAME_NEWS_LIST) {
			//设置游戏资讯页面相关内容
			GameNewsResponse response = (GameNewsResponse)baseResponse;
			int newSize = 0;
			for(int i = 0 ; i < response.getGameNewsList().size() ; i++){
				if(response.getGameNewsList().get(i).getType() == 1){
					newSize++;
				}
			}
			mGameSummaryAdapter.setNewsSize(newSize);
			mGameSummaryAdapter.append(response.getGameNewsList());
		} else if (requestType==IPlatformRequest.REQ_GAME_COMMEND_LIST) {
			//设置玩家评论页面相关内容
			GameCommendListResponse response = (GameCommendListResponse)baseResponse;
			mGameCommendAdapter.append(response.getGameCommendList());
		} else if (requestType==IPlatformRequest.REQ_GAME_PRAISE) {
			//设置玩家评论页面相关内容
			GamePraiseResponse response = (GamePraiseResponse)baseResponse;
			GamePraiseBean bean = response.getGamePraiseBean();
			if(bean.getCode().equals(HttpParam.RESULT_1000)){
				zanIcon.setEnabled(false);
				zanIcon.setBackgroundResource(E_drawable.efun_pd_zan_click);
				((OnUpdateListener)GameToPlatformParamsSaveUtil.getInstanse().getOnEfunListener()).onUpdate();
			}else if(bean.getCode().equals(HttpParam.RESULT_1100)){
				zanIcon.setEnabled(false);
				zanIcon.setBackgroundResource(E_drawable.efun_pd_zan_click);
			}
			ToastUtils.toast(mContext, bean.getMessage());
		}
	}

	@Override
	public void onClickNotifyText(int requestType) {
		super.onClickNotifyText(requestType);
		if(requestType==IPlatformRequest.REQ_GAME_COMMEND_LIST){
			commendBottom.performClick();
		}
	}
	

	/**
	 * 设置需要请求数据
	 */
	@Override
	public boolean needRequestData() {
		return true;
	}

	/**
	 * 请求数据
	 */
	@Override
	public BaseRequestBean[] needRequestDataBean() {
		
		GameDetailRequest detailRequest = new GameDetailRequest(gameCode, "", "", HttpParam.PLATFORM_AREA,HttpParam.PLATFORM);
		detailRequest.setReqType(IPlatformRequest.REQ_GAME_DETAIL);
		
		GameNewsRequest newsRequest = new GameNewsRequest(gameCode,HttpParam.PLATFORM_AREA);
		newsRequest.setReqType(IPlatformRequest.REQ_GAME_NEWS_LIST);
		
		GameCommendListRequest commendListRequest = new GameCommendListRequest(gameCode);
		commendListRequest.setReqType(IPlatformRequest.REQ_GAME_COMMEND_LIST);
		return new BaseRequestBean[] {detailRequest, newsRequest,commendListRequest};
	}

	@Override
	public void init(Bundle bundle) {
		
		headerIcon = (ImageView) findViewById(E_id.item_icon);
		vedioIcon = (ImageView) findViewById(E_id.item_button);//视频
		zanIcon = (ImageView) findViewById(E_id.item_text);//点赞
		zanIcon.setBackgroundResource(E_drawable.efun_pd_zan_icon);
		gameVersionCode = (TextView) findViewById(E_id.item_count);
		gameSize = (TextView) findViewById(E_id.item_content);
		mCategory = (RoundCornerTextView) findViewById(E_id.round_text);
		mPagerTabs = (PagerTab) findViewById(E_id.pager_view_tab);

		vedioIcon.setBackgroundResource(E_drawable.efun_pd_game_video_selector);
		
		if(bundle!=null){
			mGameItemBean = (GameItemBean) bundle.getSerializable(Const.BEAN_KEY);
			gameTitle = mGameItemBean.getGameName();
			gameVersionCode.setText(getString(E_string.efun_pd_game_version)+mGameItemBean.getVersion());
			gameSize.setText(getString(E_string.efun_pd_game_size)+mGameItemBean.getPackageSize());
			mCategory.setColor(getResources().getColor(E_color.e_00aaeb));
			mCategory.setText(mGameItemBean.getGameType());
			ImageManager.displayImage(mGameItemBean.getSmallpic(), headerIcon,ImageManager.IMAGE_SQUARE);
			gameCode = mGameItemBean.getGameCode();
			if(mGameItemBean.getPic_display().equals("0")){
				screenOrientation = true;
			}
			vedioIcon.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					IntentUtils.go2WebForVideo(mContext, mGameItemBean.getVideoUrl());
				}
			});
		}
		
		mViewPager = (GameVPager) findViewById(E_id.pager_view_v4);

		VPagerAdapter mAdapter = new VPagerAdapter(groups);
		mViewPager.setAdapter(mAdapter);

		mPagerTabs.setTab(E_layout.efun_pd_pager_tab_textview);
		mPagerTabs.setTabSelectedColor(E_color.e_5aa9ff);
		mPagerTabs.setTitles(E_array.efun_pd_game_detail_v_tab);
		mPagerTabs.setLine(E_drawable.efun_pd_blue_line_each);
		mPagerTabs.setPagerAdapter(mViewPager);
		mPagerTabs.setSelectedItem(mViewPager, 0);
		
		if(UserUtils.isLogin()){
			zanIcon.setVisibility(View.VISIBLE);
		}else{
			zanIcon.setVisibility(View.GONE);
		}
		zanIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
					requestWithDialog(createLike(), getString(E_string.efun_pd_loading_msg_commend));
				}
//				UserUtils.needLogin(mContext, new OnLoginFinishListener() {
//					@Override
//					public void loginCompleted(boolean completed) {
//						requestWithDialog(createLike(), getString(E_string.efun_pd_loading_msg_commend));
//					}
//				});
			}
		});
		
	}
	
	private BaseRequestBean[] createLike(){
		GamePraiseRequest praiseRequest = new GamePraiseRequest(gameCode);
		praiseRequest.setReqType(IPlatformRequest.REQ_GAME_PRAISE);
		return new BaseRequestBean[]{praiseRequest};
	}

	@Override
	public int LayoutId() {
		return E_layout.efun_pd_game_detail;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==RequestCode.REQ_GAME_COMMEND && resultCode==ResultCode.RST_GAME_COMMEND){
			GameCommendItemBean bean = (GameCommendItemBean) data.getSerializableExtra(Const.BEAN_KEY);
			mGameCommendAdapter.insert(bean);
			hasData(IPlatformRequest.REQ_GAME_COMMEND_LIST);
		}
	}


}
