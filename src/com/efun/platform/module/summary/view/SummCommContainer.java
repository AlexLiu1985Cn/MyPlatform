package com.efun.platform.module.summary.view;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.module.summary.bean.SummaryItemBean;
import com.efun.platform.utils.TimeFormatUtil;
import com.efun.platform.widget.ListCommContainer;

/**
 * 資訊列表
 * @author Jesse
 * 
 */
public class SummCommContainer extends ListCommContainer {
	/**
	 * KEY 存放 标题控件
	 */
	private final String KEY_Title = "KEY_Title";
	/**
	 * KEY 存放 事件控件
	 */
	private final String KEY_CreateDate = "KEY_CreateDate";

	public SummCommContainer(Context context) {
		super(context);
	}

	public SummCommContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void saveItemViews(View itemView, HashMap<String, View> itemMap) {
		itemMap.put(KEY_Title, itemView.findViewById(E_id.summary_title));
		itemMap.put(KEY_CreateDate, itemView.findViewById(E_id.summary_time));
	}

	@Override
	public void setValuesInItem(HashMap<String, View> itemMap,int position,Object data) {
		SummaryItemBean bean =  (SummaryItemBean) data;
		TextView mTitle = (TextView) itemMap.get(KEY_Title);
		TextView mCreateDate = (TextView) itemMap.get(KEY_CreateDate);
		mTitle.setText(bean.getTitle());
		mCreateDate.setText(TimeFormatUtil.LongFormatDate4(bean.getCrtime()));
	}

	@Override
	public void decorateItemView(View itemView, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View createItemViewIfNoRes() {
		// TODO Auto-generated method stub
		return null;
	}

}
