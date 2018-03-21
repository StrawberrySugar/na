package com.zizaihome.api.utils;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录
 
    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }
 
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setSocketTimeout(30000)//读取超时  
			.setConnectTimeout(30000)//连接超时
			            .build();
				
			CloseableHttpClient httpClient = HttpClients.custom()
						 .setDefaultRequestConfig(requestConfig)
						 .build();	
				
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);
			String result = null;
			CloseableHttpResponse response = null;
			try
			{
			   response = httpClient.execute(post);
			   int statecode = response.getStatusLine().getStatusCode();
			   if(statecode == 200 && !noNeedResponse){
			    	HttpEntity httpentity = response.getEntity(); 
			        if (httpentity != null){
		        		//服务器返回的JSON字符串，建议要当做日志记录起来
	        			result = EntityUtils.toString(httpentity);
		            }
		       }
			}
			catch (Exception e)
			{
				   e.printStackTrace();
			}
			finally{
				   try {
					   if(response!=null){
						   response.close();
					   }
				   } catch (IOException e) {
					   e.printStackTrace();
				   }
				   try {
					   post.abort();
				   } catch (Exception e) {
					   e.printStackTrace();
				   }
				   try {
					   httpClient.close();
				   } catch (IOException e) {
					   e.printStackTrace();
				   }
			}
			return JSONObject.fromObject(result); 
    }
    
    public static JSONObject httpPostFormData(String url, JSONObject jsonParam) {  
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setSocketTimeout(30000)//读取超时  
		.setConnectTimeout(30000)//连接超时
		            .build();
			
		CloseableHttpClient httpClient = HttpClients.custom()
					 .setDefaultRequestConfig(requestConfig)
					 .build();	
			
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();  
        Iterator iterator = jsonParam.keys();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			formparams.add(new BasicNameValuePair(key, jsonParam.getString(key))); 
		}
		  

		        
		HttpPost post = new HttpPost(url);
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			post.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String result = null;
		CloseableHttpResponse response = null;
		try
		{
		   response = httpClient.execute(post);
		   int statecode = response.getStatusLine().getStatusCode();
		   if(statecode == 200){
		    	HttpEntity httpentity = response.getEntity(); 
		        if (httpentity != null){
	        		//服务器返回的JSON字符串，建议要当做日志记录起来
        			result = EntityUtils.toString(httpentity);
	            }
	       }
		}
		catch (Exception e)
		{
			   e.printStackTrace();
		}
		finally{
			   try {
				   if(response!=null){
					   response.close();
				   }
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
			   try {
				   post.abort();
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
			   try {
				   httpClient.close();
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
		}
		return JSONObject.fromObject(result); 
    }  
 
 
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
    	
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setSocketTimeout(30000)//读取超时  
		.setConnectTimeout(30000)//连接超时
		            .build();
			
		CloseableHttpClient httpClient = HttpClients.custom()
					 .setDefaultRequestConfig(requestConfig)
					 .build();	
		HttpGet get = new HttpGet(url);
		get.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
		get.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
		String result = null;
		CloseableHttpResponse response = null;
		try
		{
		   response = httpClient.execute(get);
		   int statecode = response.getStatusLine().getStatusCode();
		   if(statecode == 200){
		    	HttpEntity httpentity = response.getEntity(); 
		        if (httpentity != null){
	        		//服务器返回的JSON字符串，建议要当做日志记录起来
        			result = EntityUtils.toString(httpentity);
	            }
	       }
		}
		catch (Exception e)
		{
			   e.printStackTrace();
		}
		finally{
			   try {
				   if(response!=null){
					   response.close();
				   }
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
			   try {
				   get.abort();
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
			   try {
				   httpClient.close();
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
		}
		return JSONObject.fromObject(result); 
    }
    
}