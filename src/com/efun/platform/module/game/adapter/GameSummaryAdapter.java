package com.efun.platform.module.game.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.module.game.bean.GameNewsBean;
import com.efun.platform.utils.TimeFormatUtil;
import com.efun.platform.widget.ArrowView;
/**
 * 游戏资讯列表
 * @author Jesse
 *
 */
public class GameSummaryAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private Context mContext;
	private ArrayList<GameNewsBean> mArray;
	private int mNewsSize;
	public GameSummaryAdapter(Context context) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mArray = new ArrayList<GameNewsBean>();
		this.mNewsSize = 0;
	}

	/**
	 * 翻页
	 * @param array
	 */
	public void append(ArrayList<GameNewsBean> array){
		mArray.addAll(array);
		notifyDataSetChanged();
	}
	/**
	 * 刷新
	 * @param array
	 */
	public void refresh(ArrayList<GameNewsBean> array){
		mArray.clear();
		append(array);
	}
	
	public void setNewsSize(int newSize){
		this.mNewsSize = newSize;
	}
	
	@Override
	public int getCount() {
		return mArray.size();
	}

	@Override
	public Object getItem(int position) {
		return mArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		String title =  mArray.get(position).getTitle();
		if ((position == 0) || (mNewsSize <= 10 && position == mNewsSize)) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(E_layout.efun_pd_game_detail_tab_summary_header, null);
			holder.mIcon = (ImageView) convertView.findViewById(E_id.item_icon);
			holder.mTitle = (TextView) convertView.findViewById(E_id.item_title);
			holder.mCategory = (TextView) convertView.findViewById(E_id.item_category);
			holder.mTitle.setText(title);
			if (position == 0) {
				holder.mIcon.setBackgroundResource(E_drawable.efun_pd_news_icon);
				holder.mCategory.setText(E_string.efun_pd_news);
			} else if(position == mNewsSize){
				holder.mIcon.setBackgroundResource(E_drawable.efun_pd_strategy_icon);
				holder.mCategory.setText(E_string.efun_pd_strategy);
			}
		} else {
			String time = TimeFormatUtil.LongFormatDate(mArray.get(position).getCrtime());
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(E_layout.efun_pd_game_detail_tab_summary_item, null);
			holder.mContent = (TextView) convertView.findViewById(E_id.item_content);
			holder.mTime = (TextView) convertView.findViewById(E_id.item_time);
			holder.mLine1 = convertView.findViewById(E_id.item_line_1);
			holder.mLine2 = convertView.findViewById(E_id.item_line_2);
			holder.mContent.setText(title);
			holder.mTime.setText(time);
			
			if ((position + 1) % 10 == 0) {
				holder.mLine1.setVisibility(View.GONE);
				holder.mLine2.setVisibility(View.VISIBLE);
			}
		}

		return convertView;
	}

	public static class ViewHolder {
		public TextView mTitle, mContent, mCategory, mText, mTime;
		public ImageView mIcon;
		public View mLine1, mLine2;
	}
}
