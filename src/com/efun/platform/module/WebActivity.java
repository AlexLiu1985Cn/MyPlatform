package com.efun.platform.module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.efun.core.tools.EfunLocalUtil;
import com.efun.facebook.share.activity.EfunFBFunctionActivity;
import com.efun.platform.AndroidScape.E_drawable;
import com.efun.platform.AndroidScape.E_id;
import com.efun.platform.AndroidScape.E_layout;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.module.base.FixedActivity;
import com.efun.platform.module.game.bean.GameNewsBean;
import com.efun.platform.module.summary.bean.BannerItemBean;
import com.efun.platform.module.summary.bean.SummaryItemBean;
import com.efun.platform.module.utils.ToastUtils;
import com.efun.platform.module.utils.UrlUtils;
import com.efun.platform.module.welfare.bean.ActItemBean;
import com.efun.platform.utils.Const;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.Web;
import com.efun.platform.widget.TitleView;

/**
 * Web 页面
 * 
 * @author Jesse
 * 
 */
public class WebActivity extends FixedActivity {
	/**
	 * 启动本页的来源
	 */
	private int mComeFrom;
	/**
	 * url
	 */
	private BaseDataBean mBean;
	/**
	 * 当頁面没有数据的时候显示的内容 ，参考{@link ListView#setEmptyView(View)}
	 */
	private RelativeLayout emptyView;
	/**
	 * 网络异常时显示的图片
	 */
	private ImageView mNotifyImg;
	/**
	 * 网络异常时显示的文本内容
	 */
	public TextView mNotifyText;

	@Override
	public void onClickRight() {
		switch (mComeFrom) {
		case Web.WEB_GO_BANNER:
			break;
		case Web.WEB_GO_SUMMARY:
			break;
		case Web.WEB_GO_ACT:
			break;
		case Web.WEB_GO_GAME:
			break;
		}
	}

	private WebView mWebView;
	private ProgressBar mloadView;

