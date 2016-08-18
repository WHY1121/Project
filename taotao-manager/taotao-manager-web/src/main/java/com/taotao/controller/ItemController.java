package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	
	@Autowired
	private ItemService itemService;
	
	
	@ResponseBody
	@RequestMapping("/item/{itemId}")
	public TbItem getItemId(@PathVariable int itemId){
		TbItem item=itemService.getItemById(itemId);
		return item;
	}
	
	
	@ResponseBody
	@RequestMapping("/item/list")
	public EUDataGridResult getItemList(Integer page,Integer rows){
		EUDataGridResult result=itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(TbItem item, String desc,String itemParam) throws Exception {
		// 添加商品信息
		itemService.saveItem(item, desc, itemParam);
		return TaotaoResult.ok();
	}
}
