package com.chejiawang.android.api;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Entity;

import com.chejiawang.android.app.AppContext;
import com.chejiawang.android.app.AppException;
import com.chejiawang.android.base.Urls;
import com.chejiawang.android.bean.CoachBasicInfo;

public class ApiClient {
	
	private static final int RETRY_TIME = 3;
	private static String appCookie;

	public static CoachBasicInfo login(AppContext appContext, String userName, String password) throws AppException{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username", userName);
		params.put("pwd", password);
		params.put("keep_login", 1);
		
		String loginurl = Urls.LOGIN_VALIDATE_HTTP;
		
		try{
			return null;//CoachBasicInfo.parse(http_post(appContext, loginurl, params, null));		
		}catch(Exception e){
			if(e instanceof AppException)
				throw (AppException)e;
			throw AppException.network(e);
		}
	}

	/**
	 * Post请求
	 * 
	 * @param appContext
	 * @param url
	 * @param params
	 * @param files
	 * @return
	 * @throws AppException
	 */
	private static InputStream http_post(AppContext appContext, String url,
			Map<String, Object> params, Map<String, File> files) throws AppException{
		String cookie = getCookie(appContext);
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpEntity httpEntry = null;
		HttpResponse httpResponse = null;
		String result = "";
		try {
			//将参数添加到mParList中
			List<NameValuePair> mParList = new ArrayList<NameValuePair>();
			httpPost = new HttpPost(url);
			if(params != null){
				for(String username: params.keySet()){
					NameValuePair nvp = new BasicNameValuePair(username, String.valueOf(params.get(username)));
					mParList.add(nvp);
				}
			}
			httpEntry = new UrlEncodedFormEntity(mParList, HTTP.UTF_8);
			httpPost.setEntity(httpEntry);
			if(cookie != null){
				httpPost.setHeader("Cookie", cookie);
			}
			httpPost.setHeader("Connection", "Keep-Alive");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int time = 0;
		do{
			try {
				httpClient = new DefaultHttpClient();
				httpResponse = httpClient.execute(httpPost);
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode != HttpStatus.SC_OK){
					throw AppException.http(statusCode);
				}else{
					String tempCookies = "";
					List<Cookie> cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
					for(Cookie ck : cookies){
						tempCookies += ck.toString() + ";";
					}
					
					if(appContext != null && tempCookies != ""){
						appContext.setProperty("cookie", tempCookies);
						appCookie = tempCookies;
					}
					
					result =EntityUtils.toString(httpResponse.getEntity());
					break;
				}
				
			} catch (ClientProtocolException e) {
				time ++;
				if(time <RETRY_TIME){
					try {
						Thread.sleep(1000);
					}catch(InterruptedException ex){}
					continue;
				}
				e.printStackTrace();
			} catch (IOException e) {
				time ++;
				if(time <RETRY_TIME){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
					}
					continue;
				}
				e.printStackTrace();
				throw AppException.network(e);
			}finally{
				//释放链接
				httpPost.abort();
				httpClient = null;
			}
			
		}while(time < RETRY_TIME);
		result = result.replaceAll("\\p{Cntrl}", "");
		return new ByteArrayInputStream(result.getBytes());
	}

	private static String getCookie(AppContext appContext) {
		if(appCookie == null || appCookie == "") {
			appCookie = appContext.getProperty("cookie");
		}
		return appCookie;
	}

}
