package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.JsonUtils;
import com.taotao.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {

	
	
	@Autowired
	private PictureService pictureService;
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile uploadFile) throws Exception{
		
		Map map=pictureService.uploadFile(uploadFile);
		String json=JsonUtils.objectToJson(map);
		return json;
		
		
	}
}
