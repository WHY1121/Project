package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCartService;
@Service
public class ItemCartServiceImpl implements ItemCartService{

	
	@Autowired
	private TbItemCatMapper itemCartMapper;  
	
	@Override
	public CatResult getItemCartList() {
		
		CatResult result=new CatResult();
		result.setData(getCatList(0));
		return result;
	}
    /**
     * 查询分类列表
     * @param parentId
     * @return
     */
	private List<?> getCatList(long parentId) {
	
		
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCartMapper.selectByExample(example);
		List resultList=new ArrayList<>();
		//添加节点
		int count=0;
		for(TbItemCat tbItemCat:list){
			if(tbItemCat.getIsParent()){
			CatNode node=new CatNode();
			if(parentId==0){
				node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
			}else{
			    node.setName(tbItemCat.getName());
			}
			node.setUrl("/products/"+tbItemCat.getId()+".html");
			node.setItem(getCatList(tbItemCat.getId()));
			resultList.add(node);
			count++;
			if(count>=14){
				break;
			}
			}else{
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}
