package com.efun.platform.utils;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.efun.core.db.EfunDatabase;
import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.http.request.bean.BaseRequestBean;
import com.efun.platform.http.response.bean.BaseResponseBean;

public class Store {
	/**
	 * 获取键对应的内容值
	 * 
	 * @param context
	 * @param etagKeys
	 * @param clazz
	 * @return
	 */
	public static String[] createTagsByClazz(Context context,
			String[] etagKeys, Class<? extends BaseResponseBean> clazz) {
		String responseJson2String = getResponseByClazz(context, clazz);
		String[] result = new String[etagKeys.length];
		if (!EfunStringUtil.isEmpty(responseJson2String)) {
			try {
				JSONObject jsonObject = new JSONObject(responseJson2String);
				for (int i = 0; i < etagKeys.length; i++) {
					if (jsonObject.has(etagKeys[i])) {
						result[i] = jsonObject.optString(etagKeys[i], "");
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				for (int i = 0; i < result.length; i++) {
					result[i] = "";
				}
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				result[i] = "";
			}
		}
		return result;
	}

	public static boolean changeNotify(Context context, String[] etagKeys,
			String[] etagValues, Class<? extends BaseResponseBean> clazz) {
		String[] result = createTagsByClazz(context, etagKeys, clazz);
		for (int i = 0; i < result.length; i++) {
			if (!result[i].equals(etagValues[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 存储
	 * 
	 * @param context
	 * @param clazz
	 */
	public static void saveResponseByClazz(Context context,
			Class<? extends BaseResponseBean> clazz, String response) {
		EfunDatabase.saveSimpleInfo(context, clazz.getName(), clazz.getName(),
				response);
	}

	public static boolean clearByClazz(Context context, Class<?> clazz) {
		return EfunDatabase.getEditor(context, clazz.getName()).clear()
				.commit();
	}

	/**
	 * 获取本地数据
	 * 
	 * @param context
	 * @param clazz
	 * @return
	 */
	public static String getResponseByClazz(Context context,
			Class<? extends BaseResponseBean> clazz) {
		return EfunDatabase.getSimpleString(context, clazz.getName(),
				clazz.getName());
	}

	/**
	 * 存储
	 * 
	 * @param context
	 * @param etagKeys
	 * @param etagValues
	 * @param clazz
	 */
	public static void saveRequestByClazz(Context context, String[] etagKeys,
			String[] etagValues, Class<? extends BaseRequestBean> clazz) {
		for (int i = 0; i < etagKeys.length; i++) {
			EfunDatabase.saveSimpleInfo(context, clazz.getName(), etagKeys[i],
					etagValues[i]);
		}
	}

	/**
	 * 存储
	 * 
	 * @param context
	 * @param etagKeys
	 * @param etagValues
	 * @param clazz
	 */
	public static void saveSimpleInfoByClazz(Context context,
			String[] etagKeys, String[] etagValues,
			Class<? extends Object> clazz) {
		for (int i = 0; i < etagKeys.length; i++) {
			EfunDatabase.saveSimpleInfo(context, clazz.getName(), etagKeys[i],
					etagValues[i]);
		}
	}

	/**
	 * 獲取數據
	 * 
	 * @param context
	 * @param etagKey
	 * @param clazz
	 */
	public static String getSimpleInfoByClazz(Context context, String etagKey,
			Class<? extends Object> clazz) {
		return EfunDatabase.getSimpleString(context, clazz.getName(),
				etagKey);
	}

	/**
	 * 获取etag Values
	 * 
	 * @param context
	 * @param etagKeys
	 * @param clazz
	 * @return
	 */
	public static HashMap<String, String> createValuesByClazz(Context context,
			String[] etagKeys, Class<? extends BaseRequestBean> clazz) {
		HashMap<String, String> etagValues = new HashMap<String, String>(
				etagKeys.length);
		for (int i = 0; i < etagKeys.length; i++) {
			String etagItemValues = EfunDatabase.getSimpleString(context,
					clazz.getName(), etagKeys[i]);
			if (EfunStringUtil.isEmpty(etagItemValues)
					|| etagItemValues.equals("null")) {
				etagItemValues = "";
			}
			etagValues.put(etagKeys[i], etagItemValues);
		}
		return etagValues;
	}

	public static boolean installed(Context context) {
		String flag = EfunDatabase.getSimpleString(context,
				Store.class.getName() + "installed", Store.class.getName()
						+ "installed");
		if (EfunStringUtil.isEmpty(flag)) {
			EfunDatabase.saveSimpleInfo(context, Store.class.getName()
					+ "installed", Store.class.getName() + "installed",
					"installed");
			return true;
		}
		return false;
	}

	public static boolean startLogo(Context context) {
		String flag = EfunDatabase.getSimpleString(context,
				Store.class.getName() + "startLogo", Store.class.getName()
						+ "startLogo");
		if (EfunStringUtil.isEmpty(flag)) {
			EfunDatabase.saveSimpleInfo(context, Store.class.getName()
					+ "startLogo", Store.class.getName() + "startLogo",
					"startLogo");
			return true;
		}
		return false;
	}

	public static void saveHistoryLogin(Context context, String[] keys,
			String[] values) {
		for (int i = 0; i < keys.length; i++) {
			EfunDatabase.saveSimpleInfo(context, Store.class.getName()
					+ "HistoryLogin", keys[i], values[i]);
		}
	}

	public static String[] getHistoryLogin(Context context, String[] keys) {
		String[] values = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			values[i] = EfunDatabase.getSimpleString(context,
					Store.class.getName() + "HistoryLogin", keys[i]);
			if (EfunStringUtil.isEmpty(values[i])) {
				values[i] = "";
			}
		}
		return values;
	}
}
