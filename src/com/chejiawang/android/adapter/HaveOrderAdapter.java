package com.chejiawang.android.adapter;

import java.util.List;

import com.chejiawang.android.bean.CoachPrecontractRecord;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 已经预约适配器
 * @author XY.sun
 *
 */
public class HaveOrderAdapter extends BaseAdapter{
	private List<CoachPrecontractRecord> mList;
	
	public HaveOrderAdapter(List<CoachPrecontractRecord> mList) {
		super();
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		return null;
	}

}
