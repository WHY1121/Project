package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbItemCat;

/**
 * 商品分类接口
 * @author fsdfsdsss
 *
 */
public interface ItemCartService {
	
	/**
	 * 根据父id查询商品分类
	 * @param parentId
	 * @return
	 */
	List<TbItemCat> getItemCatList(Long parentId);
}
