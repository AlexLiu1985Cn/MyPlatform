package com.efun.platform.module.welfare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_array;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.module.base.ElasticityFragment;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.utils.IntentUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.module.welfare.activity.ActExtensionActivity;
import com.efun.platform.module.welfare.activity.ActListActivity;
import com.efun.platform.module.welfare.activity.GiftListActivity;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.Web;

/**
 * 好康
 * 
 * @author Jesse
 * 
 */
public class HaoKangEltyFragment extends ElasticityFragment {
	private OnEfunItemClickListener mOnEfunItemClickListener;

	@Override
	public View[] addHeaderViews() {
		mOnEfunItemClickListener = new OnEfunItemClickListener() {
			@Override
			public void onItemClick(int position) {

				if (position == 0) {
					TrackingUtils.track(TrackingUtils.ACTION_WELFARE, TrackingUtils.NAME_WELFARE_GIFT);
					Intent it = new Intent(getActivity(),GiftListActivity.class);
					startActivity(it);
				} else if (position == 1) {
					TrackingUtils.track(TrackingUtils.ACTION_WELFARE, TrackingUtils.NAME_WELFARE_KNOCK_EGG);
					if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
						IntentUtils.go2Web(getActivity(), Web.WEB_GO_EGG,null);
					}
//					UserUtils.needLogin(getActivity(),
//							new OnLoginFinishListener() {
//								@Override
//								public void loginCompleted(boolean completed) {
//									IntentUtils.go2Web(getActivity(), Web.WEB_GO_EGG,null);
//								}
//							});
				}

			}
		};
		String[] contents = contents();
		int[] headers = headerIcons();
		LinearLayout mContainer = new LinearLayout(getActivity());
		mContainer.setOrientation(LinearLayout.VERTICAL);
		View marginAboveView = null;
		for (int i = 0; i < headers.length; i++) {
			if (i % 3 == 0 && i != 0) {
				View margin1 = ViewUtils.createMargin(getActivity());
				View margin2 = ViewUtils.createMargin(getActivity());
				View margin3 = ViewUtils.createMargin(getActivity());
				mContainer.addView(margin1);
				mContainer.addView(margin2);
				mContainer.addView(margin3);
				if (marginAboveView != null) {
					marginAboveView.findViewById(E_id.item_line_1).setVisibility(View.GONE);
				}
			}
			View item = createItem();
			item.findViewById(E_id.item_icon).setBackgroundResource(headers[i]);
			((TextView) item.findViewById(E_id.item_content)).setText(contents[i]);
			setListener(item, i);
			mContainer.addView(item);
			marginAboveView = item;
//			if (i == headers.length - 1) {
//				marginAboveView.findViewById(E_id.item_line_1).setVisibility(View.GONE);
//			}
		}
		return new View[] { mContainer };
	}

	@Override
	public void init(Bundle bundle) {
		mListView.setPullRefreshEnable(false);
		mListView.setPullLoadEnable(false);
		mListView.setEnable(false);
	}

	@Override
	public BaseAdapter adapter() {
		return null;
	}

	@Override
	public boolean needRequestData() {
		return false;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		return null;
	}

	private View createItem() {
		return ViewUtils.createView(getActivity(),
				E_layout.efun_pd_welfare_item);
	}

	private int[] headerIcons() {
		return new int[] { E_drawable.efun_pd_welfare_gifts,
				E_drawable.efun_pd_welfare_crush_eggs };
	}

	private String[] contents() {
		return getResources().getStringArray(E_array.efun_pd_welfare_list);
	}

	private void setListener(View v, final int position) {
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnEfunItemClickListener != null) {
					mOnEfunItemClickListener.onItemClick(position);
				}
			}
		});
	}
}
