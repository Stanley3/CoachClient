package com.chejiawang.android.api;

import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TurnApi {

	public static final String TAG = "OrderApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "scheduleInfo/getAll";
	
	public static void getTurnInfo(int coach_id, String startDate, String endDate, AsyncHttpResponseHandler handler){
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("start_schedule_date", startDate);
		params.put("end_schedule_date", endDate);
		ApiHttpClient.get(PART_URL, params, handler);
	}
}
