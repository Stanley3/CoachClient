package com.chejiawang.android.api;

import com.chejiawang.android.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 用来解析评价列表
 * 
 * @author Administrator
 *
 */
public class EvaluateApi {
	public static final String TAG = "EvaluateApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "evaluationRecord/getCoachEvaluationInfo";

	public static void getEvalution(int coach_id, int start, String sort,
			int evaluation, String dir, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("coach_id", coach_id);
		if (evaluation != -1) {
			params.put("evaluation", evaluation);
		}
		params.put("sort", sort);
		params.put("dir", dir);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		ApiHttpClient.get(PART_URL, params, handler);
	}
}
