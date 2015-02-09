package com.hesine.common.utils.pn;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

public class HttpClientUtil {

	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

	public static synchronized CloseableHttpClient getHttpClient(String host, int port){
		HttpHost targetHost = new HttpHost(host,port);
		cm.setMaxTotal(200); // 连接池里的最大连接数
        cm.setDefaultMaxPerRoute(50); // 每个路由的默认最大连接数
        cm.setMaxPerRoute(new HttpRoute(targetHost), 50);
        
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
	        public boolean retryRequest(
	                IOException exception,
	                int executionCount,
	                HttpContext context) {
	            if (executionCount >= 5) {
	                // 如果已经重试了5次，就放弃
	                return false;
	            }
	            return true;
	        }

	    }; 
	    CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm)
				.setRetryHandler(myRetryHandler)
				.build();
		
	    return httpClient;
	}
	
}
