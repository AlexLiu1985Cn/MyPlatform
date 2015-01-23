package com.efun.platform.entrance;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.efun.core.db.EfunDatabase;
import com.efun.core.res.EfunResConfiguration;
import com.efun.core.tools.EfunLogUtil;
import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.AndroidScape;
import com.efun.platform.FrameTabContainer;
import com.efun.platform.bean.EfunPlatformParamsBean;
import com.efun.platform.bean.FloatingItemBean;
import com.efun.platform.http.IPlatController;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.http.request.IPlatRequest;
import com.efun.platform.http.request.bean.AccountLoginRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.FloatingButtonRequest;
import com.efun.platform.http.request.bean.SettingCheckRequest;
import com.efun.platform.http.response.bean.AccountResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.FloatingButtonResponse;
import com.efun.platform.http.response.bean.SettingCheckResponse;
import com.efun.platform.image.cache.disc.naming.Md5FileNameGenerator;
import com.efun.platform.image.core.ImageLoader;
import com.efun.platform.image.core.ImageLoaderConfiguration;
import com.efun.platform.image.core.assist.QueueProcessingType;
import com.efun.platform.module.account.bean.ResultBean;
import com.efun.platform.module.account.bean.User;
import com.efun.platform.module.base.impl.OnEfunItemClickListener;
import com.efun.platform.module.base.impl.OnRequestListener;
import com.efun.platform.module.utils.AppUtils;
import com.efun.platform.module.utils.IntentUtils;
import com.efun.platform.module.utils.UrlUtils;
import com.efun.platform.module.utils.UserUtils;
import com.efun.platform.module.utils.ViewUtils;
import com.efun.platform.status.IPlatStatus;
import com.efun.platform.utils.Const.HttpParam;
import com.efun.platform.utils.Const.IPlatDatabaseValues;
import com.efun.platform.utils.Const.Tab;
import com.efun.platform.utils.Const.Web;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
import com.efunfloat.game.window.FloatingWindowManager;
import com.efunfloat.game.window.bean.FloatItemBean;
import com.efunfloat.game.window.listener.EfunPopItemClickListener;

public class EfunPlatformFloatViewManager {
	private static EfunPlatformFloatViewManager egPL = null;
	private String gameCode;
	private Context mContext;
	private IPlatController mController;
	private long oldGameOnlineTime;
	private boolean isStop = false;

	public EfunPlatformFloatViewManager() {
	}

	public static EfunPlatformFloatViewManager getInstance() {
		if (egPL != null)
			return egPL;
		egPL = new EfunPlatformFloatViewManager();
		return egPL;
	}

