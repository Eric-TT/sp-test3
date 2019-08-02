package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	/*
	 * @Override public User findUser(User user) { QueryWrapper<User> queryWrapper =
	 * new QueryWrapper<>(user); return userMapper.selectOne(queryWrapper); }
	 */

	@Override
	public boolean findUserCheck(String param, Integer type) {
		String column=(type==1)?"username":(type==2?"phone":"email");
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(column, param);
		int count=userMapper.selectCount(queryWrapper);
		return false;
	}

	
	
	
	
}
