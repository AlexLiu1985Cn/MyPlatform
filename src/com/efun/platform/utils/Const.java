package com.efun.platform.utils;

import android.support.v4.app.FragmentTabHost;

/**
 * 公共常量类
 * 
 * @author Jesse
 * 
 */
public class Const {
	public final static String VER_NAME = "V2";
	public final static int VER_CODE = 2;
	public final static String TAB_ACTION = "com.efun.pd.v2.tab";
	public final static long BETWEENTIME = 24*60*60*1000;

	/**
	 * {@link FragmentTabHost} 中 Tab 的Tag
	 * 
	 * @author Jesse
	 */
	public static class Tab {
		public final static int TAB_ITEM_TAG_SUMMARY = 0;
		public final static int TAB_ITEM_TAG_GAMES = 1;
		public final static int TAB_ITEM_TAG_HAOKANG = 2;
		public final static int TAB_ITEM_TAG_CUSTOMSERVICE = 3;
		public final static int TAB_ITEM_TAG_PLAYERSELF = 4;
	}

	/**
	 * 启动Web的方式
	 */
	public static class Web {
		public final static String WEB_PERFIX = "http://efuntw.com/page/";
		/**
		 * Fragment 启动 FragmentActivity 方式的key值
		 */
		public final static String WEB_GO_KEY = "WEB_GO_KEY";
		/**
		 * 
		 */
		public final static String WEB_LOAD_URL_KEY = "WEB_LOAD_URL_KEY";

		/**
		 * 来自资讯
		 */
		public final static int WEB_GO_SUMMARY = 1;
		/**
		 * 来自Banner广告
		 */
		public final static int WEB_GO_BANNER = 2;
		/**
		 * 来自活动
		 */
		public final static int WEB_GO_ACT = 3;
		/**
		 * 来自游戏
		 */
		public final static int WEB_GO_GAME = 4;
		/**
		 * 来自砸蛋
		 */
		public final static int WEB_GO_EGG = 5;
		/**
		 * 来自积金说明
		 */
		public final static int WEB_GO_GOLDVALUE = 6;
		/**
		 * 来自关于我们
		 */
		public final static int WEB_GO_US = 7;
		/**
		 * 来自会员VIP
		 */
		public final static int WEB_GO_VIP = 8;
		/**
		 * 来自悬浮按钮储值
		 */
		public final static int WEB_GO_FLOAT_RECHAR = 9;
		/**
		 * 来自个人信息的储值
		 */
		public final static int WEB_GO_PERSON_RECHAR = 10;
	}

	/**
	 * 键值
	 * 
	 * @author Jesse
	 * 
	 */
	public final class Key {
		/**
		 * Bundle存放简单数据
		 */
		public final static String BOOLEAN_KEY = "BOOLEAN_KEY";
		/**
		 * Bundle存放简单数据
		 */
		public final static String INTEGER_KEY = "INTEGER_KEY";
		/**
		 * Bundle存放简单数据
		 */
		public final static String STRING_KEY = "STRING_KEY";
		/**
		 * Facebook bundle list
		 */
		public final static String APPS_KEY = "APPS_KEY";
		/**
		 * 切换账号
		 */
		public final static String USER_KEY = "USER_KEY";
	}

	/**
	 * Fragment 与 FragmentActivity 传值Bundle的key值
	 */
	public final static String DATA_KEY = "DATA_KEY";
	/**
	 * Bundle存放Bean的key值
	 */
	public final static String BEAN_KEY = "BEAN_KEY";

	public static class HttpParam {
		public final static String LANGUAGE = "zh_TW";
		public final static String REGION = "";
		public final static String PLATFORM = "android";

		public final static String RESULT_200 = "200";
		public final static String RESULT_1000 = "1000";
		public final static String RESULT_1010 = "1010";
		public final static String RESULT_1013 = "1013";
		public final static String RESULT_1003 = "1003";
		public final static String RESULT_1006 = "1006";
		public final static String RESULT_1002 = "1002";
		public final static String RESULT_1100 = "1100";

		public final static String RESULT_1028 = "1028";
		public final static String RESULT_1029 = "1029";
		public final static String RESULT_1030 = "1030";
		public final static String RESULT_1031 = "1031";

		public final static String GIFT_STATUS_QUERY = "q";
		public final static String GIFT_STATUS_UPDATE = "u";
		
