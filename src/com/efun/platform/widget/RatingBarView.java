package com.efun.platform.widget;

import com.efun.platform.AndroidScape.E_dimens;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RatingBarView extends LinearLayout{
	private ImageView[] starIVs;
	public RatingBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RatingBarView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		starIVs = new ImageView[5];
	}
	
	//創建星星評分條
	private LayoutParams params = null;
	public void createdStarBar(final OnEfunItemClickListener mOnEfunItemClickListener){
		int margin = getResources().getDimensionPixelSize(E_dimens.e_size_5);
		int width = getResources().getDimensionPixelSize(E_dimens.e_size_80);
		for(int i = 0 ; i < 5 ; i++){
			final int position = i;
			ImageView starIV = new ImageView(getContext());
			params = new LayoutParams(width, width);
			params.setMargins(margin, 0, margin, 0);
			starIV.setBackgroundResource(E_drawable.efun_pd_ratingbar_select);
			this.addView(starIV,params);
			starIV.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(mOnEfunItemClickListener!=null){
						mOnEfunItemClickListener.onItemClick(position);
						showStar(position);
					}
				}
			});
			starIVs[i] = starIV;
		}
	}
	
	private void showStar(int position){
		for (int i = 0; i < starIVs.length; i++) {
			starIVs[i].setBackgroundResource(E_drawable.efun_pd_ratingbar_unselect);
		}
		for (int i = 0; i < position+1; i++) {
			starIVs[i].setBackgroundResource(E_drawable.efun_pd_ratingbar_select);
		}
	}
}

