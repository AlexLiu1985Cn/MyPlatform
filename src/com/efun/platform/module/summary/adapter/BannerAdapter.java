package com.efun.platform.module.summary.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunResourceUtil;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.image.ImageManager;
import com.efun.platform.image.core.DisplayImageOptions;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.summary.bean.BannerItemBean;
import com.efun.platform.module.summary.bean.SummaryItemBean;
import com.efun.platform.utils.TimeFormatUtil;

/**
 * Banner Adapter
 * 
 * @author Jesse
 * 
 */
public class BannerAdapter extends PagerAdapter {
	private OnEfunItemClickListener mOnEfunItemClickListener;
	private ArrayList<SummaryItemBean> mImages;
	private Context mContext;
	
	public BannerAdapter(Context context) {
		this.mContext = context;
	}

	public void refresh(ArrayList<SummaryItemBean> images){
		this.mImages = images;
		notifyDataSetChanged();
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return mImages.size()/2;
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {		
			View mView = LayoutInflater.from(mContext).inflate(EfunResourceUtil.findLayoutIdByName(mContext, "efun_pd_banner_item"), null);
			LinearLayout banner1 = (LinearLayout)mView.findViewById(E_id.banner_item1);
			LinearLayout banner2 = (LinearLayout)mView.findViewById(E_id.banner_item2);
			TextView title1 = (TextView) mView.findViewById(E_id.banner_title1);
			TextView title2 = (TextView) mView.findViewById(E_id.banner_title2);
			TextView content1 = (TextView) mView.findViewById(E_id.banner_content1);
			TextView content2 = (TextView) mView.findViewById(E_id.banner_content2);
			TextView time1 = (TextView) mView.findViewById(E_id.banner_time1);
			TextView time2 = (TextView) mView.findViewById(E_id.banner_time2);
			ImageView notice1 = (ImageView) mView.findViewById(E_id.banner_notice1);
			ImageView notice2 = (ImageView) mView.findViewById(E_id.banner_notice2);
			title1.setText(mImages.get(position*2).getGameName());
			title2.setText(mImages.get((position+1)*2-1).getGameName());
			content1.setText(mImages.get(position*2).getTitle());
			content2.setText(mImages.get((position+1)*2-1).getTitle());
			time1.setText(TimeFormatUtil.LongFormatDate4(mImages.get(position*2).getCrtime()));
			time2.setText(TimeFormatUtil.LongFormatDate4(mImages.get((position+1)*2-1).getCrtime()));
			switch (mImages.get(position*2).getType()) {
			case 0:
				notice1.setBackgroundResource(E_drawable.efun_pd_summary_banner_notice);
				break;

			case 1:
				notice1.setBackgroundResource(E_drawable.efun_pd_summary_banner_news);
				break;
			case 2:
				notice1.setBackgroundResource(E_drawable.efun_pd_summary_banner_guide);
				break;
			case 4:
				notice1.setBackgroundResource(E_drawable.efun_pd_summary_banner_event);
				break;
			}
			switch (mImages.get((position+1)*2-1).getType()) {
			case 0:
				notice2.setBackgroundResource(E_drawable.efun_pd_summary_banner_notice);
				break;
			case 1:
				notice2.setBackgroundResource(E_drawable.efun_pd_summary_banner_news);
				break;
			case 2:
				notice2.setBackgroundResource(E_drawable.efun_pd_summary_banner_guide);
				break;
			case 4:
				notice2.setBackgroundResource(E_drawable.efun_pd_summary_banner_event);
				break;
			}
			
			
			final int index = position;
			if(mOnEfunItemClickListener!=null){
				banner1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						mOnEfunItemClickListener.onItemClick(index*2);
					}
				});
				banner2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						mOnEfunItemClickListener.onItemClick((index+1)*2-1);
					}
				});
				view.addView(mView, 0);
			}
			return mView;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	public void setOnEfunItemClickListener(
			OnEfunItemClickListener mOnEfunItemClickListener) {
		this.mOnEfunItemClickListener = mOnEfunItemClickListener;
	}
	

}