	@SuppressLint("NewApi")
	@Override
	public void init(Bundle bundle) {
		if (bundle != null) {
			mComeFrom = bundle.getInt(Web.WEB_GO_KEY);
			if(bundle.getSerializable(Const.BEAN_KEY)!=null){
				mBean = (BaseDataBean) bundle.getSerializable(Const.BEAN_KEY);
			}
		}
		mWebView = (WebView) findViewById(E_id.websit_webview);
		mloadView = (ProgressBar)findViewById(E_id.websit_progressbar);
		emptyView = (RelativeLayout)findViewById(E_id.empty);
		mNotifyImg = (ImageView) emptyView.findViewById(E_id.center_btn);
		mNotifyText = (TextView) emptyView.findViewById(E_id.center_text);
		
		mNotifyImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View paramView) {
				mNotifyImg.setBackgroundResource(E_drawable.efun_pd_error_timeout);
				mNotifyText.setText(E_string.efun_pd_timeout_error);
				emptyView.setVisibility(View.GONE);
				mNotifyImg.setVisibility(View.GONE);
				mNotifyText.setVisibility(View.GONE);
				mWebView.setVisibility(View.VISIBLE);
				checkPage();
			}

		});

		setWebViewSetting();
		if (!EfunLocalUtil.isNetworkAvaiable(this)) {
			mWebView.setVisibility(View.GONE);
			emptyView.setVisibility(View.VISIBLE);
			mNotifyImg.setBackgroundResource(E_drawable.efun_pd_error_network);
			mNotifyText.setText(getResources().getString(E_string.efun_pd_network_error));
			mNotifyImg.setVisibility(View.VISIBLE);
			mNotifyText.setVisibility(View.VISIBLE);
			mNotifyImg.setOnClickListener(null);
			return;
		} 
		checkPage();		
		
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void setWebViewSetting() {
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setBuiltInZoomControls(true);
		settings.setSupportZoom(true);
		settings.setDomStorageEnabled(true);
		settings.setDefaultFontSize(20);// 设置默认的字体大小
		// 设置自动加载图片
		settings.setLoadsImagesAutomatically(true);
		// 如果需要用户输入账号密码，必须设置支持手势焦点
		mWebView.requestFocusFromTouch();

		mWebView.setWebViewClient(new WebViewClient() {
			// 加载错误时调用，一般提示错误信息
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				Log.d("efun", "errorCode:" + errorCode);
				Log.d("efun", "failingUrl:" + failingUrl);
				if (failingUrl.contains(getResources().getString(
						E_string.efun_pd_url_base))
						|| failingUrl.contains(getResources().getString(
								E_string.efun_pd_url_web_base))) {
					mWebView.setVisibility(View.GONE);
					emptyView.setVisibility(View.VISIBLE);
					mNotifyImg.setVisibility(View.VISIBLE);
					mNotifyText.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				// TODO Auto-generated method stub
				// super.onReceivedSslError(view, handler, error);
				handler.proceed();
			}

			@Override
			public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
				// TODO Auto-generated method stub
				return super.shouldOverrideKeyEvent(view, event);
			}

			// 用于加载新Webview之前，一般在此加载缓冲区
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				mloadView.setVisibility(View.VISIBLE);
			}

			// 用于加载新Webview之后，一般在此消除缓冲区
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				mloadView.setVisibility(View.GONE);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				Log.d("efun", "weburl:" + url);
				view.loadUrl(url);
				return true;
			}

		});

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				mloadView.setProgress(newProgress);
			}
		});

	}

	private String createEggUrl() {
		String url = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(
				UrlUtils.PLATFORM_PRE_WEB_KEY,mContext)
				+ mContext.getString(E_string.efun_pd_method_knock_egg);
		url = url + "?uid=" + GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid()
				+ "&fromType=" + HttpParam.APP;
		return url;
	}

	private String createMemberDescUrl() {
		String url = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(
				UrlUtils.PLATFORM_PRE_WEB_KEY,mContext)
				+ mContext.getString(E_string.efun_pd_method_member_desc);
		return url;
	}
	//http://www.efuntw.com/vip/vip_member.html?fromType=app&uid=1&userName=tink
	private String createVipUrl() {
		String url = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(
				UrlUtils.PLATFORM_PRE_WEB_KEY,mContext)
				+ mContext.getString(E_string.efun_pd_method_vip);
		url = url +"?fromType="+HttpParam.APP+"&uid="+GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid()+"&userName="+GameToPlatformParamsSaveUtil.getInstanse().getUser().getAccountName();
		return url;
	}

	private String createAboutUsUrl() {
		String url = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(
				UrlUtils.PLATFORM_PRE_WEB_KEY,mContext)
				+ mContext.getString(E_string.efun_pd_method_about_us);
		return url;
	}
	@Override
	public void initTitle(TitleView titleView) {
		switch (mComeFrom) {
		case Web.WEB_GO_BANNER:
			titleView.setTitleRightStatus(View.GONE);
			break;
		case Web.WEB_GO_GAME:
		case Web.WEB_GO_SUMMARY:
			titleView.setTitleCenterRes(E_string.efun_pd_summary_content, false);
//			titleView.setTitleRightRes(E_drawable.efun_pd_share_selector);
//			hasShare = true;
//			titleView.setPopWindowEnable(PopWindow.POP_WINDOW_SHARE, mBean);
			titleView.setTitleRightStatus(View.GONE);
			break;
		case Web.WEB_GO_ACT:
			titleView.setPopWindowEnable(PopWindow.POP_WINDOW_SHARE, mBean);
			titleView.setTitleRightRes(E_drawable.efun_pd_share_selector);
			titleView.setTitleCenterRes(E_string.efun_pd_act_content, false);
			break;
		case Web.WEB_GO_EGG:
			titleView.setTitleRightStatus(View.GONE);
			titleView.setTitleCenterRes(E_string.efun_pd_egg, false);
			break;
		case Web.WEB_GO_GOLDVALUE:
			titleView.setTitleRightStatus(View.GONE);
			titleView.setTitleCenterRes(E_string.efun_pd_member_gold_value, false);
			break;
		case Web.WEB_GO_US:
			titleView.setTitleRightStatus(View.GONE);
			titleView.setTitleCenterRes(E_string.efun_pd_about_us, false);
			break;
		case Web.WEB_GO_VIP:
			titleView.setTitleRightStatus(View.GONE);
			titleView.setTitleCenterRes(E_string.efun_pd_vip, false);
			break;
		}

	}
	private void checkPage() {
		switch (mComeFrom) {
		case Web.WEB_GO_BANNER:
			mWebView.loadUrl(((BannerItemBean)mBundle.getSerializable(Const.BEAN_KEY)).getUrl());
			break;
		case Web.WEB_GO_SUMMARY:
			mWebView.loadUrl(((SummaryItemBean)mBundle.getSerializable(Const.BEAN_KEY)).getIphoneUrl());
			break;
		case Web.WEB_GO_ACT:
			mWebView.loadUrl(((ActItemBean)mBundle.getSerializable(Const.BEAN_KEY)).getActivityUrl());
			break;
		case Web.WEB_GO_GAME:
			mWebView.loadUrl(((GameNewsBean)mBundle.getSerializable(Const.BEAN_KEY)).getIphoneUrl());
			break;
		case Web.WEB_GO_EGG:
			mWebView.loadUrl(createEggUrl());
			break;
		case Web.WEB_GO_GOLDVALUE:
			mWebView.loadUrl(createMemberDescUrl());
			break;	
		case Web.WEB_GO_US:
			mWebView.loadUrl(createAboutUsUrl());
			break;
		case Web.WEB_GO_VIP:
			mWebView.loadUrl(createVipUrl());
			break;
		}
	}

	@Override
	public ViewGroup[] needShowLoading() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int LayoutId() {
		// TODO Auto-generated method stub
		return E_layout.efun_pd_website;
	}

	@Override
	public boolean needRequestData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseRequestBean[] needRequestDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == EfunFBFunctionActivity.EXTRA_SHARE_DIALOG_FLAG) {
			if(resultCode == EfunFBFunctionActivity.EXTRA_SUCCESS) {
				ToastUtils.toast(WebActivity.this, E_string.efun_pd_share_success);
			} else {
				ToastUtils.toast(WebActivity.this, E_string.efun_pd_share_failure);
			}
			
		}
	}


}
