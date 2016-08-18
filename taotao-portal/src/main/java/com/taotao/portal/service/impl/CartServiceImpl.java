/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年7月8日
 * @version 1.0
 */
package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.CartItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.service.CartService;
import com.taotao.utils.HttpClientUtil;

/**
 * @author fsdfsdsss
 *
 */
@Service
public class CartServiceImpl implements CartService{
	
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	/* (non-Javadoc)
	 * @see com.taotao.portal.service.CartService#addCartItem(long, int, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		
		CartItem cartItem=null;
		List<CartItem> itemList=getCartItemList(request);
		for(CartItem item:itemList){
			if(item.getId()==itemId){
				item.setNum(item.getNum()+num);
				cartItem=item;
				break;
			}
			
		}
		if(cartItem==null){
			cartItem=new CartItem();
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			TaotaoResult result=TaotaoResult.formatToPojo(json, TbItem.class);
			if(result.getStatus()==200){
				TbItem item=(TbItem) result.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage()==null?"":(item.getImage().split(",")[0]));
				cartItem.setNum(item.getNum());
				cartItem.setPrice(item.getPrice());
			}
			itemList.add(cartItem);
		}
		CookieUtils.setCookie(request, response,  "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}
	//从cookie中获取商品列表
	private List<CartItem> getCartItemList(HttpServletRequest request){
		
		String cartjson=CookieUtils.getCookieValue(request, "TT_CART", true);
		if(cartjson==null){
			return new ArrayList<>();
		}
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartjson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	/* (non-Javadoc)
	 * @see com.taotao.portal.service.CartService#getCartItemList(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {

		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}

}
