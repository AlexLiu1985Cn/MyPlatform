package com.efun.platform.module.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.efun.core.tools.EfunLogUtil;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.module.base.impl.OnTitleButtonClickListener;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.widget.TitleView;

/**
 * Tab 内容碎片 继承了 {@link BaseFragment} 并且实现 {@link OnTitleButtonClickListener} 接口
 * 
 * @author Jesse
 * 
 */
public abstract class BaseTabFragment extends BaseFragment implements OnTitleButtonClickListener{
	/**
	 * 内容 {@link ElasticityFragment}
	 */
	private Fragment mContentFragment;
	private TitleView mTitleView;
	@Override
	public int LayoutId() {
		return E_layout.efun_pd_elasticity_tab;
	}

	public BaseTabFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initViews() {
		EfunLogUtil.logI("initView");
		mTitleView = (TitleView) getActivity().findViewById(E_id.title);
		if(mTitleView!=null){
			mTitleView.setOnTitleButtonClickListener(this);
			initTitle(mTitleView);
		}
		
		mContentFragment = Fragment.instantiate(getActivity(), initContent(), null);
		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		ft.replace(E_id.realtabcontent, mContentFragment);
		ft.commitAllowingStateLoss();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onClickLeft() {
		
	}
	@Override
	public void onClickRight() {
		AppUtils.ExitApp();
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
	public void onNoData(int requestType, String noDataNotify) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 初始化标题，如果返回null则无标题
	 * @return
	 */
	public abstract void initTitle(TitleView titleView);
	/**
	 * 内容 Frament 的名称
	 * @return
	 */
	public abstract String initContent();
	
}
