package com.efun.platform.http.dao.impl;

import java.util.List;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;

import com.efun.core.http.EfunHttpUtil;
import com.efun.platform.AndroidScape.E_string;
import com.efun.platform.http.request.bean.AccountBindPhoneRequest;
import com.efun.platform.http.request.bean.AccountBindPhoneSendVcodeRequest;
import com.efun.platform.http.request.bean.AccountFindPwdRequest;
import com.efun.platform.http.request.bean.AccountGetUserFBUidsByUidRequest;
import com.efun.platform.http.request.bean.AccountLoginRequest;
import com.efun.platform.http.request.bean.AccountRegisterRequest;
import com.efun.platform.http.request.bean.AccountResetPwdRequest;
import com.efun.platform.http.request.bean.AccountUpdateRequest;
import com.efun.platform.http.request.bean.ActivityExtensionDownloadRequest;
import com.efun.platform.http.request.bean.ActivityExtensionGiftRequest;
import com.efun.platform.http.request.bean.ActivityExtensionRequest;
import com.efun.platform.http.request.bean.ActivityListRequest;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.request.bean.CsAskRequest;
import com.efun.platform.http.request.bean.CsGainGameListRequest;
import com.efun.platform.http.request.bean.CsGainRoleListRequest;
import com.efun.platform.http.request.bean.CsGainServerListRequest;
import com.efun.platform.http.request.bean.CsQuestionListRequest;
import com.efun.platform.http.request.bean.CsReplyCommitQuestionRequest;
import com.efun.platform.http.request.bean.CsReplyDetailsRequest;
import com.efun.platform.http.request.bean.CsReplyFinishQuestionRequest;
import com.efun.platform.http.request.bean.CsReplyQuestionListRequest;
import com.efun.platform.http.request.bean.CsReplyStatusRequest;
import com.efun.platform.http.request.bean.FloatingButtonRequest;
import com.efun.platform.http.request.bean.GameCommendListRequest;
import com.efun.platform.http.request.bean.GameCommendRequest;
import com.efun.platform.http.request.bean.GameDetailRequest;
import com.efun.platform.http.request.bean.GameListRequest;
import com.efun.platform.http.request.bean.GameNewsRequest;
import com.efun.platform.http.request.bean.GamePraiseRequest;
import com.efun.platform.http.request.bean.GiftKnockRequest;
import com.efun.platform.http.request.bean.GiftListRequest;
import com.efun.platform.http.request.bean.GiftSelfListRequest;
import com.efun.platform.http.request.bean.GiftSelfStatusRequest;
import com.efun.platform.http.request.bean.SettingCheckRequest;
import com.efun.platform.http.request.bean.SummaryHomeRequest;
import com.efun.platform.http.request.bean.SummaryListRequest;
import com.efun.platform.http.request.bean.UserUpdateHeaderRequest;
import com.efun.platform.http.request.bean.UserUpdateInfoRequest;
import com.efun.platform.http.response.bean.AccountBindPhoneResponse;
import com.efun.platform.http.response.bean.AccountGetUserFBUidsByUidResponse;
import com.efun.platform.http.response.bean.AccountResponse;
import com.efun.platform.http.response.bean.AccountUpdateResponse;
import com.efun.platform.http.response.bean.ActivityExtensionDownloadResponse;
import com.efun.platform.http.response.bean.ActivityExtensionGiftResponse;
import com.efun.platform.http.response.bean.ActivityExtensionResponse;
import com.efun.platform.http.response.bean.ActivityListResponse;
import com.efun.platform.http.response.bean.BaseResponseBean;
import com.efun.platform.http.response.bean.CsAskResponse;
import com.efun.platform.http.response.bean.CsGainGameListResponse;
import com.efun.platform.http.response.bean.CsGainRoleListResponse;
import com.efun.platform.http.response.bean.CsGainServersListResponse;
import com.efun.platform.http.response.bean.CsQuestionListResponse;
import com.efun.platform.http.response.bean.CsReplyCommitQuestionResponse;
import com.efun.platform.http.response.bean.CsReplyDetailsResponse;
import com.efun.platform.http.response.bean.CsReplyFinishQuestionResponse;
import com.efun.platform.http.response.bean.CsReplyQuestionListResponse;
import com.efun.platform.http.response.bean.CsReplyStatusResponse;
import com.efun.platform.http.response.bean.FloatingButtonResponse;
import com.efun.platform.http.response.bean.GameCommendListResponse;
import com.efun.platform.http.response.bean.GameCommendResponse;
import com.efun.platform.http.response.bean.GameDetailResponse;
import com.efun.platform.http.response.bean.GameListResponse;
import com.efun.platform.http.response.bean.GameNewsResponse;
import com.efun.platform.http.response.bean.GamePraiseResponse;
import com.efun.platform.http.response.bean.GiftKnockResponse;
import com.efun.platform.http.response.bean.GiftListResponse;
import com.efun.platform.http.response.bean.GiftSelfListResponse;
import com.efun.platform.http.response.bean.GiftSelfStatusResponse;
import com.efun.platform.http.response.bean.SettingCheckResponse;
import com.efun.platform.http.response.bean.SummaryHomeResponse;
import com.efun.platform.http.response.bean.SummaryListResponse;
import com.efun.platform.http.response.bean.UserUpdateHeaderResponse;
import com.efun.platform.http.response.bean.UserUpdateInfoResponse;
import com.efun.platform.module.utils.UrlUtils;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
/**
 * {@link IPlatformRequest} 实现类
 * @author Jesse
 *
 */
