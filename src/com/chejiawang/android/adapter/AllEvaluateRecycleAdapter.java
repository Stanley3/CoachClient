package com.chejiawang.android.adapter;

import com.chejiawang.android.bean.EvaluationAndOrderInfo;
import com.chejiawang.android.ui.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AllEvaluateRecycleAdapter extends RecycleBaseAdapter {

	private Context context;
	public AllEvaluateRecycleAdapter(Context context) {
		super();
		this.context = context;
	}
	public AllEvaluateRecycleAdapter(View headerView){
		mHeaderView = headerView;
	}
	
	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.evaluation_list_item, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}
	
	@Override
	protected void onBindItemViewHolder(RecycleBaseAdapter.ViewHolder vh, int position) {
		super.onBindItemViewHolder(vh, position);
		AllEvaluateRecycleAdapter.ViewHolder holder= (ViewHolder) vh;
		EvaluationAndOrderInfo evaluate = (EvaluationAndOrderInfo) _data.get(position);
		holder.tv_evaluate_skill.setText("技能" + evaluate.getSkill() + "分");
		holder.tv_evaluate_service.setText("服务" + evaluate.getService_attitude() + "分");
		holder.tv_evaluate_hygiene.setText("卫生" + evaluate.getHygiene() + "分");
		
		holder.tv_evaluate_content.setText(evaluate.getContent());
		holder.tv_evaluate_day.setText("日期：" + evaluate.getEvaluation_time());
		String subject = evaluate.getCourse_status();
//		subject = subject.equals("2") ? "科目二":"科目三";
//		holder.tv_subject.setText(subject);
		String evaluation = evaluate.getEvaluation();
		Drawable image;
		if(evaluation.equals("0")){
			image = context.getResources().getDrawable(R.drawable.pingjia_good);
		}else if(evaluation.equals("1")){
			image = context.getResources().getDrawable(R.drawable.pingjia_mid);
		}else{
			image = context.getResources().getDrawable(R.drawable.pingjia_bad);
		}
		holder.iv_evaluate_level.setBackgroundDrawable(image);

		//需要添加是否是VIP包过信息    2015.6.27  孙晓雨
		
		
	}
	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder{
		public ImageView iv_evaluate_level;
	    public TextView tv_evaluate_service;
	    public TextView tv_evaluate_skill;
	    public TextView tv_evaluate_hygiene;
	    public TextView tv_evaluate_content;
	    public TextView tv_subject;
	    public TextView tv_evaluate_day;
		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			iv_evaluate_level = (ImageView) view.findViewById(R.id.iv_evaluate_level);
			tv_evaluate_service = (TextView) view.findViewById(R.id.tv_evaluate_service);
			tv_evaluate_skill = (TextView) view.findViewById(R.id.tv_evaluate_skill);
			tv_evaluate_hygiene = (TextView)view.findViewById(R.id.tv_evaluate_hygiene);
			tv_evaluate_content = (TextView) view.findViewById(R.id.tv_evaluate_content);
			tv_subject = (TextView) view.findViewById(R.id.tv_subject);
			tv_evaluate_day = (TextView) view.findViewById(R.id.tv_evaluate_day);
		   
		}
		
	}

}
