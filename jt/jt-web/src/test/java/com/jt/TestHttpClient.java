package com.jt;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	/*
	 * 编码思路:
	 * 1.创建工具API对象
	 * 2.定义远程url地址
	 * 3.定义请求类型对象
	 * 4.发起http请求,获取响应结果
	 * 5.从响应对象中获取数据
	 */
	@Test
	public void testGet() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		String url="https://item.jd.com/27421175304.html";
		HttpGet get =new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		//判断响应是否正确
		if (response.getStatusLine().getStatusCode()==200) {
			//从响应中获取数据
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		}else {
			System.out.println("请稍后重试");
		}
		
	}
}
