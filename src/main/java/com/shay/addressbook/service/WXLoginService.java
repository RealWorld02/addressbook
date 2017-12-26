package com.shay.addressbook.service;

import org.springframework.stereotype.Service;

@Service
public interface WXLoginService {

	public String saveUser(String code, String encryptedData, String iv);
}
