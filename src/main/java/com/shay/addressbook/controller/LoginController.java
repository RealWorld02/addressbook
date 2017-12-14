package com.shay.addressbook.controller;

import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shay.addressbook.util.LoginUtil;

/**
 * 微信小程序登陆接口 通过前台页面传入的code值
 */
@RestController
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/wxlogin", method = RequestMethod.POST)
	public JSONObject login(String code, String encryptedData, String iv) {
		String session_key = null;
		String token = LoginUtil.getWebAccess(code);
		String rec = LoginUtil.httpGet(token);
		JSONObject json = JSON.parseObject(rec);
		if (json != null) {
			session_key = json.getString("session_key");
		}
		byte[] dataByte = Base64.decode(encryptedData);// 被加密的数据
		byte[] keyByte = Base64.decode(session_key);// 加密秘钥
		byte[] ivByte = Base64.decode(iv);// 偏移量
		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				System.out.println(result);
				return JSON.parseObject(result);
			}
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
