package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public interface ItemSearchService {
	
	
	 SearchResult searchItem(String queryString, Integer page) throws Exception;
}
