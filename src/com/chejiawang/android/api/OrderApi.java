package com.chejiawang.android.api;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 用来解析预约列表
 * 
 * @author Administrator
 *
 */
public class OrderApi {
	public static final String TAG = "OrderApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "orderRecord/getCoachPrecontractedRecord";
	public static void getOrderList(int coach_id, int start, String sort, int order_status,
			String dir, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("order_time", dir);
		params.put("order_status", order_status);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		ApiHttpClient.get(PART_URL, params, handler);
	}
	
	public static void getPracticeFinishList(int coach_id, int start, String sort, int order_status,
			String dir, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("sort", sort);
		params.put("dir", dir);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		params.put("order_status", order_status);
		ApiHttpClient.get(PART_URL, params, handler);
	}
	
	public static void getOrderCancelList(int coach_id, int start, String sort, int order_status,
			String dir, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("sort", sort);
		params.put("dir", dir);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		params.put("order_status", order_status);
		ApiHttpClient.get(PART_URL, params, handler);
	}
}
