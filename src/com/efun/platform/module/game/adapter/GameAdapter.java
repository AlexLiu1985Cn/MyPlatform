package com.efun.platform.module.game.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.image.ImageManager;
import com.efun.platform.module.game.bean.GameItemBean;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.module.utils.TrackingUtils;
import com.efun.platform.utils.Const.AppStatus;
/**
 * 游戏列表适配器
 * @author Jesse
 *
 */
public class GameAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private ArrayList<GameItemBean> mArray;
	private Context mContext;
	public GameAdapter(Context context) {
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mArray = new ArrayList<GameItemBean>();
	}
	/**
	 * 翻页
	 * @param array
	 */
	public void append(ArrayList<GameItemBean> array){
		mArray.addAll(array);
		notifyDataSetChanged();
	}
	/**
	 * 刷新
	 * @param array
	 */
	public void refresh(ArrayList<GameItemBean> array){
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(E_layout.efun_pd_game_list_item, null);
			holder.mIcon = (ImageView) convertView.findViewById(E_id.item_icon);
			holder.mTitle = (TextView) convertView.findViewById(E_id.item_title);
			holder.mContent = (TextView) convertView.findViewById(E_id.item_content);
			holder.mCategory = (TextView) convertView.findViewById(E_id.item_category);
			holder.mCount = (TextView) convertView.findViewById(E_id.item_count);
			holder.mButton = (ImageView) convertView.findViewById(E_id.item_button);
			holder.mText = (TextView) convertView.findViewById(E_id.item_text);
//			holder.mBodyLayout = (View) convertView.findViewById(E_id.item_game_body);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	
		convertView.setVisibility(View.VISIBLE);
//		holder.mBodyLayout.setVisibility(View.VISIBLE);
		
		ImageManager.displayImage(mArray.get(position).getSmallpic(), holder.mIcon,ImageManager.IMAGE_SQUARE);
		holder.mTitle.setText(mArray.get(position).getGameName());
		holder.mContent.setText(mArray.get(position).getGameDesc());
		holder.mCategory.setText(mArray.get(position).getGameType());
		holder.mCount.setText(mArray.get(position).getLike()+ mContext.getString(E_string.efun_pd_game_item_zan));
		final int index = position;
		if(AppUtils.isAppInstalled(mContext, mArray.get(position).getPackageName())){
			if(AppUtils.isAppUpdate(mContext, mArray.get(position).getPackageName(), mArray.get(position).getAndroidVersion())){
				holder.mButton.setBackgroundResource(E_drawable.efun_pd_game_update_selector);
				holder.mButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						TrackingUtils.track(TrackingUtils.ACTION_GAME, mArray.get(position).getGameName()+TrackingUtils.NAME_GAME_UPDATE);
						AppUtils.download(mContext, mArray.get(index).getAndroidDownload());
					}
				});
				mArray.get(position).setStatus(AppStatus.UPDATE);
			}else{
//				convertView.setVisibility(View.GONE);
//				holder.mBodyLayout.setVisibility(View.GONE);
//				return convertView;
				holder.mButton.setBackgroundResource(E_drawable.efun_pd_game_download_selector);
				holder.mButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						TrackingUtils.track(TrackingUtils.ACTION_GAME, mArray.get(position).getGameName()+TrackingUtils.NAME_GAME_DOWNLOAD);
						AppUtils.download(mContext, mArray.get(index).getAndroidDownload());
					}
				});
				mArray.get(position).setStatus(AppStatus.DOWNLOAD);
			}
		}else{
			holder.mButton.setBackgroundResource(E_drawable.efun_pd_game_download_selector);
			holder.mButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TrackingUtils.track(TrackingUtils.ACTION_GAME, mArray.get(position).getGameName()+TrackingUtils.NAME_GAME_DOWNLOAD);
					AppUtils.download(mContext, mArray.get(index).getAndroidDownload());
				}
			});
			mArray.get(position).setStatus(AppStatus.DOWNLOAD);
		}
		return convertView;
	}

	public static class ViewHolder {
		public TextView mTitle, mContent, mCategory, mCount, mText;
		public ImageView mIcon, mButton;
		public View mBodyLayout;
	}

}
