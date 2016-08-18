package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataTree;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCartService;

@Controller
@RequestMapping("/item/cat")
public class ItemCartController {

	
	@Autowired
	private ItemCartService itemCartService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUDataTree> categoryList(@RequestParam(value="id",defaultValue="0") Long parentId) throws Exception{
		
		
		List<TbItemCat> itemCatList = itemCartService.getItemCatList(parentId);
		
		List<EUDataTree> list=new ArrayList<>();
		for(TbItemCat itemCat:itemCatList){
			EUDataTree tree=new EUDataTree();
			tree.setId(itemCat.getId());
			tree.setText(itemCat.getName());
			tree.setState(itemCat.getIsParent()?"closed":"open");
			list.add(tree);
		}
		return list;
	}
	
	
}
