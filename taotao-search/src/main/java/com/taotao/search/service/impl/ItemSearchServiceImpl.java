package com.taotao.search.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.ItemSearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.ItemSearchService;


@Service
public class ItemSearchServiceImpl implements ItemSearchService{
	
	
	@Value("${SEARCH_RESULT_PAGE_SIZE}")
	private Integer PAGE_SIZE;
	@Resource
	private ItemSearchDao itemSearchDao;
	
	

	@Override
	public SearchResult searchItem(String queryString, Integer page) throws Exception {
		
		
		SolrQuery query=new SolrQuery();
		if(StringUtils.isBlank(queryString)){
			query.setQuery("*:*");
			
		}else{
			query.setQuery(queryString);
		}
		//分页条件
		
		if(page==null){
			page=1;
			
		}
		query.setStart((page-1)*PAGE_SIZE);
		query.setRows(PAGE_SIZE);
		//设置高亮显示
		
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		
		//设置默认搜索与
		query.set("df", "item_keywords");
		SearchResult result=itemSearchDao.queryDocument(query);
		
		//计算分页
		Long recordCount = result.getRecordCount();
		int pageCount=(int)(recordCount/PAGE_SIZE);
		if(recordCount%PAGE_SIZE>0){
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}

}
