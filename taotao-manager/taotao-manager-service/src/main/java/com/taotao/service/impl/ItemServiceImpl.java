package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Resource
	private TbItemMapper itemMapper;
	@Resource
	private TbItemDescMapper  itemDescMapper;
	@Resource
    private TbItemParamItemMapper itemParamItemMapper; 
	
	
	
	@Override
	public TbItem getItemById(long itemId) {
		//添加查询条件
		TbItemExample example =new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list=itemMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			TbItem item=list.get(0);
			return item;
		}
		
		return null;
	}
    
	/**
	 * 使用pageHelper分页显示数据
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		
		
		//查询商品列表
		TbItemExample example =new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list=itemMapper.selectByExample(example);
		//创建返回对象
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		
		//取回记录总条数
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult saveItem(TbItem item, String desc,String itemParam) throws Exception {
		Date date=new Date();
		//获得商品id
		long id=IDUtils.genItemId();
		//添加商品信息
		item.setId(id);
		//商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		//添加商品描述
		TaotaoResult result=insertItemDesc(id, desc);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		//添加商品规格信息
		result=insertItemParamItem(id, itemParam);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		
		return TaotaoResult.ok();
	}
	
	//添加商品描述信息表

    public TaotaoResult insertItemDesc(Long itemId, String desc) {
		
    	TbItemDesc itemDesc = new TbItemDesc();
		//获得一个商品id
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//插入数据
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	//添加商品信息规格表
    public TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		
		TbItemParamItem item=new TbItemParamItem();
		item.setItemId(itemId);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		item.setParamData(itemParam);
		itemParamItemMapper.insert(item);
		return TaotaoResult.ok();
	}

}
