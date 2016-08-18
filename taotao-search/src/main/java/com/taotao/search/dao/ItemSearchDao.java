package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

public interface ItemSearchDao {
   
	SearchResult  queryDocument(SolrQuery solrQuery) throws Exception;
	
}
