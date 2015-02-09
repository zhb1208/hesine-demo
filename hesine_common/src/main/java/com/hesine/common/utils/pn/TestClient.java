package com.hesine.common.utils.pn;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.hesine.common.utils.HttpClientUtil;


public class TestClient {
	private static String URI = "http://211.151.62.19:8080"; //"http://172.27.244.20:8081";
	public static void main(String[] args) throws Exception {
		/*for(int i=0; i<10; i++){
			int groupId = testGroupAdd();
			int notifId = testMessagePush(groupId);
			testMessageQuery(notifId);
		}*/
		//testMessageQuery(100);
		int groupId = testGroupAdd();
		System.out.println("groupId:"+groupId);
		int notifyId = testMessagePush(groupId);
		System.out.println("notifyId:"+notifyId);
		
		
	}

	private static void testMessageQuery(int notifId) throws Exception {
		String url = URI+"/message/query";
		HttpQueryMessage queryMessage = new HttpQueryMessage();
		queryMessage.setNotifId(notifId);
		Base64 base64 = new Base64();
		String encodeString = base64.encodeAsString("lottery_test:123456".getBytes());
		Header header = new BasicHeader("Authorization",
				"Basic "+encodeString);
		String postData = JSON.toJSONString(queryMessage);
		System.out.println("postData:" + postData);
		HttpResponse response = HttpClientUtil.getInstance().postMethod(url,
				postData.getBytes(), header);
		System.out.println("response is:" + response);
		System.out.println("content:"
				+ EntityUtils.toString(response.getEntity()));
	}

	private static int testMessagePush(int groupId) throws Exception,
			IOException {
		String url = URI+"/message/push";

		HttpPushMessage message = new HttpPushMessage();
		message.setExpiry(3000);
		message.setGroupId(groupId);
		message.setNotifyType(EnumConstants.BROADCAST);
		message.setPayload("test");
		message.setPnType(EnumConstants.HCPS);
		String postData = JSON.toJSONString(message);
//		System.out.println("postData:" + postData);
//		Header header = new BasicHeader("Authorization",
//				"Basic dGVzdElkOnRlc3Rwd2Q=");
//		HttpResponse response = HttpClientUtil.getInstance().postMethod(url,
//				postData.getBytes(), header);
//		System.out.println("response is:" + response);
//		String content = EntityUtils.toString(response.getEntity());
		String content = new BasePush(url, "lottery_online_digest", "!qa@ws#ed").push(postData);
		System.out.println("content:" + content);

//		message.setNotifyType(EnumConstants.UNICAST);
//		message.setPayload("test");
//		message.setRegId("ce1b2b5642cf210c627bc0f5099960d8ba5f7db0a6ae3716521bf0f4b08297e0");
//		message.setPnType(EnumConstants.APNS);
//		postData = JSON.toJSONString(message);
//		System.out.println("postData:" + postData);
//		response = HttpClientUtil.getInstance().postMethod(url,
//				postData.getBytes(), header);
//		System.out.println("response is:" + response);
//		System.out.println("content:"
//				+ EntityUtils.toString(response.getEntity()));
		//
//		System.out.println("NotifId:"
//				+ JSON.parseObject(content, ObjId.class).getNotifId());
		return JSON.parseObject(content, ObjId.class).getNotifId();

	}

	/**
	 * @throws Exception
	 * @throws IOException
	 */
	private static int testGroupAdd() throws Exception, IOException {
		String url = URI+"/group/add/";

		RegGroup group = new RegGroup();
		group.setName("group2");
		group.setDesc("second group");

		Set<String> registrationList = new HashSet<String>();
		registrationList.add("BFCBEF265F3AEE26F7657244");
		registrationList.add("BFCBEF26326FEE26F7657244");
		registrationList.add("BFCBEF264A3EEE26F7657244");
		registrationList.add("BFCBEF26B13EEE26F7657244");

		group.setRegIdList(registrationList);
		String postData = JSON.toJSONString(group);
		System.out.println("postData:" + postData);

//		Header header = new BasicHeader("Authorization",
//				"Basic dGVzdElkOnRlc3Rwd2Q=");
//		HttpResponse response = HttpClientUtil.getInstance().postMethod(url,
//				postData.getBytes(), header);
//		System.out.println("response is:" + response);
//		String content = EntityUtils.toString(response.getEntity());
//		System.out.println("content:" + content);
		String content = new BasePush(url, "lottery_online_digest", "!qa@ws#ed").push(postData);
		return JSON.parseObject(content, ObjId.class).getGroupId();
	}

}
