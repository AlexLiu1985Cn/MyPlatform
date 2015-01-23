package com.efun.platform.http.dao.impl;

import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.response.bean.BaseResponseBean;

/**
 * 接口
 * 
 * @author Jesse
 * 
 */
public interface IPlatformRequest {
	/**
	 * 获取本地数据
	 */
	public static final int REQ_LOCAL_DATA = -1;

	/**
	 * 悬浮按钮
	 */
	public static final int REQ_FLOAT_BTN = 100;
	/**
	 * 资讯-获取首页信息
	 */
	public static final int REQ_SUMMARY_HOME = 1;

	/**
	 * 资讯-获取资讯列表
	 */
	public static final int REQ_SUMMARY_LIST_ALL = 11;
	public static final int REQ_SUMMARY_LIST_NEWS = 12;
	public static final int REQ_SUMMARY_LIST_BULLETIN = 13;
	public static final int REQ_SUMMARY_LIST_ACTIVITY = 14;
	public static final int REQ_SUMMARY_LIST_STRATEGY = 15;

	/**
	 * 游戏-获取游戏列表
	 */
	public static final int REQ_GAME_LIST = 2;
	/**
	 * 游戏-获取游戏资讯列表
	 */
	public static final int REQ_GAME_NEWS_LIST = 21;
	/**
	 * 游戏-游戏详情
	 */
	public static final int REQ_GAME_DETAIL = 22;
	/**
	 * 游戏-游戏评论列表
	 */
	public static final int REQ_GAME_COMMEND_LIST = 23;
	/**
	 * 游戏-游戏评论
	 */
	public static final int REQ_GAME_COMMEND = 24;
	/**
	 * 游戏-游戏点赞
	 */
	public static final int REQ_GAME_PRAISE = 25;
	/**
	 * 好康-活動列表
	 */
	public static final int REQ_ACT_LIST = 32;
	/**
	 * 好康-禮包中心
	 */
	public static final int REQ_GIFT_LIST = 33;

	/**
	 * 好康-抢礼包
	 */
	public static final int REQ_GIFT_KNOCK = 34;
	/**
	 * 好康-序列号中心
	 */
	public static final int REQ_GIFT_SELF_LIST = 35;
	/**
	 * 好康-领取免费点数
	 */
	public static final int REQ_ACT_EXTENSION = 36;
	/**
	 * 好康-领取奖励
	 */
	public static final int REQ_ACT_EXTENSION_GIFT = 37;
	/**
	 * 好康-下载游戏请求
	 */
	public static final int REQ_ACT_EXTENSION_DOWNLOAD = 38;
	/**
	 * 好康-序列号中心-状态提示
	 */
	public static final int REQ_GIFT_SELF_STATUS = 39;

	/**
	 * 用户-更新用户信息
	 */
	public static final int REQ_USER_UPDATE_INFO = 5;

	/**
	 * 用户-更新用户头像
	 */
	public static final int REQ_USER_UPDATE_HEADER = 51;

	/**
	 * /** 账号-登陆
	 */
	public static final int REQ_ACCOUNT_LOGIN = 8;
	/**
	 * 账号-注册
	 */
	public static final int REQ_ACCOUNT_REGISTER = 81;
	/**
	 * 账号-找回密码
	 */
	public static final int REQ_ACCOUNT_FORGET_PWD = 82;
	/**
	 * 账号-修改密码
	 */
	public static final int REQ_ACCOUNT_RESET_PWD = 83;
	/**
	 * 账号-绑定手机，发送验证码
	 */
	public static final int REQ_ACCOUNT_BIND_PHONE_SEND_VCODE = 84;
	/**
	 * 账号-绑定手机
	 */
	public static final int REQ_ACCOUNT_BIND_PHONE = 85;
	/**
	 * 账号-刷新数据
	 */
	public static final int REQ_ACCOUNT_UPDATE = 86;

	/**
	 * 客服-提交问题
	 */
	public static final int REQ_CS_ASK = 7;
	/**
	 * 客服-提交问题-获取游戏列表
	 */
	public static final int REQ_CS_GAIN_GAMES = 71;
	/**
	 * 客服-提交问题-获取服务器列表
	 */
	public static final int REQ_CS_GAIN_SERVER = 72;
	/**
	 * 客服-提交问题-获取角色列表
	 */
	public static final int REQ_CS_GAIN_ROLE = 73;
	/**
	 * 客服-常见问题
	 */
	public static final int REQ_CS_QUESTION_LIST_POP = 74;
	public static final int REQ_CS_QUESTION_LIST_LOR = 75;
	public static final int REQ_CS_QUESTION_LIST_RCG = 76;

