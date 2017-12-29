package com.shay.addressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shay.addressbook.entity.User;
import com.shay.addressbook.service.UserService;

/**
 * 微信登陆接口 通过前台页面传入的code值
 */
@RestController
public class UserController {

	@Autowired
	private UserService userServicce;

	@RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
	public String wxlogin(String code, String encryptedData, String iv) {
		return userServicce.saveUser(code, encryptedData, iv);
	}
	
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public User getUserInfo(User user){
		return userServicce.getUserInfo(user);
	}
	
	@RequestMapping(value = "/saveAddress", method = RequestMethod.GET)
	public String saveAddress(User user){
		return userServicce.saveAddress(user);
	}
}