package com.chejiawang.android.adapter;

import com.chejiawang.android.adapter.AllEvaluateRecycleAdapter.ViewHolder;
import com.chejiawang.android.bean.EvaluationAndOrderInfo;
import com.chejiawang.android.bean.VIPStudentOfCoachInfo;
import com.chejiawang.android.ui.R;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class VipStudentAdapter extends RecycleBaseAdapter {

	public VipStudentAdapter() {
		super();
	}

	public VipStudentAdapter(View headerView) {
		mHeaderView = headerView;
	}

	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.item_student_vip, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}

	@Override
	protected void onBindItemViewHolder(com.chejiawang.android.adapter.RecycleBaseAdapter.ViewHolder vh, int position) {
		VipStudentAdapter.ViewHolder holder = (ViewHolder) vh;
		VIPStudentOfCoachInfo studentInfo = (VIPStudentOfCoachInfo) _data.get(position);
		holder.tv_name.setText(studentInfo.getStudent_name());
		// holder.tv_order_subject.setText(studentInfo.getStudent_level());
		holder.tv_order_day.setText("开通日期:" + studentInfo.getBegin_vip_date());
		holder.tv_practice_time.setText("练习" + studentInfo.getTraining_times() + "次");

//		int level = studentInfo.getStudent_level();
//		String strLevel = "科目一";
//		if(level == 1)
//			strLevel = "科目一";
//		if(level == 2)
//			strLevel = "科目一";
//		if(level == 3)
//			strLevel = "科目三";
//		if(level == 4)
//			strLevel = "科目四";
			
	}

	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder {
		public TextView tv_name;
		public TextView tv_order_subject;
		public TextView tv_order_day;
		public TextView tv_practice_time;
		public ImageView iv_order_type;
		public ImageView iv_cellphone;

		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_order_subject = (TextView) view.findViewById(R.id.tv_order_subject);
			tv_order_day = (TextView) view.findViewById(R.id.tv_order_day);
			tv_practice_time = (TextView) view.findViewById(R.id.tv_practice_time);
			iv_order_type = (ImageView) view.findViewById(R.id.iv_order_type);
			iv_cellphone = (ImageView) view.findViewById(R.id.iv_cellphone);
		}

	}

}
