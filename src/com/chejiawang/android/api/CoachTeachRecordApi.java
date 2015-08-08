package com.chejiawang.android.api;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class CoachTeachRecordApi {

	public static final String TAG = "CoachTeachRecordApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "orderRecord/getCoachTeachRecord";

	public static void getCoachTeachRecord(int coach_id, int start,int length,
			AsyncHttpResponseHandler handler) {
		Logger.i(TAG, "getCoachTeachRecord");
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		ApiHttpClient.get(PART_URL, params, handler);
	}

}
