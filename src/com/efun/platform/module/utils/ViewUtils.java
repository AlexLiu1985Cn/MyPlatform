package com.efun.platform.module.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunLocalUtil;
import com.efun.core.tools.EfunLogUtil;
import com.efun.platform.AndroidScape.E_color;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.AndroidScape.E_style;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnEfunListener;
import com.efun.platform.module.base.impl.OnToastClickListener;
import com.efun.platform.widget.list.XListView;

/**
 * 创建View类
 * 
 * @author Jesse
 *
 */
public class ViewUtils {
	public static View createView(LayoutInflater inflater, int layoutId) {
		return inflater.inflate(layoutId, null);
	}

	public static View createView(Context context, int layoutId) {
		return LayoutInflater.from(context).inflate(layoutId, null);
	}

	/**
	 * 创建Margin 40px View
	 * 
	 * @return
	 */
	public static View createMargin(Context context) {
		View margin = createView(context, E_layout.efun_pd_margin_40);
		return margin;
	}

	/**
	 * 创建加载框
	 * 
	 * @param context
	 * @return
	 */
	public static View createLoading(Context context) {
		View view = createView(context, E_layout.efun_pd_async_loading);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		view.setBackgroundColor(context.getResources().getColor(
				E_color.e_efeff4));
		return view;
	}

	/**
	 * 创建底部按钮
	 * 
	 * @param context
	 * @return
	 */
	public static View createBottom(Context context) {
		return createView(context, E_layout.efun_pd_game_detail_tab_bottom);
	}

	/**
	 * 创建WebView
	 * 
	 * @param context
	 * @return
	 */
	public static View createWebView(Context context) {
		return createView(context, E_layout.efun_pd_website);
	}

	/**
	 * 创建ListView
	 * 
	 * @return
	 */
	public static XListView createListView(Context context) {
		XListView listDetail = new XListView(context);
		listDetail.setPullRefreshEnable(false);
		listDetail.setPullLoadEnable(false);
		listDetail.setVerticalScrollBarEnabled(false);
		listDetail.setDivider(new ColorDrawable(Color.TRANSPARENT));
		listDetail.setDividerHeight(0);
		listDetail
				.setPersistentDrawingCache(ViewGroup.PERSISTENT_SCROLLING_CACHE);
		listDetail.setSelector(new ColorDrawable(Color.TRANSPARENT));
		return listDetail;
	}

	/**
	 * 创建ListView
	 * 
	 * @return
	 */
	public static XListView createListView2(Context context) {
		XListView listDetail = new XListView(context);
		listDetail.setPullLoadEnable(true);
		listDetail.setVerticalScrollBarEnabled(false);
		listDetail.setDivider(new ColorDrawable(Color.TRANSPARENT));
		listDetail.setDividerHeight(0);
		listDetail
				.setPersistentDrawingCache(ViewGroup.PERSISTENT_SCROLLING_CACHE);
		listDetail.setSelector(new ColorDrawable(Color.TRANSPARENT));
		return listDetail;
	}

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(E_layout.efun_pd_loading, null);
		TextView msgTv = (TextView) v.findViewById(E_id.text_1);
		msgTv.setText(msg);
		Dialog loadingDialog = new Dialog(context, E_style.DL_Dialog);
		loadingDialog.setCancelable(false);
		loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;

	}

	public static Dialog createLoginWaitingDialog(Context context, String msg) {
		return createLoginWaitingDialog(context, msg, null, null);
	}

	public static Dialog createToast(Context context, String msg) {
		LayoutInflater inflater = LayoutInflater.from(context);
		final View v = inflater.inflate(E_layout.efun_pd_toast, null);
		TextView msgTv = (TextView) v.findViewById(E_id.text_1);
		msgTv.setText(msg);
		final Dialog loadingDialog = new Dialog(context, E_style.DL_Dialog);
		loadingDialog.setCancelable(false);
		loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		loadingDialog.show();
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (loadingDialog != null) {
					loadingDialog.dismiss();
				}
			}
		});
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (loadingDialog != null) {
					loadingDialog.dismiss();
				}
			}
		}, 3000);
		return loadingDialog;
	}

	public static Dialog createToastUpdate(Context context,
			final OnEfunItemClickListener onEfunItemClickListener,
			final boolean isForce) {
		LayoutInflater inflater = LayoutInflater.from(context);
		final View v = inflater.inflate(E_layout.efun_pd_toast_update, null);
		TextView msgTv = (TextView) v.findViewById(E_id.item_content);
		TextView okBtn = (TextView) v.findViewById(E_id.text_1);
		TextView cancelBtn = (TextView) v.findViewById(E_id.text_2);
		TextView forceOkBtn = (TextView) v.findViewById(E_id.text_3);
		RelativeLayout btnLayout = (RelativeLayout) v.findViewById(E_id.contaier_relative_1);
		RelativeLayout btnLayoutForce = (RelativeLayout) v.findViewById(E_id.contaier_relative_2);
		final Dialog loadingDialog = new Dialog(context,E_style.Transparent);
		loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		if (isForce) {
			msgTv.setText(E_string.efun_pd_notification_update_2);
			forceOkBtn.setText(E_string.efun_pd_sure);
			btnLayout.setVisibility(View.GONE);
			btnLayoutForce.setVisibility(View.VISIBLE);
			loadingDialog.setCancelable(false);
			forceOkBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (loadingDialog != null) {
						if (onEfunItemClickListener != null)
							onEfunItemClickListener.onItemClick(0);
					}
				}
			});
		} else {
			msgTv.setText(E_string.efun_pd_notification_update_1);
			btnLayout.setVisibility(View.VISIBLE);
			btnLayoutForce.setVisibility(View.GONE);
			okBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (onEfunItemClickListener != null)
						onEfunItemClickListener.onItemClick(0);
				}
			});
			cancelBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (loadingDialog != null) {
						loadingDialog.dismiss();
					}
				}
			});
			
		}
		loadingDialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
		loadingDialog.show();
		return loadingDialog;
	}

	public static Dialog createLoginWaitingDialog(Context context, String msg,
			final OnEfunListener onEfunListener, Handler handler) {
		LayoutInflater inflater = LayoutInflater.from(context);
		final View v = inflater.inflate(E_layout.efun_pd_toast, null);
		TextView msgTv = (TextView) v.findViewById(E_id.text_1);
		msgTv.setText(msg);
		final Dialog loadingDialog = new Dialog(context, E_style.DL_Transparent);
		loadingDialog.setCancelable(false);
		loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		loadingDialog.show();
		if (handler != null) {
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					if (onEfunListener != null) {
						loadingDialog.dismiss();
						((OnToastClickListener) onEfunListener).onClick();
					}
				}
			}, 3000);
		}
		return loadingDialog;
	}
}
