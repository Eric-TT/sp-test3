package com.jt;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
	/*
	 * spring整合redis入门案例
	 */
	@Test
	public void testRedis1() {
		String host="192.168.32.129";
		int port=6379;
		Jedis jedis=new Jedis(host,port);
		jedis.set("1903", "1903毕业了");
		System.out.println(jedis.get("1903"));
		//设定数据超时时间
		jedis.expire("1903", 20);
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1903还能存活"+jedis.ttl("1903"));
	}
	@Test
	public void testRedis2() {
		Jedis jedis = new Jedis("192.168.32.129", 6379);
		jedis.setex("abc", 100, "英文字母");
		System.out.println(jedis.get("abc"));
	}
	/*
	 * 锁机制用法
	 * 
	 */
	@Test
	public void testRedis3() {
		Jedis jedis = new Jedis("192.168.32.129", 6379);
		//jedis.set("yue", "8点xxxx地点");
		//jedis.set("yue", "5点xxxx");
		Long flag1 = jedis.setnx("yue", "8点xxxx地点");
		Long flag2 = jedis.setnx("yue", "5点xxxx");
		System.out.println(flag1+":::"+flag2);
		System.out.println("yue"+jedis.get("yue"));
	}
	/*
	 * 锁机制优化
	 */
	@Test
	public void testRedis4() {
		Jedis jedis = new Jedis("192.168.32.129", 6379);
		String result1 = jedis.set("yue", "晚上8点", "NX", "EX", 20);
		jedis.del("yue");
		String resuly2 = jedis.set("yue", "晚上9点", "NX", "EX", 20);
		System.out.println(result1);
		System.out.println(resuly2);
	}
	@Test
	public void testHash1() {
		Jedis jedis = new Jedis("192.168.32.129", 6379);
		jedis.hset("user", "id", "100");
		jedis.hset("user", "name", "wangba");
		jedis.hset("user", "age", "1000");
		System.out.println(jedis.hgetAll("user"));
	}
	/*
	 * list集合
	 */
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.32.129", 6379);
		jedis.lpush("list", "1,2,3,4,5");
		System.out.println("输出"+jedis.lpop("list"));
	}
	/*
	 * 测试事务控制
	 */
	@Test
	public void testTx() {
		Jedis jedis = new Jedis("192.168.32.129", 6379);
		Transaction transaction = jedis.multi();//开启事务
		try {
			transaction.set("aa", "aaa");
			transaction.set("bb", "bbb");
			//int a=1/0;
			transaction.exec();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
			transaction.discard();
		}
	}
}
