package com.db.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.mapper.UserMapper;
import com.db.pojo.User;
import com.db.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

@Service
public class DubboUserServiceImpl implements DubboUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;

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
	public int doRegister(User user) {
		String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pwd).setEmail(user.getPhone()).setCreated(new Date()).setUpdated(user.getCreated());
		int insert = userMapper.insert(user);
		return insert;
	}

	@Override
	public String doLogin(User user) {
		String md5pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5pwd);
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		User one = userMapper.selectOne(queryWrapper);
		String token = null;
		if (one != null) {
			String salt = "DB_TICKET_" + System.currentTimeMillis() + one.getUsername();
			salt = DigestUtils.md5DigestAsHex(salt.getBytes());
			one.setPassword("************");
			String json = ObjectMapperUtil.toJSON(one);
			jedisCluster.setex(salt, 7 * 24 * 3600, json);
			token = salt;
		}
		return token;
	}

	@Override
	public User findUser(Long userId) {
		User user = userMapper.selectById(userId);
		return user;
	}
}
