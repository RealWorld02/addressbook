package com.shay.addressbook.service;

import org.springframework.stereotype.Service;

import com.shay.addressbook.entity.User;

@Service
public interface UserService {

	public String saveUser(String code, String encryptedData, String iv);
	
	public User getUserInfo(User user);
	public String saveAddress(User user);
}
