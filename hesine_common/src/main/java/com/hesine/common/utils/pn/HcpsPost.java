/**
 * 
 */
package com.hesine.common.utils.pn;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author wanghua
 *
 */
public class HcpsPost {
	private BasePush pushObj;
	private String postData;
	private HttpPost httppost;
	HttpClientContext context;
	
	public static HcpsPost createPost(BasePush pushObj, String postData) throws MalformedURLException{
		HcpsPost hcpsPost = new HcpsPost(pushObj, postData);
		hcpsPost.init();
		return hcpsPost;
	}
	
	private HcpsPost(BasePush pushObj, String postData){
		this.pushObj = pushObj;
		this.postData = postData;
	}
	
	private void init() throws MalformedURLException{
		String host = pushObj.getHost();
		int port = pushObj.getPort();
		httppost = new HttpPost(pushObj.getRequestUrl());
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(120000).setConnectTimeout(60000).build();
		httppost.setConfig(requestConfig);
		httppost.setEntity(new StringEntity(postData,ContentType.APPLICATION_JSON));
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(new AuthScope(host, port),
				new UsernamePasswordCredentials(pushObj.getCpId(), pushObj.getCpPwd()));
		context = HttpClientContext.create();
		context.setCredentialsProvider(credentialsProvider);
	}
	
	
	public CloseableHttpResponse execute(CloseableHttpClient httpClient) throws ClientProtocolException, IOException{
		 return httpClient.execute(httppost, context);
	}

}
