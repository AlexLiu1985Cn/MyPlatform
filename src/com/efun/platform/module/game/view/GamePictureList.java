package com.efun.platform.module.game.view;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.image.ImageManager;
import com.efun.platform.widget.ListContainer;
/**
 * 游戏截图控件
 * @author Jesse
 *
 */
public class GamePictureList extends ListContainer{
	private final String KEY_Image = "KEY_Image";
	public GamePictureList(Context context) {
		super(context);
	}
	public GamePictureList(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	@Override
	public void decorateItemView(View itemView, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveItemViews(View itemView, HashMap<String, View> itemMap) {
		itemMap.put(KEY_Image, itemView);
	}

	@Override
	public void setValuesInItem(HashMap<String, View> itemMap, int index,Object object) {
		String url = (String) object;
		GameShotImageView icon = (GameShotImageView) itemMap.get(KEY_Image);
		icon.setScreenOrientation(screenOrientation);
		if(screenOrientation){
			icon.setImageBitmap(ImageManager.createBitmap(getContext(), ImageManager.IMAGE_RECTANGLE_H));
		}else{
			icon.setImageBitmap(ImageManager.createBitmap(getContext(), ImageManager.IMAGE_RECTANGLE_V));
		}
		if(!EfunStringUtil.isEmpty(url)){
			ImageManager.displayImage(url,icon);
		}
	}
	@Override
	public View createItemViewIfNoRes() {
		return new GameShotImageView(getContext());
	}

}
