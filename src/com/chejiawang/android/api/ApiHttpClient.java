package com.chejiawang.android.api;

import java.util.Locale;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;

import com.chejiawang.android.app.AppContext;
import com.chejiawang.android.app.Logger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApiHttpClient {

	public static String TAG = "ApiHttpClient";
	private static String API_URL;
	/**
	 * 初始的URL 非常重要
	 */
	public static String DEV_API_URL = "http://120.26.61.165/DTAServer/rest/%s";  //需要些的URL
	
	
	public static AsyncHttpClient client;
	public final static String HOST = "120.26.61.165"; //主机名，待添加
	private static String appCookie;
	
	public static String getAppCookie() {
		return appCookie;
	}

	public static void setAppCookie(String appCookie) {
		ApiHttpClient.appCookie = appCookie;
	}

	static{
		API_URL = DEV_API_URL;
	}
	
	public static void setHttpClient(AsyncHttpClient c){
		client = c;
		client.addHeader("Accept-Language", Locale.getDefault().toString());
		client.addHeader("Host", HOST );
		client.addHeader("Connection", "Keep-Alive");
		setUserAgent(ApiClientHelper.getUserAgent(AppContext.instance()));
	}

	public static void setUserAgent(String userAgent) {
		client.setUserAgent(userAgent);
	}

	public static String getCookie(AppContext appContext) {
		if(appCookie == null || appCookie == ""){
			appCookie = AppContext.getCookie();
		}
		return appCookie;
	}
	public static void cleanCookie(){
		appCookie = "";
	}
	public static void setCookie(String cookie){
		client.addHeader("Cookie", cookie);
	}
	
	public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler handler){
		Logger.e(TAG, getAbsoluteApiUrl(partUrl));
//	    CookieStore cookies = (CookieStore) (client.getHttpContext().getAttribute(ClientContext.COOKIE_STORE));//获取AsyncHttpClient中的CookieStore
//        if(cookies!=null){
//            for(Cookie c: cookies.getCookies()){
//            	
//               Logger.e(TAG,c.getName() + "=" +c.getValue());
//            }
//        }else{
//        	Logger.e(TAG,"session is null");
//        }
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
	}

	public static void post(String partUrl, RequestParams params, AsyncHttpResponseHandler handler){
		//Logger.e(TAG, getAbsoluteApiUrl(partUrl));
		client.post(getAbsoluteApiUrl(partUrl), params, handler);
		
	}
	public static String getAbsoluteApiUrl(String partUrl) {
		String url = String.format(API_URL, partUrl);
		//Logger.e(TAG, "request: " + url);
		return url;
	}
}
