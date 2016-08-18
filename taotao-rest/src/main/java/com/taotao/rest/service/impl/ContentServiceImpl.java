package com.taotao.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.dao.impl.JedisClientCluster;
import com.taotao.rest.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	
	@Resource
	private TbContentMapper contentMapper;
	@Value("${INDEX_CONTENR_REDIS_KEY}")
	private String INDEX_CONTENR_REDIS_KEY;
	@Resource
	private JedisClient jedisClient;
	@Override
	public List<TbContent> getContentList(long contentCid) {
		//缓存中取数据
		try {
			String string = jedisClient.hget(INDEX_CONTENR_REDIS_KEY,contentCid+"");
			if (!StringUtils.isBlank(string)) {
				List<TbContent> list = JsonUtils.jsonToList(string, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据内容分类id查询
		TbContentExample example=new TbContentExample();
		Criteria createCriteria = example.createCriteria();
	    createCriteria.andCategoryIdEqualTo(contentCid);
	    List<TbContent> list = contentMapper.selectByExample(example);
	
		//缓存中放数据
        try {
        	String json = JsonUtils.objectToJson(list);
        	jedisClient.hset(INDEX_CONTENR_REDIS_KEY, contentCid+"", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}

}
