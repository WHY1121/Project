package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.ItemSearchService;
import com.taotao.utils.ExceptionUtil;

@Controller
public class ItemSearchController {
	
	@Autowired
	private ItemSearchService itemSearchService;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam(value="q") String queryString,
			@RequestParam(value="page",defaultValue="1")Integer page){
		
		if(StringUtils.isBlank(queryString)){
			
			return TaotaoResult.build(400, "查询条件是必须的参数");
		}
		SearchResult result=null;
		try {
			String string=new String(queryString.getBytes("iso-8859-1"), "utf-8");
			result=itemSearchService.searchItem(string, page);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TaotaoResult.ok(result);
		
		
	}
	

}
