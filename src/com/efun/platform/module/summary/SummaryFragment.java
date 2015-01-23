package com.efun.platform.module.summary;

import android.view.View;

import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.module.PopWindow;
import com.efun.platform.module.base.BaseTabFragment;
import com.efun.platform.module.summary.fragment.SmryEltyFragment;
import com.efun.platform.widget.TitleView;

/**
 * 资讯Tab页面
 * @author Jesse
 * 
 */
public class SummaryFragment extends BaseTabFragment {
	@Override
	public void initTitle(TitleView titleView) {
		titleView.setTitleLeftStatus(View.GONE);
		titleView.setTitleLeftStatus(View.GONE);
		titleView.setTitleRightTextRes(E_string.efun_pd_return_game);
	}
	
	@Override
	public String initContent() {
		return SmryEltyFragment.class.getName();
	}
}
