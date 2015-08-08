package com.chejiawang.android.list;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.bean.CoachIncomeRecord;
import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.utils.ListEntity;
import com.chejiawang.android.utils.StringUtils;

public class CoachIncomeList extends Entity implements ListEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TAG = "CoachIncomeList";
	ArrayList<CoachIncomeRecord> coachList;
	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return coachList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public ArrayList<CoachIncomeRecord> getCoachList() {
		return coachList;
	}

	public void setCoachList(ArrayList<CoachIncomeRecord> coachList) {
		this.coachList = coachList;
	}

	public static CoachIncomeList parse(InputStream inputStream) throws Exception
	{
		String result = StringUtils.inStream2String(inputStream);
		Logger.i(TAG, result);
		if (result == null) {
			return null;
		}
		CoachIncomeList list = new CoachIncomeList();
		try {
			JSONObject dataObject = new JSONObject(result);
			ArrayList<CoachIncomeRecord> mList = new ArrayList<CoachIncomeRecord>();
			JSONArray jsonArry = dataObject.getJSONArray("data");
			
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				CoachIncomeRecord oneRecord = new CoachIncomeRecord();
//				oneRecord.setCoach_id(recordJson.getInt("coach_id"));
				oneRecord.setCourse_status(recordJson.getInt("course_status"));
				oneRecord.setOrder_amount(recordJson.getDouble("order_amount"));
				oneRecord.setOrder_dead_time(recordJson.getString("order_dead_time"));
//				oneRecord.setOrder_freeze_time(recordJson.getString("begin_vip_date"));
				oneRecord.setOrder_id(recordJson.getInt("order_id"));
				oneRecord.setStudent_level(recordJson.getInt("student_level"));
				oneRecord.setStudent_name(recordJson.getString("student_name"));
				oneRecord.setStudent_phone(recordJson.getString("student_phone"));
				mList.add(oneRecord);
			}
			list.setCoachList(mList);
			Logger.e(TAG, "parse success!!");
		} catch (JSONException e) {
			Logger.e(TAG, "parse error!!");
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public static String getTAG() {
		return TAG;
	}

	public static void setTAG(String tAG) {
		TAG = tAG;
	}

		
	
}
