package com.shay.addressbook.DAO;

import org.springframework.stereotype.Repository;

import com.shay.addressbook.entity.User;

@Repository
public interface UserDAO {

	User getUserInfo(User user);
	void saveUser(User user);
	void updateUser(User user);
	void saveAddress(User user);
}
