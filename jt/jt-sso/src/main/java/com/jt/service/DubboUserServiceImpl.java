package com.jt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public void saveUser(User user) {
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setEmail(user.getPhone())
			.setPassword(md5Pass)
			.setCreated(new Date())
			.setUpdated(user.getCreated());
		userMapper.insert(user);
		
	}

	@Override
	public String doLogin(User user) {
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		User userDB = userMapper.selectOne(queryWrapper);
		String token=null;
		if (userDB!=null) {
			String tokenTemp="JT_TICKET"+System.currentTimeMillis()+user.getUsername();
			tokenTemp=DigestUtils.md5DigestAsHex(tokenTemp.getBytes());
			userDB.setPassword("123");
			String userJSON = ObjectMapperUtil.toJson(userDB);
			jedisCluster.setex(tokenTemp, 7*24*3600, userJSON);
			token=tokenTemp;
		}
		return token;
	}
	

}
