package com.chejiawang.android.api;

import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FinanceFreezeApi {

	public static final String TAG = "FinanceFreezeApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "orderRecord/getCoachIncomeRecord";
	public static void getFinanceFreeze(int coach_id, int order_status, int start, int length, AsyncHttpResponseHandler handler)
	{
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("order_status", order_status);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		ApiHttpClient.get(PART_URL, params, handler);
	}
}
