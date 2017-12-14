package com.shay.addressbook.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class LoginUtil {
	/**
	 * appid 小程序id
	 */
	public static final String APPID = "wx226409f8ba03e176";
	/**
	 * AppSecret 小程序密钥
	 */
	public static final String SECRET = "9b531993adb4492ded942b9cea84664d";
	/**
	 * 用于获取用户openid的接口网址
	 */
	public static final String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

	/**
	 * 通过APPID，SECET，code组合出用于获取用户openid的实际网址
	 */
	public static String getWebAccess(String code) {
		return String.format(Web_access_tokenhttps, APPID, SECRET, code);
	}

	/**
	 * 通过HttpGet类发送GET请求并获取返回信息
	 * 
	 * @param path
	 *            发送至的网址
	 * @return
	 */
	public static String httpGet(String path) {
		if (path == null) {
			return null;
		}
		String rec = null;
		HttpGet get = new HttpGet(path);
		try {
			HttpResponse response = HttpClients.createDefault().execute(get);
			HttpEntity entity = response.getEntity();
			rec = EntityUtils.toString(entity);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return rec;
	}
}
