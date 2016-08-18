package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface ContentService {

	//首页广告的接口
	List<TbContent> getContentList(long contentCid);
}
