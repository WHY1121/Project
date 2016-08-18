package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService {

	
	@Resource
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
	
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否 查询到结果
		if(list!=null && list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
