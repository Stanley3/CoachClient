package com.chejiawang.android.list;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.bean.CoachTeachRecord;
import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.bean.VIPStudentOfCoachInfo;
import com.chejiawang.android.utils.ListEntity;
import com.chejiawang.android.utils.StringUtils;

public class CoachTeachRecordList extends Entity implements ListEntity{
	public static String TAG = "CoachTeachRecordList";
	ArrayList<CoachTeachRecord> coachTeachRecordList;
	
	
	public static String getTAG() {
		return TAG;
	}

	public static void setTAG(String tAG) {
		TAG = tAG;
	}


	public ArrayList<CoachTeachRecord> getCoachTeachRecordList() {
		return coachTeachRecordList;
	}

	public void setCoachTeachRecordList(ArrayList<CoachTeachRecord> coachTeachRecordList) {
		this.coachTeachRecordList = coachTeachRecordList;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return coachTeachRecordList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static CoachTeachRecordList parse(InputStream inputStream) throws Exception
	{
		String result = StringUtils.inStream2String(inputStream);
		//Logger.e(TAG, result);
		if (result == null) {
			return null;
		}
		CoachTeachRecordList coachRecordList = new CoachTeachRecordList();
		try {
			
			Logger.i(TAG, result);
			JSONObject dataObject = new JSONObject(result);
			ArrayList<CoachTeachRecord> mList = new ArrayList<CoachTeachRecord>();
			JSONArray jsonArry = dataObject.getJSONArray("data");
			
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				CoachTeachRecord oneRecord = new CoachTeachRecord();
//				oneRecord.setCoach_id(recordJson.getInt("coach_id"));
				oneRecord.setCourse_status(recordJson.getInt("course_status"));
				oneRecord.setStudent_id(recordJson.getInt("student_id"));
				oneRecord.setStudent_level(recordJson.getInt("student_level"));
				oneRecord.setStudent_name(recordJson.getString("student_name"));
				oneRecord.setStudent_phone(recordJson.getString("student_phone"));
				mList.add(oneRecord);
			}
			coachRecordList.setCoachTeachRecordList(mList);
			Logger.e(TAG, "parse success!!");
		} catch (JSONException e) {
			Logger.e(TAG, "parse error!!");
			e.printStackTrace();
			return null;
		}
		return coachRecordList;
	}
}
