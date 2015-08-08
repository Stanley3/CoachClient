package com.chejiawang.android.adapter;

import com.chejiawang.android.adapter.VipStudentAdapter.ViewHolder;
import com.chejiawang.android.bean.CoachTeachRecord;
import com.chejiawang.android.bean.VIPStudentOfCoachInfo;
import com.chejiawang.android.ui.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CoachTeachRecordAdapter extends RecycleBaseAdapter {

	public CoachTeachRecordAdapter() {
		super();
	}

	public CoachTeachRecordAdapter(View headerView) {
		mHeaderView = headerView;
	}

	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.item_teach_record, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}

	@Override
	protected void onBindItemViewHolder(com.chejiawang.android.adapter.RecycleBaseAdapter.ViewHolder vh, int position) {
		// TODO Auto-generated method stub
		CoachTeachRecordAdapter.ViewHolder holder = (ViewHolder) vh;
		CoachTeachRecord coachTeachRecord = (CoachTeachRecord) _data.get(position);
		holder.tv_name.setText(coachTeachRecord.getStudent_name());
		// holder.tv_order_subject.setText(studentInfo.getStudent_level());
		holder.tv_teach_day.setText("开始时间:" + coachTeachRecord.getTraining_start_time());
		holder.tv_teach_time
				.setText(coachTeachRecord.getTraining_start_time() + "-" + coachTeachRecord.getTraining_end_time());
		int orderStatus = coachTeachRecord.getCourse_status();
		String strOrderStutus = "未报名";
		switch(orderStatus)
		{
		case 1:
			strOrderStutus = "科目一";
			break;
		case 2:
			strOrderStutus = "科目二";
			break;
		case 3:
			strOrderStutus = "科目三";
			break;
		case 4:
			strOrderStutus = "科目四";
			break;
		case 5:
			strOrderStutus = "拿证";
			break;
		}
		
		holder.tv_order_subject.setText(strOrderStutus);
	}

	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder {
		public TextView tv_name;
		public TextView tv_order_subject;
		public TextView tv_teach_day;
		public TextView tv_teach_time;
		public ImageView iv_order_type;
		public ImageView iv_cellphone;

		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_order_subject = (TextView) view.findViewById(R.id.tv_order_subject);
			tv_teach_day = (TextView) view.findViewById(R.id.tv_teach_day);
			tv_teach_time = (TextView) view.findViewById(R.id.tv_teach_time);
			iv_order_type = (ImageView) view.findViewById(R.id.iv_order_type);
			iv_cellphone = (ImageView) view.findViewById(R.id.iv_cellphone);
		}

	}

}
