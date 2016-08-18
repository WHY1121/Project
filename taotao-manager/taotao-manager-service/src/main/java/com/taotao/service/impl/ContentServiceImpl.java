package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import com.taotao.utils.HttpClientUtil;

@Service
public class ContentServiceImpl implements ContentService{

	
	
	@Resource
	private TbContentMapper contentMapper;
	
	@Value("${REST_BASR_URL}")
	private String REST_BASR_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		
		//不全pojo
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		try {
			HttpClientUtil.doGet(REST_BASR_URL+REST_CONTENT_SYNC_URL+content.getId());
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}
	@Override
	public EUDataGridResult getPageList(Integer page, Integer rows) {
		
		TbContentExample example=new TbContentExample();
		PageHelper.startPage(page, rows);
		List<TbContent> list=contentMapper.selectByExample(example);
		
		//返回创建对象
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		//取回记录总条数
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		
		return result;
	}

}
