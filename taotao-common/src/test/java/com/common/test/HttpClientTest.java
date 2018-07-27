package com.common.test;

import com.taotao.common.utils.HttpClientUtil;

public class HttpClientTest {
	
	public static void main(String[] args) {
		String result = HttpClientUtil.doGet("http://rest.taotao.com/rest/content/list/89");
		System.out.println(result);
	}
}
