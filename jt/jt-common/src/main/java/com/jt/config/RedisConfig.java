package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@Configuration //标识是一个配置类
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	/*
	 * 搭建redis集群
	 */
	@Value("${redis.nodes}")
	private String nodes;
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes = getNodes();
		return new JedisCluster(nodes);
	}
	private Set<HostAndPort> getNodes() {
		Set<HostAndPort> nodesSets=new HashSet<>();
		String[] strNode=nodes.split(",");
		for (String redisNode : strNode) {
			String host=redisNode.split(":")[0];
			int port = Integer.parseInt(redisNode.split(":")[1]);
			HostAndPort hostAndPort = 
					new HostAndPort(host, port);
			nodesSets.add(hostAndPort);

		}
		return nodesSets;
	}
	
	/*
	 * 1.xml配置文件添加bean标签
	 * 2.配置类的形式
	 * 配置;
	 *    将jedis对象交给spring容器管理
	 *    利用properties配置文件为属性动态赋值
	 */
	/*
	 * @Value("${redis.host}") private String host;
	 * 
	 * @Value("${redis.post}") private int port;
	 */
	/*
	 * @Value("${redis.nodes}") private String nodes;
	 * 
	 * @Bean public ShardedJedis shardedJedis() { List<JedisShardInfo> shards=new
	 * ArrayList<JedisShardInfo>(); String[] strNodes=nodes.split(","); for (String
	 * strNode : strNodes) { String[] node=strNode.split(":"); String host =node[0];
	 * int post=Integer.parseInt(node[1]); shards.add(new
	 * JedisShardInfo(host,post)); } return new ShardedJedis(shards); }
	 */
	/*
	 * 实现哨兵配置
	 */
/*	@Value("${redis.sentinel.masterName}")
	private String masterName;
	@Value("${redis.sentinels}")
	private String nodes;
	@Bean
	public JedisSentinelPool jedisSerntinelPool() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add(nodes);
		JedisSentinelPool pool=new JedisSentinelPool(masterName, sentinels);
		return pool;
	}
	@Bean
	@Scope("prototype")//多例对象
	public Jedis jedis(@Qualifier("jedisSerntinelPool")JedisSentinelPool pool) {//指定bean赋值
		
		Jedis jedis=pool.getResource();
		return jedis;
	}*/
}
