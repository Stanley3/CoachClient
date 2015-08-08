package com.chejiawang.android.api;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class VipStudentApi {
	public static final String TAG = "VipStudentApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "studentDepositRecord/getVIPStudentOfCoachInfo";
	public static void getVipStudent(int coach_id, int start, String sort,
			String dir, AsyncHttpResponseHandler handler)
	{
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		params.put("sort", sort);
		params.put("dir", dir);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		ApiHttpClient.get(PART_URL, params, handler);
	}

}
