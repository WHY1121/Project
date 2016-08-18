package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	public TaotaoResult contentCachSync(@PathVariable Long contentId){
		TaotaoResult result = redisService.syscContent(contentId);
		return result;
		
	}
}
