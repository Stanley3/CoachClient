package com.chejiawang.android.adapter;
import com.chejiawang.android.ui.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TurnBaseAdapter extends BaseAdapter {

	private static final int WORKHORE = 24;
	private static final String REST = "休息";
	private static final String NORMAL = "教1人";
	private static final char WORKBIT = '1';
	private static final char RESTBIT = '0';
	private String turnData = "111110000011111000001010"; ;
	private Context context;
	
	public TurnBaseAdapter(Context context) {
		super();
		this.context  = context;
	}

	@Override
	public int getCount() {
		return WORKHORE;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, R.layout.turn_gird_item, null);
		TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
		TextView tv_status = (TextView) view.findViewById(R.id.tv_status);
		
		String time_position = getTimeFormat(position);
		char status = turnData.charAt(position);
		String work_status;
		if(status == WORKBIT){
			work_status = NORMAL;
			view.setBackgroundColor(context.getResources().getColor(R.color.my_blue));
		}else{
			work_status = REST;
			view.setBackgroundColor(context.getResources().getColor(R.color.main_gray));
		}
		
		tv_time.setText(time_position);
		tv_time.setTextColor(context.getResources().getColor(R.color.my_white));
		tv_status.setText(work_status);
		tv_status.setTextColor(context.getResources().getColor(R.color.my_white));
		
		return view;
	}

	private String getTimeFormat(int position) {
		StringBuffer buffer = new StringBuffer();
		if(position < 10){
			buffer.append("0");
			buffer.append(new Integer(position).toString());
			buffer.append(":00");
			buffer.append("-");
			
			if(position == 9){
				buffer.append("10");
			}else{
				buffer.append(new Integer(position + 1).toString());
			}
			buffer.append(":00");
			return buffer.toString();
		}else{

			buffer.append(new Integer(position).toString());
			buffer.append(":00-");

			if(position == 23){
				buffer.append("0");
				buffer.append((new Integer((position + 1) % 24)).toString());
			}else{
				buffer.append((new Integer(position + 1)).toString());
			}
			buffer.append(":00");
			return buffer.toString();
		}
	}

}
