package com.db.aspect;

import com.db.annotation.Cache_Find;
import com.db.annotation.Cache_Update;
import com.db.enums.TYPE_ENUM;
import com.db.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Aspect
@Component
public class CacheAspect {

	@Autowired(required = false)
	private JedisCluster jedis;

	@Around("@annotation(cache_update)")
	public Object updateCache(ProceedingJoinPoint point, Cache_Update cache_update) throws Throwable {
		String preValue = cache_update.value();
		Map<String, JedisPool> nodes = jedis.getClusterNodes();
		out: for (Entry<String, JedisPool> node : nodes.entrySet()) {
			Jedis resource = node.getValue().getResource();
			Set<String> keys = resource.keys(preValue + "*");
			for (String str : keys) {
				try {
					resource.del(str);
				} catch (Exception e) {
					continue out;
				} finally {
					resource.close();
				}
			}
		}
		Object result = point.proceed();
		return result;
	}

	@Around("@annotation(cache_find)")
	public Object findCache(ProceedingJoinPoint point, Cache_Find cache_find) throws Throwable {
		String key = getKey(point, cache_find);
		String string = jedis.get(key);
		Object result = null;
		if (StringUtils.isEmpty(string)) {
			System.out.println("查找数据库");
			result = point.proceed();
			String json = ObjectMapperUtil.toJSON(result);
			if (cache_find.secondes() == 0) {
				jedis.set(key, json);
			} else {
				jedis.setex(key, cache_find.secondes(), json);
			}
		} else {
			System.out.println("查找缓存");
			Class<?> returnType = getClass(point);
			result = ObjectMapperUtil.toObject(string, returnType);
		}
		return result;
	}

	private Class<?> getClass(ProceedingJoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		return signature.getReturnType();
	}

	private String getKey(ProceedingJoinPoint point, Cache_Find cache) {
		TYPE_ENUM enum_type = cache.type();
		if (enum_type.equals(TYPE_ENUM.EMPTY)) {
			return cache.value();
		}
		String value = cache.value();
		if (StringUtils.isEmpty(cache.value())) {
			MethodSignature method = (MethodSignature) point.getSignature();
			value = method.getName();
		}
		Object[] args = point.getArgs();
		String arg0 = args[0].toString();
		String key = value + arg0;
		return key;
	}

}
