package com.chejiawang.android.adapter;

import com.chejiawang.android.adapter.AllEvaluateRecycleAdapter.ViewHolder;
import com.chejiawang.android.bean.CoachIncomeRecord;
import com.chejiawang.android.bean.VIPStudentOfCoachInfo;
import com.chejiawang.android.ui.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FinanceFreeze extends RecycleBaseAdapter {

	private Context context;

	public FinanceFreeze(Context context) {
		super();
		this.context = context;
	}

	public FinanceFreeze(View headerView) {
		mHeaderView = headerView;
	}

	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.item_finance_freeze, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}

	@Override
	protected void onBindItemViewHolder(com.chejiawang.android.adapter.RecycleBaseAdapter.ViewHolder vh, int position) {
		FinanceFreeze.ViewHolder holder = (ViewHolder) vh;
		CoachIncomeRecord coachIncomeRecord = (CoachIncomeRecord) _data.get(position);
		
		holder.tv_name.setText(coachIncomeRecord.getStudent_name());
//		holder.tv_order_subject.setText(coachIncomeRecord.getStudent_level());
		holder.tv_day.setText("冻结时间：" + coachIncomeRecord.getOrder_freeze_time());
		holder.tv_freeze_count.setText(String.valueOf(coachIncomeRecord.getOrder_amount()));
		holder.tv_reason.setText("冻结原因：未通过");

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
		public TextView tv_day;
		public TextView tv_reason;
		public TextView tv_freeze_count;
		public ImageView iv_cellphone;

		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_order_subject = (TextView) view.findViewById(R.id.tv_order_subject);
			tv_day = (TextView) view.findViewById(R.id.tv_day);
			tv_reason = (TextView) view.findViewById(R.id.tv_reason);
			tv_freeze_count = (TextView) view.findViewById(R.id.tv_freeze_count);
			iv_cellphone = (ImageView) view.findViewById(R.id.iv_cellphone);
		}

	}

}
