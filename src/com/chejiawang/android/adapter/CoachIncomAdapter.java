package com.chejiawang.android.adapter;

import com.chejiawang.android.adapter.VipStudentAdapter.ViewHolder;
import com.chejiawang.android.bean.CoachIncomeRecord;
import com.chejiawang.android.bean.VIPStudentOfCoachInfo;
import com.chejiawang.android.ui.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CoachIncomAdapter extends RecycleBaseAdapter {

	public CoachIncomAdapter() {
		super();
	}

	public CoachIncomAdapter(View headerView) {
		mHeaderView = headerView;
	}

	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.item_coach_income, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}

	@Override
	protected void onBindItemViewHolder(com.chejiawang.android.adapter.RecycleBaseAdapter.ViewHolder vh, int position) {
		// TODO Auto-generated method stub
		CoachIncomAdapter.ViewHolder holder = (ViewHolder) vh;
		CoachIncomeRecord coachIncomeRecord = (CoachIncomeRecord) _data.get(position);
		holder.tv_name.setText(coachIncomeRecord.getStudent_name());
		// holder.tv_order_subject.setText(studentInfo.getStudent_level());
		holder.tv_income_day.setText("收入日期:" + coachIncomeRecord.getOrder_dead_time());
		holder.tv_income.setText("+" + coachIncomeRecord.getOrder_amount());
		int orderStatus = coachIncomeRecord.getCourse_status();
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
		public TextView tv_income_day;
		public TextView tv_income;
		public ImageView iv_order_type;
		public ImageView iv_cellphone;

		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_order_subject = (TextView) view.findViewById(R.id.tv_order_subject);
			tv_income_day = (TextView) view.findViewById(R.id.tv_income_day);
			tv_income = (TextView) view.findViewById(R.id.tv_income);
			iv_order_type = (ImageView) view.findViewById(R.id.iv_order_type);
			iv_cellphone = (ImageView) view.findViewById(R.id.iv_cellphone);
		}

	}

}
