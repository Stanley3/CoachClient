package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.adapter.CoachTeachRecordAdapter;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.CoachTeachRecordApi;
import com.chejiawang.android.app.Logger;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.list.CoachTeachRecordList;
import com.chejiawang.android.utils.ListEntity;

import android.view.View;

public class FinanceTouchTeachLogin extends BaseRecycleViewFragment {
	
	private int coach_id = 23; // 需要重新获取
	private final String TAG = "FinanceTouchTeachLogin";
	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		super.initViews(view);
	}
	
	@Override
	public void onItemClick(View view) {
		// TODO Auto-generated method stub
		super.onItemClick(view);
	}
	
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		// TODO Auto-generated method stub
		return new CoachTeachRecordAdapter();
	}
	
	@Override
	protected String getCacheKey() {
		// TODO Auto-generated method stub
		return TAG;
	}
	
	@Override
	protected ListEntity parseList(InputStream is) throws Exception {
		// TODO Auto-generated method stub
		CoachTeachRecordList coachTeachRecordList = CoachTeachRecordList.parse(is);
		return coachTeachRecordList;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {
		// TODO Auto-generated method stub
		return (CoachTeachRecordList)seri;
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		// TODO Auto-generated method stub
		return super.getCacheKeyPrefix();
	}
	
	@Override
	protected void sendRequestData() {
		// TODO Auto-generated method stub
		Logger.i(TAG, "sendRequestData");
		CoachTeachRecordApi.getCoachTeachRecord(coach_id, mCurrentPage,10,getResponseHandler());
	}
}
