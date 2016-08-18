package com.taotao.sso.dao;

public interface JedisClient {
	
	
	String get(String key);
	String set(String key,String value);
	String hget(String hkey,String key);
	long hset(String heky,String key,String value);
	//增长的值
	long incr(String key);
	long expire(String key,int second);
	long ttl(String key);
	long hdel(String hkey,String key);
	

}