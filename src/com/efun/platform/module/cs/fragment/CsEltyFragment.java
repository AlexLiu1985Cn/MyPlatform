package com.efun.platform.module.cs.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.FrameTabContainer;
import com.efun.platform.AndroidScape.E_array;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.CsReplyStatusRequest;
import com.efun.platform.http.request.bean.GameListRequest;
import com.efun.platform.http.response.bean.AccountGetUserFBUidsByUidResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.CsReplyStatusResponse;
import com.efun.platform.module.base.ElasticityFragment;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnLoginFinishListener;
import com.efun.platform.module.cs.activity.CsAskActivity;
import com.efun.platform.module.cs.activity.CsQuestionActivity;
import com.efun.platform.module.cs.activity.CsReplyActivity;
import com.efun.platform.module.cs.bean.CsReplyStatusBean;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.Tab;
/**
 * 客服页面
 * @author Jesse
 *
 */
public class CsEltyFragment extends ElasticityFragment {
	private OnEfunItemClickListener mOnEfunItemClickListener;
	private boolean completed = true;
	@Override
	public View[] addHeaderViews() {
		mOnEfunItemClickListener = new OnEfunItemClickListener() {
			@Override
			public void onItemClick(int position) {
				if (position == 0) {
					TrackingUtils.track(TrackingUtils.ACTION_CS, TrackingUtils.NAME_CS_QUESTION);
					Intent it = new Intent(getActivity(), CsQuestionActivity.class);
					startActivity(it);
				} else if (position == 1) {
					// 登陆
					if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
						TrackingUtils.track(TrackingUtils.ACTION_CS, TrackingUtils.NAME_CS_ASK);
						Intent it = new Intent(getActivity(), CsAskActivity.class);
						startActivity(it);
					}
				} else if (position == 2) {
					if(hasNewReplay!=null){
						hasNewReplay.setVisibility(View.GONE);
					}
					// 登陆
					if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null && !EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
						TrackingUtils.track(TrackingUtils.ACTION_CS, TrackingUtils.NAME_CS_REPLY);
						Intent it = new Intent(getActivity(), CsReplyActivity.class);
						startActivity(it);
					}
				} 
			}
		};
		
		String[] contents = contents();
		int[] headers = headerIcons();
		LinearLayout mContainer = new LinearLayout(getActivity());
		mContainer.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < headers.length; i++) {
			View item = createItem();
			item.findViewById(E_id.item_icon).setBackgroundResource(headers[i]);
			((TextView) item.findViewById(E_id.item_content)).setText(contents[i]);
			setListener(item, i);
			mContainer.addView(item);
		}
		return new View[] { mContainer };
	}

	@Override
	public void init(Bundle bundle) {
		mListView.setPullRefreshEnable(false);
		mListView.setPullLoadEnable(false);
	}

	@Override
	public BaseAdapter adapter() {
		return null;
	}

	@Override
	public boolean needRequestData() {
		return true;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		completed = false;
		String uid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
		CsReplyStatusRequest request = new CsReplyStatusRequest(uid);
		request.setReqType(IPlatformRequest.REQ_CS_REPLY_STATUS);
		return new BaseRequestBean[]{request};
	}

	private View createItem() {
		View view = ViewUtils.createView(getActivity(), E_layout.efun_pd_welfare_item);
		hasNewReplay = view.findViewById(E_id.item_text);
		return view;
	}
	
	private View hasNewReplay;
	
	@Override
	public void onResume() {
		super.onResume();
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser()==null){
			hasNewReplay.setVisibility(View.GONE);
		}
		if(allow || (FrameTabContainer.lastTag==Tab.TAB_ITEM_TAG_CUSTOMSERVICE)){
			requestReplyStatus();
		}
	}
	
	private boolean allow = true;
	
	private int[] headerIcons() {
		return new int[] { E_drawable.efun_pd_cs_question, E_drawable.efun_pd_cs_ask,  E_drawable.efun_pd_cs_reply };
	}

	private String[] contents() {
		return getResources().getStringArray(E_array.efun_pd_cs_list);
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

	
	public void requestReplyStatus(){
		if(GameToPlatformParamsSaveUtil.getInstanse().getUser()!=null && completed){
			completed = false;
			String uid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
			CsReplyStatusRequest request = new CsReplyStatusRequest(uid);
			request.setReqType(IPlatformRequest.REQ_CS_REPLY_STATUS);
			requestWithoutDialog(new BaseRequestBean[]{request});
		}
	}
	
	@Override
	public void onSuccess(int requestType, BaseResponseBean response) {
		super.onSuccess(requestType, response);
		if(requestType==IPlatformRequest.REQ_CS_REPLY_STATUS){
			completed = true;
			allow = false;
			CsReplyStatusResponse mCsReplyStatusResponse = (CsReplyStatusResponse) response;
			CsReplyStatusBean mCsReplyStatusBean = mCsReplyStatusResponse.getCsReplyStatusBean();
			if(mCsReplyStatusBean.getCode().equals(HttpParam.RESULT_1000)){
				hasNewReplay.setVisibility(View.VISIBLE);
			}else{
				hasNewReplay.setVisibility(View.GONE);
			}
		}		
	}

}
