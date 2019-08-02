package com.jt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMybatisPlus {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void insertUser() {
		User user = new User();
		user.setName("1903").setAge(4).setSex("都有");
		userMapper.insert(user);
		System.out.println("添加成功");
	}
	//2.查询操作
	/**
	 * queryWrapper表示条件构造器
	 * 动态拼接where name="1903班"
	 * 对象中不为null属性充当where条件
	 */
	@Test
	public void select_Id_count() {
		//根据Id查询
		User user = userMapper.selectById(53);
		System.out.println(user);
		//查询全部记录数 
		int count = userMapper.selectCount(null);
		System.out.println("获取记录总数为:"+count);
		//查询全部的女性记录数  sex="女"
		//1.面向对象方式
		User userCount = new User();
		userCount.setSex("女");
		QueryWrapper<User> queryWrapper =new QueryWrapper<>(userCount);
		int sexCount = userMapper.selectCount(queryWrapper);
		System.out.println("获取记录总数:"+sexCount);
	}

	/* 条件查询 
	 * > gt < lt =eq
	 * >= ge <=le
	 * 
	 * */
	@Test
	public void selectUser2() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.gt("age", 18);
		int count = userMapper.selectCount(queryWrapper);
		System.out.println("age>18人数"+count);
	}
	@Test
	public void selectUser3() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.ge("age", 18).eq("sex", "女");
		int count=userMapper.selectCount(queryWrapper);
		userMapper.selectCount(queryWrapper);
		System.out.println("数量"+count);
	}
	//删除
	@Test
	public void deleteUser() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", "1903");
		userMapper.delete(queryWrapper);
	}
	@Test
	public void updateUser() {
		//根据id进行更新,则id字段自动充当where条件
		/*
		 * User user = new User(); user.setId(54).setName("如花").setAge(120).setSex("女");
		 * userMapper.updateById(user);
		 */
		
		User temUser=new User();
		temUser.setName("不知火舞").setAge(18);
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("name", "如花");
		userMapper.update(temUser, updateWrapper);
	}
}
