package com.db.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class HttpClientService {

	@Autowired
	private CloseableHttpClient httpClient;
	@Autowired
	private RequestConfig requestConfig;

	public String doGet(String url, Map<String, String> params, String charset) {
		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		if (params != null) {
			url += "?";
			for (Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				url = url + key + "=" + value + "&";
			}
			url = url.substring(0, url.length() - 1);// 去掉最后一个&
		}
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);// 定义请求超时时间
		String result = null;
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), charset);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String doGet(String url) {
		return doGet(url, null, null);
	}

	public String doGet(String url, String charset) {
		return doGet(url, null, charset);
	}

	public String doGet(String url, Map<String, String> params) {
		return doGet(url, params, null);
	}

	public String doPost(String url, Map<String, String> params, String charset) {
		String result = null;
		HttpPost post = new HttpPost(url);
		post.setConfig(requestConfig);
		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		if (params != null) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(parameters, charset);
				post.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			CloseableHttpResponse execute = httpClient.execute(post);
			if (execute.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(execute.getEntity(), charset);
			} else {
				System.out.println("获取状态码信息:" + execute.getStatusLine().getStatusCode());
				throw new RuntimeException();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String doPost(String url, Map<String, String> params) {
		return doPost(url, params, null);
	}

	public String doPost(String url, String charset) {
		return doPost(url, null, charset);
	}

	public String doPost(String url) {
		return doPost(url, null, null);
	}
}
