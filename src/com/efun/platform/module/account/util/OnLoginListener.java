package com.efun.platform.module.account.util;

import com.efun.platform.module.base.impl.OnEfunListener;

/**
 * 三方登陆回调接口
 * @author Jesse
 *
 */
public interface OnLoginListener extends OnEfunListener{
	/**
	 * 登陆完成
	 * @param third_uid
	 */
	public void loginCompleted(String third_uid);
}
