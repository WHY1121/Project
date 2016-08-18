package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private TbUserMapper userMapper;
	@Resource
	private JedisClient jedisClient;
	@Value("${SSO_SESSION_EXPIRE}")
	private int SSO_SESSION_EXPIRE;
	@Value("$REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Override
	public TaotaoResult checkData(String content, Integer type) {
		//创建查询条件、
		TbUserExample example=new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		if(1==type){
			createCriteria.andUsernameEqualTo(content);
		}else if (2==type) {
			createCriteria.andPhoneEqualTo(content);
			
		}else if (3==type) {
			createCriteria.andEmailEqualTo(content);
		}
		List<TbUser> user=userMapper.selectByExample(example);
		if(user==null||user.size()==0){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setUpdated(new Date());
		user.setCreated(new Date());
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();

	}

	@Override
	public TaotaoResult userLogin(String username, String password,HttpServletRequest request,HttpServletResponse response) {
		
		TbUserExample example=new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if(null ==list||list.size()==0){
			return TaotaoResult.build(400, "用户名,密码错误");
			
		}
		TbUser tbUser = list.get(0);
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())){
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		String token = UUID.randomUUID().toString();
		tbUser.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, JsonUtils.objectToJson(tbUser));
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token,SSO_SESSION_EXPIRE);
		//添加到cookie
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		
		
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		
		String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		if(StringUtils.isBlank(json)){
			return TaotaoResult.build(400, "此session已经过期请重新登录");
			
		}
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token,SSO_SESSION_EXPIRE);
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}



}
