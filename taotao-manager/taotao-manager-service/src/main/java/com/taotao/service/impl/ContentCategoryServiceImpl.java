package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUDataTree;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	
	@Resource
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EUDataTree> getCategoryList(long parentId) {
		
		
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		
		List<EUDataTree> resultList=new ArrayList<>();
		for(TbContentCategory contentCategory:list){
			EUDataTree euDataTree=new EUDataTree();
			euDataTree.setId(contentCategory.getId());
			euDataTree.setText(contentCategory.getName());
			euDataTree.setState(contentCategory.getIsParent()?"closed":"open");
			resultList.add(euDataTree);
			
		}
		return resultList;
	}
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		
		TbContentCategory contentCategory=new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setUpdated(new Date());
		contentCategory.setCreated(new Date());
	     //添加记录
		contentCategoryMapper.insert(contentCategory);
		
		//查看父节点
		TbContentCategory paCategory=contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!paCategory.getIsParent()){
			paCategory.setIsParent(true);
			//更新
			contentCategoryMapper.updateByPrimaryKey(paCategory);
			
		}
		
		return TaotaoResult.ok(contentCategory);
	}

}
