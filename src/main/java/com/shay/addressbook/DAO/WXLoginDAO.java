package com.shay.addressbook.DAO;

import org.springframework.stereotype.Repository;

import com.shay.addressbook.entity.User;

@Repository
public interface WXLoginDAO {

	Integer checkUser(User user);
	void saveUser(User user);
}
