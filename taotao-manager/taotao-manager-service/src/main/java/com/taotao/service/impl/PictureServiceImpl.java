package com.taotao.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService {

	
	//注入protities
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Override
	public Map uploadFile(MultipartFile uploadFile) {
		
		Map map=savePicture(uploadFile);
		
		return map;
	}
   /**
    * 保存图片
    * @param uploadFile
    * @return
    * @throws Exception 
    */
	private Map savePicture(MultipartFile uploadFile) {
		
		Map  map=new HashMap<>();
		try {
			//判断是否为空
			if(uploadFile.isEmpty()){
				return null;
			}
			 //更改文件名
			String  originalName=uploadFile.getOriginalFilename();
			String newName=IDUtils.genImageName();
			newName=newName+originalName.substring(originalName.lastIndexOf("."));
			//上传文件路径
			String filePath=new DateTime().toString("/yyyy/MM/dd");
			
			//上传到ftp服务器
			
			boolean meaasge=FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FTP_BASE_PATH, filePath, newName, uploadFile.getInputStream());
			if(!meaasge){
				map.put("error", 1);
				map.put("message", "文件上传失败");
				return map;
			}
			map.put("error", 0);
			map.put("url", IMAGE_BASE_URL+filePath+"/"+newName);
			return map;
		} catch (Exception e) {
			map.put("error", 1);
			map.put("message", "文件上传异常");
			return map;
		}
		
		
	}

}