	/**
	 * 创建悬浮按钮
	 * 
	 * @param context
	 *            上下文对象
	 * @param bean
	 *            EfunPlatformParamsBean 对象
	 */
	public void efunCreateFloatView(Context context,
			final EfunPlatformParamsBean bean) {
		mContext = context;
		// 开启日志
		Log.d("efun", "=====floatViewCreate()=====");
		EfunLogUtil.enableInfo(true);
		EfunLogUtil.enableDebug(true);
		Log.i("efun",
				"params:ui:" + bean.getUid() + "/un:" + bean.getUname()
						+ "/sc:" + bean.getServerCode() + "/ri:"
						+ bean.getRoleId() + "/rn:" + bean.getEfunRole()
						+ "/rl:" + bean.getRoleLevel() + "/sign:"
						+ bean.getSign() + "/timeStamp:" + bean.getTimestamp()
						+ "/rm:" + bean.getRemark() + "/ci:"
						+ bean.getCreditId());

		// 日志，检查参数是否传完整
		bean.checkParams();

		if (EfunStringUtil.isEmpty(gameCode)) {
			gameCode = EfunResConfiguration.getGameCode(mContext);
		}
		// 設置應用的上下文

		// 避免重复调用
		try {
			if (!EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil
					.getInstanse().getUid())) {
				if (GameToPlatformParamsSaveUtil.getInstanse().getUid()
						.equals(bean.getUid())
						&& GameToPlatformParamsSaveUtil.getInstanse()
								.getServerCode().equals(bean.getServerCode())
						&& GameToPlatformParamsSaveUtil.getInstanse()
								.getRoleId().equals(bean.getRoleId())) {
					// 保存相应的参数
					GameToPlatformParamsSaveUtil.getInstanse().setUid(
							bean.getUid());
					GameToPlatformParamsSaveUtil.getInstanse().setUserName(
							bean.getUname());
					GameToPlatformParamsSaveUtil.getInstanse().setGameCode(
							gameCode);
					GameToPlatformParamsSaveUtil.getInstanse().setServerCode(
							bean.getServerCode());
					GameToPlatformParamsSaveUtil.getInstanse().setRoleId(
							bean.getRoleId());
					GameToPlatformParamsSaveUtil.getInstanse().setEfunRole(
							bean.getEfunRole());
					GameToPlatformParamsSaveUtil.getInstanse().setRoleLevel(
							bean.getRoleLevel());
					GameToPlatformParamsSaveUtil.getInstanse().setSign(
							bean.getSign());
					GameToPlatformParamsSaveUtil.getInstanse().setTimestamp(
							bean.getTimestamp());
					GameToPlatformParamsSaveUtil.getInstanse().setRemark(
							bean.getRemark());
					GameToPlatformParamsSaveUtil.getInstanse().setCreditId(
							bean.getCreditId());
					GameToPlatformParamsSaveUtil.getInstanse().setLoginType(
							bean.getLoginType());
					return;
				} else {
					GameToPlatformParamsSaveUtil.getInstanse().setUid(
							bean.getUid());
					GameToPlatformParamsSaveUtil.getInstanse().setUserName(
							bean.getUname());
				}
			} else {
				GameToPlatformParamsSaveUtil.getInstanse()
						.setUid(bean.getUid());
				GameToPlatformParamsSaveUtil.getInstanse().setUserName(
						bean.getUname());
			}
		} catch (Exception e) {
			GameToPlatformParamsSaveUtil.getInstanse().setUid(bean.getUid());
			GameToPlatformParamsSaveUtil.getInstanse().setUserName(
					bean.getUname());
		}
		FloatingWindowManager.getInstance().windowManagerFinish();
		AndroidScape.setContext(mContext);
		UrlUtils.initUrl(mContext);
		initImageLoader();

		// 统计用户在线时长
		efunStartStatictics(mContext);
		String oldUid = EfunDatabase.getSimpleString(mContext,
				IPlatDatabaseValues.PLATFORM_FILE, IPlatDatabaseValues.UID);
		if (EfunStringUtil.isEmpty(oldUid)) {
			oldUid = bean.getUid();
		}
		EfunLogUtil.logI("efun", "oldUid:" + oldUid);
		// 保存UID
		EfunDatabase.saveSimpleInfo(mContext,
				IPlatDatabaseValues.PLATFORM_FILE, IPlatDatabaseValues.UID,
				bean.getUid());
		// 悬浮按钮开关,储值按钮开关相关参数
		final String plateFormOnline = oldUid + "_" + oldGameOnlineTime + "_"
				+ "0" + "-" + bean.getUid();
		EfunLogUtil.logI("efun", "plateFormOnline:" + plateFormOnline);