		public final static String PLATFORM_AREA = "tw";
		public final static String JSONP = "1";
		public final static String APP = "app";
		public final static String PLATFORM_CODE = "twap";
		public final static String PHONE = "phone";
		public final static String PLATFORM_APP_KEY = "90DDA231C4FA0662A35506A27ADD8AFD";//appKey 秘钥
		public final static String PLATFORM_APP_PLATFORM = "e00000";//平台标识
		public final static String PLATFORM_APP_SERVERCODE = "0";
		public final static String PLATFORM_APP_ROLEID = "0";
		public final static String ANDORIOS = "andorios";
		public final static String PLATFORMBUTTON = "platformButton";
		public final static String ISNEW = "1";

		// 获取游戏接口
		public final static String CS_PID = "1";
		public final static String CS_DEPT_TW = "31";
		public final static String CS_GPID = "1";
		public final static String CS_PLAYER = "player";
		public final static String CS_CHECK = "check";
		
		/**
		 * 成功
		 */
		public static final int SUCCESS = 1;
		/**
		 * 异常
		 */
		public static final int ERROR = -1;
		/**
		 * 超时
		 */
		public static final int TIMEOUNT = 0;
	}

	public static class LoginPlatform {
		public final static String FACEBOOK = "fb";
		public final static String BAHAMUT = "gamer";
		public final static String GOOGLE = "google";
		public final static String EFUN = "efun";
	}

	public static class RequestCode {
		public final static int REQ_CODE_UPDATE_NICK = 2000;
		public final static int REQ_USER_INFO_USER = 2001;
		public final static int REQ_USER_INFO_HEAD_PHONE = 2002;
		public final static int REQ_USER_INFO_HEAD_THUMB = 2003;
		public final static int REQ_USER_INFO_HEAD_PHONE_CUT = 2004;
		public final static int REQ_BIND_PHONE = 2005;
		public final static int REQ_ACCOUNT_FACEBOOK_LOGIN = 2006;
		public final static int REQ_CS_REPLY_QUESTION_REQUEST = 2007;
		public final static int REQ_REQUEST = 2008;
		public final static int REQ_GAME_COMMEND = 2009;
		public final static int REQ_LOGOUT = 2010;
	}

	public static class ResultCode {
		public final static int RST_CODE_UPDATE_NICK = 2000;
		public final static int RST_ACCOUNT_FACEBOOK_LOGIN = 2001;
		public final static int RST_BIND_PHONE = 2002;
		public final static int RST_CS_REPLY_QUESTION_RESULT = 2003;
		public final static int RST_CS_REPLY_FINISH = 2004;
		public final static int RST_CS_REPLY_READ = 2005;
		public final static int RST_REQUEST = 2006;
		public final static int RST_GAME_COMMEND = 2010;
		public final static int RST_LOGOUT = 2011;
	}
	
	public static class BeanType{
		
		public final static int BEAN_SETTINGBEAN = 9000;//SettingBean
		public final static int BEAN_GAMENEWSBEAN = 9001;//GameNewsBean
		public final static int BEAN_SUMMARYITEMBEAN = 9019;//SummaryItemBean
		public final static int BEAN_ACTITEMBEAN = 9020;//ActItemBean
		
	}
	
	public static class Share{
		public final static String SHARE_LINESHARE_URL = "http://line.naver.jp/R/msg/text/?";
		public final static String SHARE_APPSHARE_URL = "http://ad.efuntw.com/ads_scanner.shtml?gameCode=twap&advertiser=efunapp&adsid=efunapp_twap";
	}
	
	public static class AppStatus{
		public final static int DOWNLOAD = 1;
		public final static int START = 2;
		public final static int UPDATE = 3;
	}
	
	/**
	 * 悬浮按钮存储文件
	 * @author itxuxxey
	 *
	 */
	public static class IPlatDatabaseValues{
		public static final String PLATFORM_FILE = "platform.db";
		public static final String UID = "UID";
		public static final String UNAME="UNAME";
		public static final String DATA_KEY_HOME = "DATA_HOME";
		public static final String DATA_KEY_PERSION="DATA_PERSION";
		public static final String DATA_TOTAL_TIME = "TOTAL_TIME";
		public static final String DATA_FIRST_TIME = "FIRST_TIME";
	}
}