public class IPlatformImpl implements IPlatformRequest {
	/**
	 * URL - 平台域名
	 */
	private String mUrl;
	/**
	 * URL - 游戏域名
	 */
	private String mGameUrl;
	/**
	 * URL - fb域名
	 */
	private String mFbUrl;
	/**
	 * URL - 查询用户信息域名
	 */
	private String mLoginUrl;
	/**
	 * Method - 接口方法名称
	 */
	private String mMethod;
	/**
	 * {@link Context} 上下文
	 */
	private Context mContext;

	public IPlatformImpl(Context context) {
		mUrl = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.PLATFORM_PRE_KEY,context);
		mGameUrl = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.GAME_PRE_KEY,context);
		mFbUrl = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.FB_PRE_KEY,context);
		mLoginUrl = GameToPlatformParamsSaveUtil.getInstanse().getIPlatUrlByKey(UrlUtils.LOGIN_PRE_KEY,context);
		mUrl = checkUrl(mUrl);
		mContext = context;
	}
	/**
	 * 校验链接是否标准
	 * @param url 
	 * @return 
	 */
	private String checkUrl(String url) {
		if (!url.endsWith("/")) {
			url = url + "/";
		}
		return url;
	}
	/**
	 * 校验Method是否标准
	 * @param method
	 */
	private void checkMethod(String method) {
		if (!method.endsWith(".shtml")) {
			throw new IllegalAccessError("Method Illeagel");
		}
	}

	@Override
	public BaseResponseBean summaryHome(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_summary_home);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(SummaryHomeRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		SummaryHomeResponse response = new SummaryHomeResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean summaryList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_summary_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(SummaryListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		SummaryListResponse response = new SummaryListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean gameList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_game_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GameListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GameListResponse response = new GameListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean gamePraise(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_game_like);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GamePraiseRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GamePraiseResponse response = new GamePraiseResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean gameDetail(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_game_detail);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GameDetailRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GameDetailResponse response = new GameDetailResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean gameNewsList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_game_news);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GameNewsRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GameNewsResponse response = new GameNewsResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, true);
		return response;
	}

	@Override
	public BaseResponseBean gameCommendList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_game_commend_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GameCommendListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GameCommendListResponse response = new GameCommendListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, true);
		return response;
	}

	@Override
	public BaseResponseBean gameCommend(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_game_commend);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GameCommendRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GameCommendResponse response = new GameCommendResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean login(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_login);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountLoginRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountResponse response = new AccountResponse();
		response.setContext(mContext);
		response.setRequestBean(request);
		response.pares(request, responseStr, false);
		return response;
	}
	@Override
	public BaseResponseBean update(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_login);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountUpdateRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountUpdateResponse response = new AccountUpdateResponse();
		response.setContext(mContext);
		response.setRequestBean(request);
		response.pares(request, responseStr, false);
		return response;
	}
	@Override
	public BaseResponseBean register(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_register);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountRegisterRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountResponse response = new AccountResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean forgetPwd(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_forget_pwd);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountFindPwdRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountResponse response = new AccountResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean resetPwd(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_reset_pwd);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountResetPwdRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountResponse response = new AccountResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean userUpdateHeader(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_user_header);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(UserUpdateHeaderRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		UserUpdateHeaderResponse response = new UserUpdateHeaderResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean userUpdateInfo(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_user_update);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(UserUpdateInfoRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		UserUpdateInfoResponse response = new UserUpdateInfoResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean checkVersion(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_check_version);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(SettingCheckRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		SettingCheckResponse response = new SettingCheckResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean actsList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_act_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(ActivityListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		ActivityListResponse response = new ActivityListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean actExtension(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_act_extension);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(ActivityExtensionRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl+ mMethod, params);
		ActivityExtensionResponse response = new ActivityExtensionResponse();
		response.setContext(mContext);
		response.pares(request, responseStr,false);
		return response;
	}
	
	@Override
	public BaseResponseBean actExtensionGift(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_act_extension_gift);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(ActivityExtensionGiftRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl+ mMethod, params);
		ActivityExtensionGiftResponse response = new ActivityExtensionGiftResponse();
		response.setContext(mContext);
		response.pares(request, responseStr,false);
		return response;
	}
	@Override
	public BaseResponseBean actExtensionDownload(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_act_extension_download);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(ActivityExtensionDownloadRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mFbUrl+ mMethod, params);
		ActivityExtensionDownloadResponse response = new ActivityExtensionDownloadResponse();
		response.setContext(mContext);
		response.pares(request, responseStr,false);
		return response;
	}
	@Override
	public BaseResponseBean giftsList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_gift_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GiftListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GiftListResponse response = new GiftListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean giftKnock(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_gift_knock);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GiftKnockRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GiftKnockResponse response = new GiftKnockResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean giftSelfList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_gift_self_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GiftSelfListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GiftSelfListResponse response = new GiftSelfListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}
	
	@Override
	public BaseResponseBean giftSelfStatus(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_gift_self_status);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(GiftSelfStatusRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		GiftSelfStatusResponse response = new GiftSelfStatusResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csAskGainGames(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_gain_games);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsGainGameListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mGameUrl + mMethod, params);
		CsGainGameListResponse response = new CsGainGameListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csAskGainServers(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_gain_servers);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsGainServerListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mGameUrl + mMethod, params);
		CsGainServersListResponse response = new CsGainServersListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csAskGainRole(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_gain_role);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsGainRoleListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mGameUrl + mMethod, params);
		CsGainRoleListResponse response = new CsGainRoleListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csAsk(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_ask);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsAskRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsAskResponse response = new CsAskResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csReplayQuestionList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_replay_question_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsReplyQuestionListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsReplyQuestionListResponse response = new CsReplyQuestionListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csCommitQuestion(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_replay_question);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsReplyCommitQuestionRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsReplyCommitQuestionResponse response = new CsReplyCommitQuestionResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csFinishQuestion(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_replay_finish_question);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsReplyFinishQuestionRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsReplyFinishQuestionResponse response = new CsReplyFinishQuestionResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csQuestionList(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_question_list);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsQuestionListRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsQuestionListResponse response = new CsQuestionListResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean bindPhoneToSendVCode(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_bind_phone_send_vcode);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountBindPhoneSendVcodeRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountBindPhoneResponse response = new AccountBindPhoneResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean bindPhone(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_bind_phone);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountBindPhoneRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		AccountBindPhoneResponse response = new AccountBindPhoneResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}

	@Override
	public BaseResponseBean csReplyDetails(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_replay_details);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsReplyDetailsRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsReplyDetailsResponse response = new CsReplyDetailsResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}
	@Override
	public BaseResponseBean csReplyStatus(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_replay_status);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(CsReplyStatusRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mUrl + mMethod, params);
		CsReplyStatusResponse response = new CsReplyStatusResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}
	
	@Override
	public BaseResponseBean accountGetUserFBUidsByUid(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_cs_get_fbuserInfos);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(AccountGetUserFBUidsByUidRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mLoginUrl + mMethod, params);
//		String responseStr = EfunHttpUtil.efunExecutePostRequest("http://lgtest2.efuneu.com/pcLogin_loadUserGameRelation.shtml", params);
		AccountGetUserFBUidsByUidResponse response = new AccountGetUserFBUidsByUidResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}
	
	@Override
	public BaseResponseBean floatBtn(BaseRequestBean request) {
		mMethod = mContext.getString(E_string.efun_pd_method_float_btn_url);
		checkMethod(mMethod);
		List<NameValuePair> params = request.buildPostParams(FloatingButtonRequest.class);
		String responseStr = EfunHttpUtil.efunExecutePostRequest(mGameUrl + mMethod, params);
		FloatingButtonResponse response = new FloatingButtonResponse();
		response.setContext(mContext);
		response.pares(request, responseStr, false);
		return response;
	}


}
