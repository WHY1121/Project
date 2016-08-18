package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片
 * @author fsdfsdsss
 *
 */
public interface PictureService {

	
	 Map uploadFile(MultipartFile uploadFile);
}
