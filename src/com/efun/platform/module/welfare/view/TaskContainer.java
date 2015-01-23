package com.efun.platform.module.welfare.view;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.module.welfare.bean.ActExtensionTaskBean;
import com.efun.platform.widget.ListContainer;
/**
 * 任务列表
 * @author Jesse
 *
 */
public class TaskContainer extends ListContainer{
	private final String KEY_Number = "KEY_Number";
	private final String KEY_Content = "KEY_Content";
	private final String KEY_Status = "KEY_Status";
	private final String CURRENT_STATS_COMPLETED = "2";
	public TaskContainer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public TaskContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decorateItemView(View itemView, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveItemViews(View itemView, HashMap<String, View> itemMap) {
		itemMap.put(KEY_Number, itemView.findViewById(E_id.text_1));
		itemMap.put(KEY_Content, itemView.findViewById(E_id.text_2));
		itemMap.put(KEY_Status, itemView.findViewById(E_id.imageview));
	}

	@Override
	public void setValuesInItem(HashMap<String, View> itemMap, int index,Object object) {
		ActExtensionTaskBean taskItemBean = (ActExtensionTaskBean) object;
		TextView mNumber = (TextView) itemMap.get(KEY_Number);
		TextView mContent = (TextView) itemMap.get(KEY_Content);
		ImageView mStatus = (ImageView) itemMap.get(KEY_Status);
		mNumber.setText((index+1)+"");
		mContent.setText(taskItemBean.getTaskTitle());
		if(taskItemBean.getCurrentState().equals(CURRENT_STATS_COMPLETED)){
			mStatus.setBackgroundResource(E_drawable.efun_pd_completed);
			mContent.setTextColor(getResources().getColor(E_color.e_5d340c));
			mNumber.setTextColor(getResources().getColor(E_color.e_5d340c));
		}
	}

	@Override
	public View createItemViewIfNoRes() {
		return null;
	}

}
