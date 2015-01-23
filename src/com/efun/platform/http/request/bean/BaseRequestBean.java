package com.efun.platform.http.request.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.efun.core.tools.EfunLogUtil;
import com.efun.core.tools.EfunStringUtil;
import com.efun.platform.http.dao.impl.IPlatformRequest;
import com.efun.platform.utils.GameToPlatformParamsSaveUtil;
/**
 * 请求类
 * @author Jesse
 *
 */
public abstract class BaseRequestBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 请求类型 ，可以查看 {@link IPlatformRequest} 接口
	 */
	private int reqType;

	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}
	/**
	 * 通过反射，够着POST请求参数
	 * @param mClazz {@link BaseRequestBean}
	 * @return
	 */
	public List<NameValuePair> buildPostParams(Class<? extends BaseRequestBean> mClazz) {
		StringBuffer buffer = new StringBuffer("[ RequestName:" + mClazz.getSimpleName() + "|");
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		Field[] fields = mClazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].getName().equals("serialVersionUID")) {
				try {
					if (fields[i].getName().equals("uid")) {
						String uid = "0";
						if(GameToPlatformParamsSaveUtil.getInstanse().getUser() != null){							
							if(!EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid())){
								if(fields[i].get(this)!=null){
									uid = fields[i].get(this).toString();
								}else{								
									uid = GameToPlatformParamsSaveUtil.getInstanse().getUser().getUid();
								}
							}
						}else{
							if(!EfunStringUtil.isEmpty(GameToPlatformParamsSaveUtil.getInstanse().getUid())){
								if(fields[i].get(this)!=null){
									uid = fields[i].get(this).toString();
								}else{								
									uid = GameToPlatformParamsSaveUtil.getInstanse().getUid();
								}
							}
						}
						postParams.add(new BasicNameValuePair(fields[i].getName(), uid));
						buffer.append(fields[i].getName() + ":" + uid);
					} else {
						String value = "";
						if(fields[i].get(this)!=null){
							value = fields[i].get(this).toString();
						}
						postParams.add(new BasicNameValuePair(fields[i].getName(),value));
						buffer.append(fields[i].getName() + ":" + value);
					}
					if (i != fields.length - 1) {
						buffer.append("|");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		buffer.append("]");
		EfunLogUtil.logD(buffer.toString());
		return postParams;
	}

}
