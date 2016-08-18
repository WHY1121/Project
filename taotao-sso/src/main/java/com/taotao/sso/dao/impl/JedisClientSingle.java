package com.taotao.sso.dao.impl;

import javax.annotation.Resource;

import com.taotao.sso.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient{

	
	@Resource
	private JedisPool jedisPool;
	@Override
	public String get(String key) {
		Jedis resource = jedisPool.getResource();
		String string=resource.get(key);
		resource.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis resource = jedisPool.getResource();
		String string = resource.set(key, value);
		resource.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis resource = jedisPool.getResource();
		String string = resource.hget(hkey, key);
		resource.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

}
