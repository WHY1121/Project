package com.taotao.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
     
	@Resource
    private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public String getItemParamByItemId(Long itemId) {
		
		TbItemParamItemExample example=new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list==null||list.size()==0){
			return "";
		}
		TbItemParamItem tbItemParamItem = list.get(0);
		String paramData = tbItemParamItem.getParamData();
		//拼接为html
		StringBuffer sb=new StringBuffer();
		List<Map> jsonList=JsonUtils.jsonToList(paramData, Map.class);
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">");
		sb.append("<tbody>");
        for(Map map:jsonList){
		sb.append("<tr><th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th></tr>");
		sb.append("<tr>");
		sb.append("</tr>");
		List<Map> list2=(List<Map>) map.get("params");
		for(Map map2:list2){
		sb.append("<tr><td class=\"tdTitle\">"+map2.get("k")+"</td><td>"+map2.get("v")+"</td></tr>");
		}
		sb.append("</tbody>");
		sb.append("</table>");
		}
		
		
		return sb.toString();
	} 
     
  
	

}