	/**
	 * 客服-客服回复列表
	 */
	public static final int REQ_CS_REPLAY_QUESTION_LIST = 77;

	/**
	 * 客服-玩家回复问题
	 */
	public static final int REQ_CS_REPLAY_COMMIT_QUESTION = 78;

	/**
	 * 客服-玩家完结评分问题
	 */
	public static final int REQ_CS_REPLAY_FINISH_QUESTION = 79;

	/**
	 * 客服-回复列表详情
	 */
	public static final int REQ_CS_REPLY_DETAILS = 791;
	
	/**
	 * 客服-回复狀態
	 */
	public static final int REQ_CS_REPLY_STATUS = 792;
	
	/**
	 * 客服-获取FB账号对应的用户信息
	 */
	public static final int REQ_CS_GET_FB_USER_INFO = 793;

	/**
	 * 设置-检查更新
	 */
	public static final int REQ_SETTING_CHECK_VERSION = 9;

	/**
	 * 资讯首页
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean summaryHome(BaseRequestBean request);

	/**
	 * 资讯列表
	 * 
	 * @return
	 */
	public BaseResponseBean summaryList(BaseRequestBean request);

	/**
	 * 游戏-游戏列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean gameList(BaseRequestBean request);

	/**
	 * 游戏-游戏点赞
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean gamePraise(BaseRequestBean request);

	/**
	 * 游戏-游戏详情
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean gameDetail(BaseRequestBean request);

	/**
	 * 游戏-新闻资讯列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean gameNewsList(BaseRequestBean request);

	/**
	 * 游戏-评论列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean gameCommendList(BaseRequestBean request);

	/**
	 * 游戏评论
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean gameCommend(BaseRequestBean request);

	/**
	 * 登陆
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean login(BaseRequestBean request);
	
	/**
	 * 登陆
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean update(BaseRequestBean request);

	/**
	 * 注册
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean register(BaseRequestBean request);

	/**
	 * 忘记密码
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean forgetPwd(BaseRequestBean request);

	/**
	 * 修改密码
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean resetPwd(BaseRequestBean request);

	/**
	 * 账户-更新用户头像
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean userUpdateHeader(BaseRequestBean request);

	/**
	 * 更新用户信息
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean userUpdateInfo(BaseRequestBean request);

	/**
	 * 客服-提问-获取游戏列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csAskGainGames(BaseRequestBean request);

	/**
	 * 客服-提问-获取服务器列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csAskGainServers(BaseRequestBean request);

	/**
	 * 客服-提问-获取角色列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csAskGainRole(BaseRequestBean request);

	/**
	 * 客服-提交问题
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csAsk(BaseRequestBean request);

	/**
	 * 客服-客服获取客服回复的历史记录
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csReplayQuestionList(BaseRequestBean request);

	/**
	 * 客服-玩家提问
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csCommitQuestion(BaseRequestBean request);

	/**
	 * 客服-评分问题
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csFinishQuestion(BaseRequestBean request);

	/**
	 * 客服-问题详情
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csReplyDetails(BaseRequestBean request);

	/**
	 * 客服-常见问题
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csQuestionList(BaseRequestBean request);
	
	/**
	 * 客服-回復狀態
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean csReplyStatus(BaseRequestBean request);
	/**
	 * 客服-获取FB用户信息列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean accountGetUserFBUidsByUid(BaseRequestBean request);

	/**
	 * 系统-检测版本
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean checkVersion(BaseRequestBean request);

	/**
	 * 好康-活动信息列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean actsList(BaseRequestBean request);

	/**
	 * 好康-领取免费点数
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean actExtension(BaseRequestBean request);
	/**
	 * 好康-领取奖励
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean actExtensionGift(BaseRequestBean request);
	/**
	 * 好康-下载游戏许可
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean actExtensionDownload(BaseRequestBean request);
	/**
	 * 好康-礼包中心列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean giftsList(BaseRequestBean request);

	/**
	 * 好康-抢礼包
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean giftKnock(BaseRequestBean request);

	/**
	 * 好康-序列号列表
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean giftSelfList(BaseRequestBean request);
	
	/**
	 * 好康-我的序列号-状态查询
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean giftSelfStatus(BaseRequestBean request);
	

	/**
	 * 账号-绑定手机，发送验证码
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean bindPhoneToSendVCode(BaseRequestBean request);

	/**
	 * 账号-绑定手机
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean bindPhone(BaseRequestBean request);
	
	/**
	 * 悬浮按钮
	 * @param request {@link BaseRequestBean}
	 * @return @return {@link BaseResponseBean} 
	 */
	public BaseResponseBean floatBtn(BaseRequestBean request);

}
