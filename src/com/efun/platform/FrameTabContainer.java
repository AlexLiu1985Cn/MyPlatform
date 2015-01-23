package com.efun.platform;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.efun.core.tools.EfunLogUtil;
import com.efun.core.tools.EfunResourceUtil;
//import com.efun.facebook.share.self.FacebookSelfPluginImpl;
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
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.base.impl.OnRequestListener;
import com.efun.platform.module.cs.CustomServiceFragment;
import com.efun.platform.module.cs.fragment.CsEltyFragment;
import com.efun.platform.module.game.GamesFragment;
import com.efun.platform.module.person.PersonalCenterFragment;
import com.efun.platform.module.person.fragment.PerCenterEltyFragment;
import com.efun.platform.module.summary.SummaryFragment;
import com.efun.platform.module.utils.PushUtils;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.welfare.HaoKangFragment;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Store;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.RequestCode;
import com.efun.platform.utils.Const.ResultCode;
import com.efun.platform.utils.Const.Tab;

/**
 * 框架容器
 * 
 * @author Jesse
 * 
 */
public class FrameTabContainer extends FragmentActivity implements FrameTabListener {
	
	/**
	 * {@link FragmentTabHost}
	 */
	private FragmentTabHost mFragmentTabHost;
	/**
	 * 资讯 Tab的Tag值
	 */
	private final String TAB_ITEM_TAG_SUMMARY = "Summary";
	/**
	 * 游戏 Tab的Tag值
	 */
	private final String TAB_ITEM_TAG_GAMES = "Games";
	/**
	 * 好康 Tab的Tag值
	 */
	private final String TAB_ITEM_TAG_HAOKANG = "HaoKang";
	/**
	 * 客服 Tab的Tag值
	 */
	private final String TAB_ITEM_TAG_CUSTOMSERVICE = "CustomService";
	/**
	 * 玩家'我' Tab的Tag值
	 */
	private final String TAB_ITEM_TAG_PLAYERSELF = "PlayerSelf";
	/**
	 * {@link TabSpec}
	 */
	private final String[] mTabSpecs = new String[] { TAB_ITEM_TAG_SUMMARY, TAB_ITEM_TAG_GAMES, TAB_ITEM_TAG_HAOKANG, TAB_ITEM_TAG_CUSTOMSERVICE, TAB_ITEM_TAG_PLAYERSELF };
	public static int lastTag = Tab.TAB_ITEM_TAG_SUMMARY;

	@Override
	protected void onCreate(Bundle arg0) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(arg0);
		setContentView(E_layout.efun_pd_main_tab_layout);
		initViews();
		initDatas();
		initListeners();
		
		GameToPlatformParamsSaveUtil.getInstanse().addActivity(this);
		
		//google分析
		TrackingUtils.init(this);
		TrackingUtils.track(TrackingUtils.ACTION_APP, TrackingUtils.NAME_APP_START);
				
		TrackingUtils.trackSingle(this,TrackingUtils.ACTION_APP, TrackingUtils.NAME_APP_START_UNIQUE,Const.BETWEENTIME, FrameTabContainer.class);
		
