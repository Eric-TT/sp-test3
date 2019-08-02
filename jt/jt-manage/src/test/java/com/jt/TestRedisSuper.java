package com.jt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestRedisSuper {
	@Test
	public void testShards() {
		List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
		String host="192.168.32.129";
		shards.add(new JedisShardInfo(host,6379));
		shards.add(new JedisShardInfo(host,6380));
		shards.add(new JedisShardInfo(host,6381));
		ShardedJedis jedis = new ShardedJedis(shards);
		jedis.set("1903", "不懂");
		System.out.println(jedis.get("1903"));
	}
	@Test
	public void testSentinel() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add("192.168.32.129:26379");
		JedisSentinelPool pool=new JedisSentinelPool("mymaster", sentinels);
		Jedis jedis=pool.getResource();
		jedis.set("1903", "测试");
		System.out.println(jedis.get("1903"));
	}
	/*
	 * 测试redis集群
	 */
	@Test
	public void testCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.32.129", 7000));
		nodes.add(new HostAndPort("192.168.32.129", 7001));
		nodes.add(new HostAndPort("192.168.32.129", 7002));
		nodes.add(new HostAndPort("192.168.32.129", 7003));
		nodes.add(new HostAndPort("192.168.32.129", 7004));
		nodes.add(new HostAndPort("192.168.32.129", 7005));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("1903", "搭建成功");
		System.out.println(jedisCluster.get("1903"));
	}
}
