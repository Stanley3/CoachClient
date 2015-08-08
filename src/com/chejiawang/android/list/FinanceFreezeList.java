package com.chejiawang.android.list;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.bean.CoachIncomeRecord;
import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.utils.ListEntity;
import com.chejiawang.android.utils.StringUtils;

public class FinanceFreezeList extends Entity implements ListEntity{
	public static String TAG = "FinanceFreezeList";
	private List<CoachIncomeRecord> coachIncomeRecordList = new ArrayList<CoachIncomeRecord>();
	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return coachIncomeRecordList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressLint("NewApi")
	public static FinanceFreezeList parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);

		Logger.i(TAG, result);
		if (result == null) {
			return null;
		}
		FinanceFreezeList list = new FinanceFreezeList();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArry = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				CoachIncomeRecord record = new CoachIncomeRecord();
				record.setCoach_id(recordJson.getInt("coach_id"));
				record.setStudent_id(recordJson.getInt("student_id"));
				record.setOrder_amount(recordJson.getDouble("order_amount"));
//				record.setOrder_dead_time(recordJson.getString("order_dead_time"));
				record.setCourse_status(recordJson.getInt("course_status"));
				record.setOrder_freeze_time(recordJson.getString("order_freeze_time"));
				record.setOrder_id(recordJson.getInt("order_id"));
				record.setStudent_level(recordJson.getInt("student_level"));
				record.setStudent_name(recordJson.getString("student_name"));
//				record.setStudent_phone(recordJson.getString("student_phone"));
				list.coachIncomeRecordList.add(record);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}
}