		// 保存相应的参数
		GameToPlatformParamsSaveUtil.getInstanse().setPlateFormOnline(
				plateFormOnline);
		GameToPlatformParamsSaveUtil.getInstanse().setGameCode(gameCode);
		GameToPlatformParamsSaveUtil.getInstanse().setServerCode(
				bean.getServerCode());
		GameToPlatformParamsSaveUtil.getInstanse().setRoleId(bean.getRoleId());
		GameToPlatformParamsSaveUtil.getInstanse().setEfunRole(
				bean.getEfunRole());
		GameToPlatformParamsSaveUtil.getInstanse().setRoleLevel(
				bean.getRoleLevel());
		GameToPlatformParamsSaveUtil.getInstanse().setSign(bean.getSign());
		GameToPlatformParamsSaveUtil.getInstanse().setTimestamp(
				bean.getTimestamp());
		GameToPlatformParamsSaveUtil.getInstanse().setRemark(bean.getRemark());
		GameToPlatformParamsSaveUtil.getInstanse().setCreditId(
				bean.getCreditId());
		GameToPlatformParamsSaveUtil.getInstanse().setLoginType(
				bean.getLoginType());
		// 请求悬浮按钮
		floatBtnRequest();

	}

	private void openFloatButton(ArrayList<FloatItemBean> buttons) {
		// UserUtils.initUser(mContext);
		// Intent intent = new Intent(mContext, FrameTabContainer.class);
		// mContext.startActivity(intent);

		FloatingWindowManager.getInstance().setFloatItems(buttons);
		for (FloatItemBean bean : buttons) {
			if (bean.getItemType().equals("platformStore")) {
				GameToPlatformParamsSaveUtil.getInstanse().setRechargeBtnFlag(
						true);
			}
		}

		FloatingWindowManager.getInstance().initFloatingView(
				(Activity) mContext, 0, 0, new EfunPopItemClickListener() {

					@Override
					public void itemClicked(FloatItemBean bean) {
						// TODO Auto-generated method stub
						Log.i("efun", "itemName:" + bean.getItemName()
								+ "===itemType:" + bean.getItemType());
						if (bean.getItemType().equals("cs")) {//客服
							GameToPlatformParamsSaveUtil.getInstanse()
									.setTabType(Tab.TAB_ITEM_TAG_CUSTOMSERVICE);
						} else if (bean.getItemType().equals("platformStore")) {//储值
						} else if (bean.getItemType().equals("goodies")) {//好康
							GameToPlatformParamsSaveUtil.getInstanse()
									.setTabType(Tab.TAB_ITEM_TAG_HAOKANG);
						} else if (bean.getItemType().equals("fhide")) {//隐藏
							if (mContext != null) {
								dialog();
							}
							return;
						} else if (bean.getItemType().equals("person")) {//我
							GameToPlatformParamsSaveUtil.getInstanse()
									.setTabType(Tab.TAB_ITEM_TAG_PLAYERSELF);
						}
						request(bean.getItemType());
					}
				});
		if(isStop){
			FloatingWindowManager.getInstance().windowManagerStop();
		}
	}

	private void request(final String ItemType) {
		mController = new IPlatController(mContext);
		mController.executeAll(null, createIPlatRequests(needRequestBean()),
				new OnRequestListener() {
					@Override
					public void onSuccess(int requestType,
							BaseResponseBean baseResponse) {
						if (requestType == IPlatformRequest.REQ_ACCOUNT_LOGIN) {
							AccountResponse response = (AccountResponse) baseResponse;
							ResultBean result = response.getResultBean();
							EfunLogUtil.logD("efun", "userRequestMessage:"+result.getMessage());
							GameToPlatformParamsSaveUtil.getInstanse().setUser(
									(User) result);

							if (ItemType.equals("platformStore")) {
								IntentUtils.go2ReChargeWeb(mContext,
										Web.WEB_GO_FLOAT_RECHAR);
							} else {
								Intent intent = new Intent(mContext,
										FrameTabContainer.class);
								mContext.startActivity(intent);
							}

						}
					}

					@Override
					public void onFailure(int requestType) {
						EfunLogUtil.logE("请求会员信息失败：requestType==>"+requestType);
					}

					@Override
					public void onTimeout(int requestType) {
						EfunLogUtil.logE("请求会员信息超时：requestType==>"+requestType);
					}

					@Override
					public void onNoData(int requestType, String noDataNotify) {
						// TODO Auto-generated method stub
						EfunLogUtil.logE("请求会员信息没数据：requestType==>"+requestType+"/noDataNotify:"+noDataNotify);
					}
				});
	}

	private void floatBtnRequest() {
		mController = new IPlatController(mContext);
		mController.executeAll(null,
				createIPlatRequests(createFloatBtnRequestBean()),
				new OnRequestListener() {
					@Override
					public void onSuccess(int requestType,
							BaseResponseBean baseResponse) {
						if (requestType == IPlatformRequest.REQ_FLOAT_BTN) {
							FloatingButtonResponse response = (FloatingButtonResponse) baseResponse;
							Log.d("efun", "message:"+response.getFloatingButtonBean().getMessage());
							if (response.getFloatingButtonBean().getCode()
									.equals("1")) {
								openFloatButton(response
										.getFloatingButtonBean().getButtons());
							} else {

							}

						}
					}

					@Override
					public void onFailure(int requestType) {
						Log.e("efun", "悬浮按钮请求失败");
					}

					@Override
					public void onTimeout(int requestType) {
						Log.e("efun", "悬浮按钮请求超时");
					}

					@Override
					public void onNoData(int requestType, String noDataNotify) {
						// TODO Auto-generated method stub
						Log.e("efun", "悬浮按钮请求没数据");
					}
				});
	}

	private IPlatRequest[] createIPlatRequests(BaseRequestBean[] requests) {
		IPlatRequest[] mRequests = new IPlatRequest[requests.length];
		for (int i = 0; i < mRequests.length; i++) {
			mRequests[i] = new IPlatRequest(mContext)
					.setRequestBean(requests[i]);
		}
		return mRequests;
	}

	private BaseRequestBean[] needRequestBean() {
		// if(UserUtils.isLogin()){
		AccountLoginRequest loginRequest = UserUtils.createRequest(mContext);
		return new BaseRequestBean[] { loginRequest };
		// }
		// return new BaseRequestBean[]{checkRequest};
	}

	private BaseRequestBean[] createFloatBtnRequestBean() {
		// if(UserUtils.isLogin()){
		FloatingButtonRequest floatBtnRequest = UserUtils
				.createFloatBtnRequest(mContext);
		return new BaseRequestBean[] { floatBtnRequest };
		// }
		// return new BaseRequestBean[]{checkRequest};
	}

	/**
	 * 销毁悬浮按钮
	 * 
	 * @param context
	 */
	public void efunDestoryFloatView(Context context) {
		Log.d("efun", "=====floatViewDestory()=====");
		efunStopStatistics(context);
		GameToPlatformParamsSaveUtil.getInstanse().setUid("");
		GameToPlatformParamsSaveUtil.getInstanse().setUserName("");
		FloatingWindowManager.getInstance().windowManagerFinish();
	}

	public void efunPauseAndStopFloatView(Context context) {
		isStop = true;
		Log.d("efun", "=====floatViewPauseAndStop()=====");
		efunStopStatistics(context);
		FloatingWindowManager.getInstance().windowManagerStop();
	}

	public void efunResumeFloatView(Context context) {
		isStop = false;
		Log.d("efun", "=====floatViewResume()=====");
		efunContinueStatistics(context);
		FloatingWindowManager.getInstance().windowManagerRestart(context);
	}

	/**
	 * 设置游戏gameCode
	 * 
	 * @param gameCode
	 */
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	/**
	 * 初始化图片加载器
	 */
	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				mContext).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 开始统计用户在线时长
	 * 
	 * @param context
	 */
	private void efunStartStatictics(Context context) {
		EfunLogUtil.logI("efun",
				"=============efunStartStatictics(Context context)======");
		long totalTime = EfunDatabase.getSimpleLong(context,
				IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_TOTAL_TIME);
		oldGameOnlineTime = totalTime / 60000;
		EfunLogUtil.logI("efun", "上次登录统计的时间：totalTime===>" + totalTime / 60000
				+ "分");
		// 将上次登录统计的时间上传给服务器
		// 获取前一次的uid
		String oldUid = EfunDatabase.getSimpleString(context,
				IPlatDatabaseValues.PLATFORM_FILE, IPlatDatabaseValues.UID);
		// 保存当前的登录时间
		EfunDatabase
				.saveSimpleInfo(context, IPlatDatabaseValues.PLATFORM_FILE,
						IPlatDatabaseValues.DATA_FIRST_TIME,
						System.currentTimeMillis());
		// 初始化，开始新的一次统计
		EfunDatabase.saveSimpleInfo(context, IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_TOTAL_TIME, 0L);
	}

	/**
	 * 中途重新启动继续统计用户在线时长
	 * 
	 * @param context
	 */
	private void efunContinueStatistics(Context context) {
		EfunLogUtil.logI("efun",
				"=======efunRestartStatistics(Context context)============");
		long totalTime = EfunDatabase.getSimpleLong(context,
				IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_TOTAL_TIME);
		EfunLogUtil.logI("efun",
				"=efunRestartStatistics(Context context)=====重新开始记录时的当前已有的统计时间：totalTime===>"
						+ totalTime);
		EfunDatabase
				.saveSimpleInfo(context, IPlatDatabaseValues.PLATFORM_FILE,
						IPlatDatabaseValues.DATA_FIRST_TIME,
						System.currentTimeMillis());
		EfunLogUtil.logI("efun",
				"=efunRestartStatistics(Context context)=====重新开始记录的时间：firstTime===>"
						+ System.currentTimeMillis());
	}

	/**
	 * 暂停统计用户在线时长
	 * 
	 * @param context
	 */
	private void efunStopStatistics(Context context) {
		EfunLogUtil.logI("efun",
				"=============efunStopStatistics(Context context)======");
		long totalTime = EfunDatabase.getSimpleLong(context,
				IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_TOTAL_TIME);
		EfunLogUtil.logI("efun",
				"=efunStopStatistics()=====暂停时，停止记录时间之前的：totalTime===>"
						+ totalTime);
		long firstTime = EfunDatabase.getSimpleLong(context,
				IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_FIRST_TIME);
		if (firstTime == 0) {
			firstTime = System.currentTimeMillis();
		}
		EfunLogUtil.logI("efun",
				"=efunStopStatistics()=====暂停时，获得此前保存的开始统计时间：firstTime===>"
						+ firstTime);
		EfunDatabase.saveSimpleInfo(context, IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_TOTAL_TIME,
				totalTime + (System.currentTimeMillis() - firstTime));
		EfunDatabase
				.saveSimpleInfo(context, IPlatDatabaseValues.PLATFORM_FILE,
						IPlatDatabaseValues.DATA_FIRST_TIME,
						System.currentTimeMillis());
		long totalTimel = EfunDatabase.getSimpleLong(context,
				IPlatDatabaseValues.PLATFORM_FILE,
				IPlatDatabaseValues.DATA_TOTAL_TIME);
		EfunLogUtil.logI("efun",
				"=efunStopStatistics()=====暂停时，停止记录的时间：totalTimel===>"
						+ totalTimel);
	}

	private void dialog() {
		// 新建AlertDialog对话框
		// 弹出新的对话框
		new AlertDialog.Builder(mContext)
				.setCancelable(true)
				.setMessage("隱藏後重新登入遊戲你就能看得見我喔")
				.setNegativeButton("取消", new DialogInterface.OnClickListener() // 取消操作
						{
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						})
				.setPositiveButton("確認", new DialogInterface.OnClickListener() // 确认操作
						{
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								FloatingWindowManager.getInstance()
										.windowManagerFinish();
							}
						}).show();
	}

}
