package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.mapper.UserMapper;
import com.db.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public Boolean findCheckUser(String param, Integer type) {
		User user = new User();
		switch (type) {
		case 1:
			user.setUsername(param);
			break;
		case 2:
			user.setPhone(param);
			break;
		case 3:
			user.setEmail(param);
			break;
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
		User one = userMapper.selectOne(queryWrapper);
		return one != null;
	}

	@Override
	public List<User> findAll() {
		List<User> list = userMapper.selectList(null);
		return list;
	}

	@Override
	public Boolean doLogin(User user) {
		User one = userMapper.selectOne(new QueryWrapper<User>(user));

		return one != null;
	}

}
