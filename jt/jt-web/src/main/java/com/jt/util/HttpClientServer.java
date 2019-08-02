package com.jt.util;

import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

@Service
public class HttpClientServer {
	@Autowired
	private CloseableHttpClient httpClient;
	@Autowired
	private RequestConfig requestConfig;
	/*
	 * 参数说明:
	 * 1.url地址
	 * 2.用户使用的参数用map封装
	 * 3.指定编码的格式
	 * 
	 * 步骤:
	 * 1.校验字符集,如果字符集为空 设定一个默认值
	 * 2.校验params是否为null
	 * null:表示用户get请求无需传参
	 * !null:需要传参,get请求
	 * 3.发起http的get请求
	 * 
	 */
	public String doGet(String url,Map<String, String> params,String charset) {
		//1.校验字符集
		if (StringUtils.isEmpty(charset)) {
			charset="UTF-8";
		}
		//2.校验参数是否为null
		if (params!=null) {
			url+="?";
			//2.1遍历map集合
			for (Map.Entry<String, String> entry:params.entrySet()) {
				String key=entry.getKey();
				String value=entry.getValue();
				url=url+key+"="+value+"&";
			}
			//2.2经过遍历循环最终url多个&
			url=url.substring(0,url.length()-1);
		}
		//3.发起get请求
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);//定义请求超时时间
		String result = null;
		try {
			CloseableHttpResponse response = 
					httpClient.execute(get);

			if(response.getStatusLine().getStatusCode()==200) {
				result = EntityUtils.toString(response.getEntity(),charset);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;
	}
	public String doGet(String url) {
		return doGet(url, null, null);
	}

	public String doGet(String url,Map<String,String> params) {
		return doGet(url, params, null);
	}

}
