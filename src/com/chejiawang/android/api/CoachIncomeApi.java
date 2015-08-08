package com.chejiawang.android.api;

import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class CoachIncomeApi {

	public static final String TAG = "CoachIncomeApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "orderRecord/getCoachIncomeRecord";
	public static void getCoachIncome(int coach_id, int start, int order_status,int length, AsyncHttpResponseHandler handler)
	{
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("start", start);
		params.put("length", 10);
		params.put("order_status", 3);
		ApiHttpClient.get(PART_URL, params, handler);
	}

}
