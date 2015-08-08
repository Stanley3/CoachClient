package com.chejiawang.android.list;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.app.Logger;
import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.bean.Entity;
import com.chejiawang.android.utils.ListEntity;
import com.chejiawang.android.utils.StringUtils;

public class PracticeFinishList extends Entity implements ListEntity {
	public static String TAG = "PracticeFinishList";
	private List<CoachPrecontractRecordVo> practiceFinishList = new ArrayList<CoachPrecontractRecordVo>();

	
	public List<CoachPrecontractRecordVo> getPracticeFinishList() {
		return practiceFinishList;
	}

	public void setPracticeFinishList(
			List<CoachPrecontractRecordVo> practiceFinishList) {
		this.practiceFinishList = practiceFinishList;
	}

	public static PracticeFinishList parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);
		//Logger.e(TAG, "Response:" + result);
		if (result == null) {
			return null;
		}
		PracticeFinishList list = new PracticeFinishList();
		try {
			JSONArray jsonArry = new JSONArray(result);
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				CoachPrecontractRecordVo record = new CoachPrecontractRecordVo();
				record.setStudent_name(recordJson.getString("student_name"));
				record.setOrder_time(recordJson.getString("order_time"));
				record.setCourse_status(recordJson.getString("course_status"));
				record.setTraining_start_time(recordJson.getString("training_start_time"));
				record.setTraining_end_time(recordJson.getString("training_end_time"));
				//需要添加学员的练习状态 2015.6.28 孙晓雨
				list.getPracticeFinishList().add(record);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return list;

	}

	@Override
	public List<?> getList() {

		return practiceFinishList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
