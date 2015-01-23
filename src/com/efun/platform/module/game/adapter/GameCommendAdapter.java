package com.efun.platform.module.game.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape.E_dimens;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.game.bean.GameCommendItemBean;
import com.efun.platform.utils.TimeFormatUtil;
/**
 * 游戏评论列表适配器
 * @author Jesse
 *
 */
public class GameCommendAdapter extends BaseAdapter {

	private LayoutInflater mLayoutInflater;
	private ArrayList<GameCommendItemBean> mArray;
	public GameCommendAdapter(Context context) {
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mArray = new ArrayList<GameCommendItemBean>();
	}
	/**
	 * 翻页
	 * @param array
	 */
	public void insert(GameCommendItemBean bean){
		mArray.add(0,bean);
		notifyDataSetChanged();
	}
	/**
	 * 翻页
	 * @param array
	 */
	public void append(ArrayList<GameCommendItemBean> array){
		mArray.addAll(array);
		notifyDataSetChanged();
	}
	/**
	 * 刷新
	 * @param array
	 */
	public void refresh(ArrayList<GameCommendItemBean> array){
		mArray.clear();
		append(array);
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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(E_layout.efun_pd_game_commend_list_item, null);
			holder.mIcon = (ImageView) convertView.findViewById(E_id.item_icon);
			holder.mTitle = (TextView) convertView.findViewById(E_id.item_title);
			holder.mContent = (TextView) convertView.findViewById(E_id.item_content);
			holder.mTime = (TextView) convertView.findViewById(E_id.item_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageManager.displayImage(mArray.get(position).getIcon(), holder.mIcon, ImageManager.IMAGE_ROUND_USER,E_dimens.e_size_8);
		if(EfunStringUtil.isEmpty(mArray.get(position).getUserName())){			
			holder.mTitle.setText(E_string.efun_pd_anonymous);			
		}else{
			holder.mTitle.setText(mArray.get(position).getUserName());			
		}
		holder.mTime.setText(TimeFormatUtil.LongFormatDate(mArray.get(position).getModifiedTime()));
		String content = mArray.get(position).getContent();
		if(!EfunStringUtil.isEmpty(content)){
			content = content.trim();
		}
		holder.mContent.setText(content);
		return convertView;
	}

	public static class ViewHolder {
		public TextView mTitle, mContent, mTime;
		public ImageView mIcon;
	}
}
