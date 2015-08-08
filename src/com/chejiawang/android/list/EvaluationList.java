package com.chejiawang.android.list;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.bean.EvaluationAndOrderInfo;
import com.chejiawang.android.utils.ListEntity;
import com.chejiawang.android.utils.StringUtils;

public class EvaluationList{
	public static String TAG = "EvaluationList";
	private MyEvalution evalution = new MyEvalution();
	
	public ArrayList<EvaluationAndOrderInfo> getEvaluationList() {
		return evalution.getEvaluationList();
	}

	public static MyEvalution parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);
		//Logger.e(TAG, result);
		if (result == null) {
			return null;
		}
		MyEvalution evalution = new MyEvalution();
		try {
			JSONObject dataObject = new JSONObject(result);
			String totalEvalutionSize = dataObject.getString("total");
			evalution.setTotal(totalEvalutionSize);
			String goodEvalutionSize = dataObject.getString("goodEvaluationSize");
			evalution.setGoodEvaluation(goodEvalutionSize);
			String badEvalutionSize = dataObject.getString("badEvaluationSize");
			evalution.setBadEvaluation(badEvalutionSize);
			Integer midEvalutionSize = Integer.parseInt(totalEvalutionSize)
					- Integer.parseInt(goodEvalutionSize)
					- Integer.parseInt(badEvalutionSize);
			evalution.setMidEvaluation(midEvalutionSize.toString());

			ArrayList<EvaluationAndOrderInfo> mList = new ArrayList<EvaluationAndOrderInfo>();
			JSONArray jsonArry = dataObject.getJSONArray("data");
			
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				EvaluationAndOrderInfo record = new EvaluationAndOrderInfo();
				record.setEvaluation(recordJson.getString("evaluation"));
				record.setSkill(recordJson.getString("skill"));
				record.setService_attitude(recordJson.getString("service_attitude"));
				record.setHygiene(recordJson.getString("hygiene"));
				record.setContent(recordJson.getString("content"));
				record.setEvaluation_time(recordJson.getString("evaluation_time"));
				record.setOrder_status(recordJson.getString("course_status"));
				mList.add(record);
			}
			evalution.setEvaluationList(mList);
			Logger.e(TAG, "parse success!!");
		} catch (JSONException e) {
			Logger.e(TAG, "parse error!!");
			e.printStackTrace();
			return null;
		}
		return evalution;
	}

}
