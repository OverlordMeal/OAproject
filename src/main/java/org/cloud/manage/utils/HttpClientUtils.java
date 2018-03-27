package org.cloud.manage.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpClientUtils {
	public static String doGet(String url,Map<String, String> paramMap){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(url.trim());
			if (paramMap != null) {
				for (String key : paramMap.keySet()) {
					uriBuilder.addParameter(key, paramMap.get(key));
				}
			}
			URI uri = uriBuilder.build();
			HttpGet httpGet = new HttpGet(uri);
			//添加请求头，模拟浏览器，避免403
			httpGet.addHeader("Accept", "text/html");
			httpGet.addHeader("Accept-Charset", "utf-8");
			httpGet.addHeader("Accept-Encoding", "gzip");
			httpGet.addHeader("Accept-Language", "en-US,en");
			httpGet.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally{
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
			
		}
		return result;
	}
	public static String doGet(String url){
		return doGet(url, null);
	}
	
	public static String doPost(String url,Map<String, String> paramMap){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			if (paramMap != null) {
				for (String key : paramMap.keySet()) {
					list.add(new BasicNameValuePair(key, paramMap.get(key)));
				}
			}
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"utf-8");
			httpPost.setEntity(urlEncodedFormEntity);
			response = httpClient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
		return result;
	}
	public static String doPost(String url){
		return doPost(url, null);
	}
	//当参数是json数据时
	public static String doPostWithJSON(String url,String Json){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			//使用json参数构建字符实体
			StringEntity stringEntity = new StringEntity(Json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(),"utf-8");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally{
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String url = "http://message.d17.cc:8080/d17-send-message/api/message/sendMsg";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("phone", "18879129328");
		paramMap.put("content", "逢中秋之际，祝[linkMan]位高权重责任轻，钱多事少离家近，每天睡到自然醒，工资数到手抽筋，奖金多到车来运，别人加班您加薪！ ");
		Map<String, String> map = JSON.parseObject(doPost(url, paramMap),Map.class);
		System.out.println(map.get("message"));
	}
}
