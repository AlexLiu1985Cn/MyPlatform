package com.efun.platform.module.game;

import android.view.View;

import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.module.PopWindow;
import com.efun.platform.module.base.BaseTabFragment;
import com.efun.platform.module.game.fragment.GmsEltyFragment;
import com.efun.platform.widget.TitleView;
/**
 * 游戏Tab页面
 * @author Jesse
 *
 */
public class GamesFragment extends BaseTabFragment{
	
	@Override
	public void initTitle(TitleView titleView) {
		titleView.setTitleLeftStatus(View.GONE);
		titleView.setTitleCenterRes(E_string.efun_pd_game, false);
		titleView.setTitleRightTextRes(E_string.efun_pd_return_game);
	}

	@Override
	public String initContent() {
		return GmsEltyFragment.class.getName();
	}
}
