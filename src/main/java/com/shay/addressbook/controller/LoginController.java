package com.shay.addressbook.controller;

import java.security.AlgorithmParameters;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shay.addressbook.service.WXLoginService;
import com.shay.addressbook.util.LoginUtil;

/**
 * 微信登陆接口 通过前台页面传入的code值
 */
@RestController
public class LoginController {

	@Autowired
	private WXLoginService wxLoginServicce;

	@RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
	public String wxlogin(String code, String encryptedData, String iv) {
		return wxLoginServicce.saveUser(code, encryptedData, iv);
	}
}