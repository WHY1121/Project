package com.taotao.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	@Resource
	private JedisClient JedisClient;

	@Resource
	private TbItemMapper itemMapper;
	@Resource
	private TbItemDescMapper itemDescMapper;
	@Resource
    private TbItemParamItemMapper itemParamMapper;

	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {

		// 从缓存中取数据
		try {
			String json = JedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if (!StringUtils.isBlank(json)) {
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(item);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		// 根据商品id查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);

		// 添加缓存
		try {
			JedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			JedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(long itemId) {
		// 从缓存中取数据
		try {
			String json = JedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if (!StringUtils.isBlank(json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		// 根据商品id查询商品信息
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

		// 添加缓存
		try {
			JedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			JedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParam(long itemId) {
		// 添加缓存
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品id对应的信息
			String json = JedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			// 判断是否有值
			if (!StringUtils.isBlank(json)) {
				// 把json转换成java对象
				TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(paramItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询规格参数
		// 设置查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem paramItem = list.get(0);
			try {
				// 把商品信息写入缓存
				JedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
				// 设置key的有效期
				JedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaotaoResult.ok(paramItem);
		}
		return TaotaoResult.build(400, "无此商品规格");
	}

}
