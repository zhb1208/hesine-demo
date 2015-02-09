package com.hesine.common.utils;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	private PoolingHttpClientConnectionManager cm;
	private HttpClient httpClient;

	public static HttpClientUtil getInstance() {
		return new HttpClientUtil();
	}

	private HttpClientUtil() {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	/**
	 * post请求到服务器
	 * 
	 * @param ip
	 * @param port
	 * @param path
	 * @param params
	 * @throws Exception
	 */
	public HttpResponse postMethod(String url, byte[] data, Header... headers) throws Exception {
		HttpPost httppost = new HttpPost(url);
		httppost.setHeaders(headers);
		HttpResponse response = null;
		try {
			ByteArrayEntity byteArrayEntity = new ByteArrayEntity(data);
			httppost.setEntity(byteArrayEntity);
			response = httpClient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpOptions httpOptions = new HttpOptions("http://www.baidu.com/");
		HttpResponse httpResponse = getInstance().httpClient.execute(httpOptions);
		System.out.println(EntityUtils.toString(httpResponse.getEntity()));
	}
	
}
