package com.efun.platform.module.welfare.adapter;

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
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.welfare.bean.GiftItemBean;
import com.efun.platform.utils.TimeFormatUtil;
import com.efun.platform.widget.CurProgressBar;
import com.efun.platform.widget.RoundCornerTextView;
/**
 * 礼包列表适配器
 * @author Jesse
 */
public class GiftAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private Context mContext;
	private ArrayList<GiftItemBean> mGifts;
	private String titleStr;
	public GiftAdapter(Context context) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mGifts = new ArrayList<GiftItemBean>();
	}

	@Override
	public int getCount() {
		return mGifts.size();
	}

	@Override
	public Object getItem(int position) {
		return mGifts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * 翻页
	 * @param array
	 */
	public void append(ArrayList<GiftItemBean> array){
		mGifts.addAll(array);
		notifyDataSetChanged();
	}
	/**
	 * 刷新
	 * @param array
	 */
	public void refresh(ArrayList<GiftItemBean> array){
		mGifts.clear();
		append(array);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(E_layout.efun_pd_welfare_gift_list_item, null);
			holder.mRoundCornerTextView= (RoundCornerTextView) convertView.findViewById(E_id.item_text);
			holder.mIcon = (ImageView) convertView.findViewById(E_id.item_icon);
			holder.mTitle = (TextView) convertView.findViewById(E_id.item_title);
			holder.mCount = (TextView) convertView.findViewById(E_id.item_count);
			holder.mTime = (TextView) convertView.findViewById(E_id.item_time);
			holder.mCurProgressBar=  (CurProgressBar) convertView.findViewById(E_id.curProgressBar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(mGifts != null ){
			ImageManager.displayImage(mGifts.get(position).getIcon(), holder.mIcon,ImageManager.IMAGE_SQUARE);			
//			holder.mTitle.setText(mGifts.get(position).getGameName());
			holder.mCount.setText((int)((1 - Double.valueOf(mGifts.get(position).getUsePercent()))*100)+"%");
			holder.mTime.setText(TimeFormatUtil.StringFormatDate2(mGifts.get(position).getActiveEndTime()));
			holder.mRoundCornerTextView.setColor(mContext.getResources().getColor(E_color.e_00aaeb));
//			holder.mRoundCornerTextView.setText(mGifts.get(position).getGoodsName());
			holder.mCurProgressBar.setThumb(mContext.getResources().getDrawable(E_drawable.efun_pd_progress_point));
			holder.mCurProgressBar.setProgress((int) ((1 - Double.valueOf(mGifts.get(position).getUsePercent()))*100));			
		
			titleStr = mGifts.get(position).getGoodsName();
			if(titleStr.contains("-")){
				int lastIndex = titleStr.lastIndexOf("-");
				titleStr = titleStr.substring(lastIndex+1, titleStr.length());				
			}
			holder.mTitle.setText(titleStr);
			holder.mRoundCornerTextView.setText(mGifts.get(position).getGameName());
		}
		
		return convertView;
	}

	public static class ViewHolder {
		public TextView mTitle, mCount, mTime;
		public RoundCornerTextView mRoundCornerTextView;
		public ImageView mIcon;
		public CurProgressBar mCurProgressBar;
	}

}
