package com.db.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
@PropertySource("classpath:/properties/redis.properties")
@Lazy
public class RedisConfig {

	@Value("${redis.nodes}")
	private String hosts;

	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes = getNodes();
		return new JedisCluster(nodes);
	}

	private Set<HostAndPort> getNodes() {
		Set<HostAndPort> nodes = new HashSet<>();
		String[] node = hosts.split(",");
		for (String no : node) {
			String[] arr = no.split(":");
			String host = arr[0];
			int port = Integer.parseInt(arr[1]);
			nodes.add(new HostAndPort(host, port));
		}
		return nodes;
	}
}
