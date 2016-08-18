package com.taotao.portal.service;


import org.springframework.beans.factory.annotation.Value;

import com.taotao.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString,int page);
	
	
	
	
	
}
