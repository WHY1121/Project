/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年7月8日
 * @version 1.0
 */
package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.CartItem;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author fsdfsdsss
 *
 */
public interface CartService {
	TaotaoResult addCartItem(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response);
	 List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
}
