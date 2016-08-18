package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


import com.taotao.search.dao.ItemSearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.ItemService;

@Repository
public class ItemSearchDaoImpl implements ItemSearchDao{

	
	
	@Resource
	private SolrServer SolrServer;
	@Override
	public SearchResult  queryDocument(SolrQuery solrQuery) throws Exception {
		
		//根据查询条件搜索索引库
		QueryResponse response=SolrServer.query(solrQuery);
		//获取商品列表
		SolrDocumentList documentList=response.getResults();
		
		//商品列表
		List<Item> items=new ArrayList<>();
		
		for(SolrDocument doList:documentList){
			
			Item item=new Item();
			
			item.setId((String)doList.get("id"));
			//高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			
			List<String> list = highlighting.get(doList.get("id")).get("item_title");
			String title="";
			if(null !=list && !list.isEmpty()){
				title=list.get(0);
				
			}else{
				title=(String)doList.get("item_title");
				
			}
			item.setTitle(title);
			item.setPrice((Long)doList.get("item_price"));
			item.setSell_point((String) doList.get("item_sell_point"));
			item.setImage((String) doList.get("item_image"));
			item.setCategory_name((String) doList.get("item_category_name"));
			items.add(item);
		}
		SearchResult result=new SearchResult();
		
		//商品列表
		result.setItemList(items);
		result.setRecordCount(documentList.getNumFound());
		return result;
	}
	
	

}
