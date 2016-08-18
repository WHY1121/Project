package com.taotao.rest.service;

import javax.annotation.Resource;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisClient;

public interface RedisService {
	
	
	TaotaoResult syscContent(long contentId);
	
	
	

}
