package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *后台首页
 * @author fsdfsdsss
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PageController {

	
	@RequestMapping("/")
	public String showIndex(){
		
		
		return "index";
	}
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		
		
		return page;
	}
}
