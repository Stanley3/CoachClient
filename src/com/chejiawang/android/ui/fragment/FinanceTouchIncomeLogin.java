package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.adapter.CoachIncomAdapter;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.CoachIncomeApi;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.list.CoachIncomeList;
import com.chejiawang.android.utils.ListEntity;

import android.util.Log;
import android.view.View;

public class FinanceTouchIncomeLogin extends BaseRecycleViewFragment {

	private static final String CACHE_KEY_PREFIX = "finance_income_";
	private int coach_id = 23;				// 这个要从其他地方获取
	private String sort = "begin_vip_date";
	private String dir = "desc";
	private final String TAG ="FinanceTouchIncomeLogin";
	

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		super.initViews(view);
	}

	//
	@Override
	public void onItemClick(View view) {
		// TODO Auto-generated method stub
		super.onItemClick(view);
	}

	@Override
	protected RecycleBaseAdapter getListAdapter() {
		// TODO Auto-generated method stub
		return new CoachIncomAdapter();
	}

	
	@Override
	protected String getCacheKey() {
		// TODO Auto-generated method stub
		return TAG;
	}
	@Override
	protected ListEntity parseList(InputStream is) throws Exception {
		// TODO Auto-generated method stub
		CoachIncomeList coachList = CoachIncomeList.parse(is);
		return coachList;
	}
	
	
	@Override
	protected ListEntity readList(Serializable seri) {
		// TODO Auto-generated method stub
		return (CoachIncomeList)seri;
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		// TODO Auto-generated method stub
		return super.getCacheKeyPrefix();
	}
	
	@Override
	protected void sendRequestData() {
		// TODO Auto-generated method stub
		// 这里需要教练id 暂时未更新
		Log.i(TAG, "SEND REQUEST");
		CoachIncomeApi.getCoachIncome(coach_id, mCurrentPage, 3, 10, getResponseHandler());
	}
	
	
}
