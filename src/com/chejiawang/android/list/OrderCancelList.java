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

public class OrderCancelList extends Entity implements ListEntity {
	public static String TAG = "HaveOrderList";
	private List<CoachPrecontractRecordVo> orderCancelList = new ArrayList<CoachPrecontractRecordVo>();


	
	public List<CoachPrecontractRecordVo> getOrderCancelList() {
		return orderCancelList;
	}

	public void setOrderCancelList(List<CoachPrecontractRecordVo> orderCancelList) {
		this.orderCancelList = orderCancelList;
	}

	public static OrderCancelList parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);

		//Logger.e(TAG, "Response:" + result);
		if (result == null) {
			return null;
		}
		OrderCancelList list = new OrderCancelList();
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
				// record.setSchool_address(recordJson.getString("school_address"));
				
				//添加取消预约的解析 2015.6.28 孙晓雨 
				list.getOrderCancelList().add(record);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return list;

	}

	@Override
	public List<?> getList() {

		return orderCancelList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
