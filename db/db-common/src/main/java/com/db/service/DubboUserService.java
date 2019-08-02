package com.db.service;

import com.db.pojo.User;

public interface DubboUserService {

	Boolean findCheckUser(String param, Integer type);

	int doRegister(User user);

	String doLogin(User user);

	User findUser(Long userId);

}
