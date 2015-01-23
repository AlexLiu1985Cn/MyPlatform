package com.efun.platform.module.person;

import android.view.View;

import com.efun.platform.module.base.BaseTabFragment;
import com.efun.platform.module.person.fragment.PerCenterEltyFragment;
import com.efun.platform.widget.TitleView;

public class PersonalCenterFragment extends BaseTabFragment {
	@Override
	public void initTitle(TitleView titleView) {
		titleView.setVisibility(View.GONE);
	}

	@Override
	public String initContent() {
		return PerCenterEltyFragment.class.getName();
	}
}
