package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {
	
	public void testFtpClient() throws Exception {
		// 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建ftp连接。默认是21端口
		ftpClient.connect("192.168.43.53", 21);
		// 登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		// 上传文件。
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("F:\\001.jpg"));
		// 设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//未加这句上传可以成功但是文件是0字节，加上这句就好了，不是很明白为什么
		ftpClient.enterLocalPassiveMode(); 
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数：服务器端文档名
		// 第二个参数：上传文档的inputStream
		ftpClient.storeFile("hello2.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
	}

	@Test
	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(
				new File("F:\\我的文件\\MyPhoto\\psu.jpg"));
		FtpUtil.uploadFile("192.168.43.53", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "/2015/09/04",
				"hello3.jpg", inputStream);
	}
}
