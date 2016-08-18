package com.taotao.rest.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import com.taotao.utils.ExceptionUtil;
@Service
public class RedisServiceImpl implements RedisService{
	
	
	@Resource
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENR_REDIS_KEY}")
	private String INDEX_CONTENR_REDIS_KEY;

	@Override
	public TaotaoResult syscContent(long contentId) {
		try {
			jedisClient.hdel(INDEX_CONTENR_REDIS_KEY, contentId+"");
		} catch (Exception e) {
			//通知管理员  邮件或者短信
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return null;
	}
	
	

}
