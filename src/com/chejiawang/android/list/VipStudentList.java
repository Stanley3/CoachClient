package com.chejiawang.android.list;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.bean.EvaluationAndOrderInfo;
import com.chejiawang.android.bean.VIPStudentOfCoachInfo;
import com.chejiawang.android.utils.ListEntity;
import com.chejiawang.android.utils.StringUtils;

public class VipStudentList extends Entity implements ListEntity {
	/**
	 * 
	 */
	public static String TAG = "VipStudentList";
	private static final long serialVersionUID = 1L;
	ArrayList<VIPStudentOfCoachInfo> vipStudentList;
	
	public ArrayList<VIPStudentOfCoachInfo> getVipStudentList() {
		return vipStudentList;
	}

	public void setVipStudentList(ArrayList<VIPStudentOfCoachInfo> vipStudentList) {
		this.vipStudentList = vipStudentList;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return vipStudentList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public static VipStudentList parse(InputStream inputStream) throws Exception
	{
		String result = StringUtils.inStream2String(inputStream);
		//Logger.e(TAG, result);
		if (result == null) {
			return null;
		}
		VipStudentList studentList = new VipStudentList();
		try {
			
			Logger.i(TAG, result);
			JSONObject dataObject = new JSONObject(result);
			ArrayList<VIPStudentOfCoachInfo> mList = new ArrayList<VIPStudentOfCoachInfo>();
			JSONArray jsonArry = dataObject.getJSONArray("data");
			
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				VIPStudentOfCoachInfo oneRecord = new VIPStudentOfCoachInfo();
				oneRecord.setBegin_vip_date(recordJson.getString("begin_vip_date"));
//				oneRecord.setCoach_id(recordJson.getInt("coach_id"));
				oneRecord.setStudent_id(recordJson.getInt("student_id"));
//				oneRecord.setStudent_level(recordJson.getInt("student_level"));
				oneRecord.setStudent_name(recordJson.getString("student_name"));
				oneRecord.setStudent_phone(recordJson.getString("student_phone"));
				oneRecord.setTraining_times(recordJson.getInt("training_times"));
				mList.add(oneRecord);
			}
			studentList.setVipStudentList(mList);
			Logger.e(TAG, "parse success!!");
		} catch (JSONException e) {
			Logger.e(TAG, "parse error!!");
			e.printStackTrace();
			return null;
		}
		return studentList;
	}
}
