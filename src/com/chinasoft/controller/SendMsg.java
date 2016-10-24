package com.chinasoft.controller;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg {

	public static void main(String[] args) throws Exception {
		
		
	}
	public static String init(String phonenum, String str) throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
//		post.addRequestHeader("Content-Type",
//				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
//		NameValuePair[] data = { new NameValuePair("Uid", "wq568827315"),
//		new NameValuePair("Key", "wq19950229"),
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "whr1803881366"),

				new NameValuePair("Key", "whr1803881366"),
				
				new NameValuePair("smsMob", phonenum),
				new NameValuePair("smsText", str) };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		
		System.out.println(phonenum);
		System.out.println(str);
		
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();
		
		return result;
	}

}