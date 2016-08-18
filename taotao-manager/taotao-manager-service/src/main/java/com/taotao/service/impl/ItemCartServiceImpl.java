package com.taotao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCartService;
@Service
public class ItemCartServiceImpl implements ItemCartService{

	
	
	@Resource
	private TbItemCatMapper tbItemCartMapper;
	
	@Override
	public List<TbItemCat> getItemCatList(Long parentId){
		TbItemCatExample example =new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//根基parentId查询节点
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list=tbItemCartMapper.selectByExample(example);
		
		return list;
	}

}
