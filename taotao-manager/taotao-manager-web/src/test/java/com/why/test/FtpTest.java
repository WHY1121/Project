package com.why.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpTest {

	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.146.132",21);
		ftpClient.login("ftpuser", "ftpuser");
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\fsdfsdsss\\Desktop\\123.png"));
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("123.jpg", inputStream);
		inputStream.close();
		
		ftpClient.logout();
	}
	
	
	
	
	
}
