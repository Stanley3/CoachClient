package com.chejiawang.android.adapter;

import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.ui.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderCancelRecycleAdapter extends RecycleBaseAdapter {

	
	public OrderCancelRecycleAdapter() {
		super();
	}
	public OrderCancelRecycleAdapter(View headerView){
		mHeaderView = headerView;
	}
	
	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.order_list_item, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}
	
	@Override
	protected void onBindItemViewHolder(RecycleBaseAdapter.ViewHolder vh, int position) {
		super.onBindItemViewHolder(vh, position);
		OrderCancelRecycleAdapter.ViewHolder holder= (ViewHolder) vh;
		CoachPrecontractRecordVo record = (CoachPrecontractRecordVo) _data.get(position);
		holder.tv_name.setText(record.getStudent_name());
		String course = record.getCourse_status();
		course = course.equals("2")? "预约科目二" : "预约科目三";
		holder.tv_order_subject.setText(course);
		String[] start_time = record.getTraining_start_time().split(" ");
		String[] end_time = record.getTraining_end_time().split(" ");
		holder.tv_order_day.setText("日期:" + start_time[0]);
		holder.tv_practice_time.setText(start_time[1] + "-" + end_time[1]);
		//需要添加取消预约    2015.6.27  孙晓雨
		
		
	}
	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder{
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
			tv_order_day = (TextView)view.findViewById(R.id.tv_order_day);
			tv_practice_time = (TextView) view.findViewById(R.id.tv_practice_time);
		    iv_order_type = (ImageView) view.findViewById(R.id.iv_order_type);
		    iv_cellphone = (ImageView) view.findViewById(R.id.iv_cellphone);
		}
		
	}

}
