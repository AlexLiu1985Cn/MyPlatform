package com.efun.platform.module.welfare.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.PopWindow;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.module.utils.PopUtils;
import com.efun.platform.module.welfare.bean.ActItemBean;
import com.efun.platform.utils.TimeFormatUtil;
/**
 * 活动列表适配器
 * @author Jesse
 *
 */
public class ActAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private ArrayList<ActItemBean> mActs;
	private PopWindow mSharePop;
	private Context mContext;

	public ActAdapter(Context context) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mActs = new ArrayList<ActItemBean>();
	}

	@Override
	public int getCount() {
		return mActs.size();
	}

	@Override
	public Object getItem(int position) {
		return mActs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 翻页
	 * @param array
	 */
	public void append(ArrayList<ActItemBean> array){
		mActs.addAll(array);
		notifyDataSetChanged();
	}
	/**
	 * 刷新
	 * @param array
	 */
	public void refresh(ArrayList<ActItemBean> array){
		mActs.clear();
		append(array);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(E_layout.efun_pd_welfare_act_list_item, null);
			holder.mIcon = (ImageView) convertView.findViewById(E_id.item_icon);
			holder.mTitle = (TextView) convertView.findViewById(E_id.item_title);
			holder.mTime = (TextView) convertView.findViewById(E_id.item_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(mActs != null){			
			holder.mTitle.setText(mActs.get(position).getActivityName());
			ImageManager.displayImage(mActs.get(position).getImg(), holder.mIcon ,ImageManager.IMAGE_RECTANGLE_H);
			holder.mTime.setText(TimeFormatUtil.StringFormatDate(mActs.get(position).getStartTime()));
		}
		
		return convertView;
	}

	public static class ViewHolder {
		public TextView mTitle,mTime;
		public ImageView mIcon;
	}

}
