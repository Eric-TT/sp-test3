package com.db.service;

import java.util.List;

import com.db.pojo.User;

public interface UserService {

	Boolean findCheckUser(String param, Integer type);

	List<User> findAll();

	Boolean doLogin(User user);

}
