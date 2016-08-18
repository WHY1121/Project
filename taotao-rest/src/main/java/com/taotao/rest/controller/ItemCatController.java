package com.taotao.rest.controller;



import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCartService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCartService itemCartService;
	
	
	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback){
		
		CatResult catResult = itemCartService.getItemCartList();
		//把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值
		String result = callback + "(" + json + ");";
		return result;
	}
//	Spring 4.1支持以下风格
//	@RequestMapping("/itemcat/list")
//	@ResponseBody
//	public Object getItemCatList(String callback) {
//		CatResult catResult = itemCatService.getItemCatList();
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
//		mappingJacksonValue.setJsonpFunction(callback);
//		return mappingJacksonValue;
//	}
	
}
