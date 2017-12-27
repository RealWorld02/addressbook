package com.shay.addressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shay.addressbook.service.WXLoginService;

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