package com.hesine.common.utils.pn;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.hesine.common.utils.HttpClientUtil;


public class PnNotify {
	
	private static String URI = "http://211.151.62.19:8080";
	//private static String URI = "http://172.27.251.245:8081";
	private static String userName="lottery_online";
	private static String password="!qa@ws#ed";
	private static String authType="basic"; // basic,digest 
		
	public static void initParameters(String URI,String userName,String password){
		PnNotify.URI = URI;
		PnNotify.userName = userName;
		PnNotify.password = password;
	}
	public static void initParameters(String URI,String userName,String password,String authType){
		PnNotify.URI = URI;
		PnNotify.userName = userName;
		PnNotify.password = password;
		PnNotify.authType = authType;
	}

	private static void messageQuery(int notifId) throws Exception {
		String url = URI+"/message/query";
		if(authType.equals("basic")){
			url = url+"/basic";
		}
		HttpQueryMessage queryMessage = new HttpQueryMessage();
		queryMessage.setNotifId(notifId);
//		Base64 base64 = new Base64();
//		String verify = userName+":"+password;
//		String encodeString = base64.encodeAsString(verify.getBytes());
//		Header header = new BasicHeader("Authorization",
//				"Basic "+encodeString);
		String postData = JSON.toJSONString(queryMessage);
//		System.out.println("postData:" + postData);
//		HttpResponse response = HttpClientUtil.getInstance().postMethod(url,
//				postData.getBytes(), header);
//		System.out.println("response is:" + response);
//		System.out.println("content:"+ EntityUtils.toString(response.getEntity()));
		new BasePush(url, userName, password).push(postData);				
	}

	public static int messagePush(HttpPushMessage message) throws Exception,IOException {
		String url = URI+"/message/push";
		if(authType.equals("basic")){
			url = url+"/basic";
		}
		String postData = JSON.toJSONString(message);
		System.out.println("postData:" + postData);
		
		String content = new BasePush(url, userName, password).push(postData);		
		return JSON.parseObject(content, ObjId.class).getNotifId();

	}

	/**
	 * @throws Exception
	 * @throws IOException
	 */
	public static int addGroup(RegGroup group) throws Exception, IOException {
		String url = URI+"/group/add";
		if(authType.equals("basic")){
			url = url+"/basic";
		}
		String postData = JSON.toJSONString(group);
		System.out.println("postData:" + postData);
		String content = new BasePush(url, userName, password).push(postData);		
		return JSON.parseObject(content, ObjId.class).getGroupId();
	}

}

class ObjId{
	private int groupId;
	private int notifId;
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return the notifId
	 */
	public int getNotifId() {
		return notifId;
	}
	
	/**
	 * @param notifId the notifId to set
	 */
	public void setNotifId(int notifId) {
		this.notifId = notifId;
	}
}