		Log.e("efun", "position:"+getResources().getDimension(EfunResourceUtil.getResourcesIdByName(this, "dimen", "e_index")));
		
	}
   

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EfunLogUtil.logI("efun", "FrameTabContainer:onDestroy()");
		//google分析
		TrackingUtils.destory();
	};

	/**
	 * 初始化控件
	 */
	private void initViews() {
		mFragmentTabHost = (FragmentTabHost) findViewById(E_id.tabhost);
		mFragmentTabHost.setup(this, getSupportFragmentManager(), E_id.realtabcontent);
	}

	@SuppressLint("NewApi")
	private void initDatas() {
		Class<?>[] tabClasses = getFragmentClass();
		int length = tabClasses.length;
		View parentView = null;
		ImageView itemImgView = null;
		for (int i = 0; i < length; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mFragmentTabHost.newTabSpec(mTabSpecs[i]).setIndicator(getTabItemView());
			// 将Tab按钮添加进Tab选项卡中
			mFragmentTabHost.addTab(tabSpec, tabClasses[i], null);
			// 设置Tab按钮的背景
			parentView = mFragmentTabHost.getTabWidget().getChildAt(i);
			itemImgView = (ImageView) parentView.findViewById(E_id.imageview);
			itemImgView.setImageResource(getTabIcon()[i]);
		}
		//跳转到指定页面
		onTabChange(GameToPlatformParamsSaveUtil.getInstanse().getTabType());
	}

	/**
	 * 初始化Tab 切换监听
	 */
	private void initListeners() {
		mFragmentTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals(TAB_ITEM_TAG_SUMMARY)) {
					TrackingUtils.track(TrackingUtils.ACTION_TAB, TrackingUtils.NAME_TAB_SUMMARY);
					lastTag = Tab.TAB_ITEM_TAG_SUMMARY;
				} else if (tabId.equals(TAB_ITEM_TAG_GAMES)) {
					TrackingUtils.track(TrackingUtils.ACTION_TAB, TrackingUtils.NAME_TAB_GAME);
					lastTag = Tab.TAB_ITEM_TAG_GAMES;
				} else if (tabId.equals(TAB_ITEM_TAG_HAOKANG)) {
					TrackingUtils.track(TrackingUtils.ACTION_TAB, TrackingUtils.NAME_TAB_WELFARE);
					lastTag = Tab.TAB_ITEM_TAG_HAOKANG;
				} else if (tabId.equals(TAB_ITEM_TAG_CUSTOMSERVICE)) {
					TrackingUtils.track(TrackingUtils.ACTION_TAB, TrackingUtils.NAME_TAB_CS);
					lastTag = Tab.TAB_ITEM_TAG_CUSTOMSERVICE;
					if(UserUtils.isLogin()){
						if(cs!=null){
							cs.requestReplyStatus();
						}
					}
				} else if (tabId.equals(TAB_ITEM_TAG_PLAYERSELF)) {
					TrackingUtils.track(TrackingUtils.ACTION_TAB, TrackingUtils.NAME_TAB_SELF);
					lastTag = Tab.TAB_ITEM_TAG_PLAYERSELF;
//					UserUtils.needLoginInTag(FrameTabContainer.this, lastTag, Tab.TAB_ITEM_TAG_PLAYERSELF, new OnLoginFinishListener() {
//						@Override
//						public void loginCompleted(boolean completed) {
//							lastTag = Tab.TAB_ITEM_TAG_PLAYERSELF;
//							if(!completed){
//								if(persion!=null){
//									persion.updatePersionInfo();
//								}
//							}
//						}
//					});
				}
			}
		});
	}
	private PerCenterEltyFragment persion;
	private CsEltyFragment cs;
	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		if(fragment instanceof PerCenterEltyFragment){
			persion = (PerCenterEltyFragment) fragment;
		}else if(fragment instanceof CsEltyFragment){
			cs = (CsEltyFragment) fragment;
		}
	}
	/**
	 * 创建Tab Item View
	 */
	private View getTabItemView() {
		int id = E_layout.efun_pd_item_tab_layout;
		return LayoutInflater.from(this).inflate(id, null);
	}

	/**
	 * 获取Tab Icon 资源Ids
	 * 
	 * @return
	 */
	private int[] getTabIcon() {
		return new int[] { E_drawable.efun_pd_tab_item_summary_selector, E_drawable.efun_pd_tab_item_games_selector, E_drawable.efun_pd_tab_item_welfare_selector, E_drawable.efun_pd_tab_item_cs_selector, E_drawable.efun_pd_tab_item_playerself_selector };
	}

	/**
	 * 创建每个Tab 项 的Fragment页面
	 * 
	 * @return
	 */
	private Class<?>[] getFragmentClass() {
		return new Class<?>[] { SummaryFragment.class, GamesFragment.class, HaoKangFragment.class, CustomServiceFragment.class, PersonalCenterFragment.class };
	}

	@Override
	public void onTabChange(int index) {
		mFragmentTabHost.setCurrentTab(index);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Tab.TAB_ITEM_TAG_PLAYERSELF && requestCode == lastTag) {
			onTabChange(lastTag);
		}else if(resultCode==ResultCode.RST_LOGOUT && requestCode ==RequestCode.REQ_LOGOUT){
			onTabChange(Tab.TAB_ITEM_TAG_SUMMARY);
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
//	private long firstClick = 0;
//	/**
//	 * 連續點擊兩次，退出程序
//	 */
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(lastTag != Tab.TAB_ITEM_TAG_SUMMARY){
//			mFragmentTabHost.setCurrentTab(Tab.TAB_ITEM_TAG_SUMMARY);
//			return true;
//		}
//		if(keyCode == KeyEvent.KEYCODE_BACK){
//			long secondClick = System.currentTimeMillis();
//			if(secondClick - firstClick > 2000){
//				ToastUtils.toast(this, E_string.efun_pd_logout,Toast.LENGTH_SHORT);
//				firstClick = secondClick;
//				return true;
//			}
//		}
//		return super.onKeyDown(keyCode, event);
//	}	
	
}
