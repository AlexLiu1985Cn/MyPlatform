package com.efun.platform.module.welfare;

import android.view.View;

import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.module.PopWindow;
import com.efun.platform.module.base.BaseTabFragment;
import com.efun.platform.module.welfare.fragment.HaoKangEltyFragment;
import com.efun.platform.widget.TitleView;

/**
 * 好康Tab頁面
 * @author Jesse
 */
public class HaoKangFragment extends BaseTabFragment{
	@Override
	public void initTitle(TitleView titleView) {
		titleView.setTitleLeftStatus(View.GONE);
		titleView.setTitleCenterRes(E_string.efun_pd_welfare, false);
		titleView.setTitleRightTextRes(E_string.efun_pd_return_game);
	}

	@Override
	public String initContent() {
		return HaoKangEltyFragment.class.getName();
	}
}
