package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.adapter.FinanceFreeze;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.FinanceFreezeApi;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.base.EvaluateBaseRecycleViewFragment;
import com.chejiawang.android.list.FinanceFreezeList;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.R.layout;
import com.chejiawang.android.utils.ListEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FinanceTouchFreezeList extends BaseRecycleViewFragment {
	//目前做测试参数
	private int coach_id = 1;
	private int start = 0;
	/**************************************************/
	protected static final String TAG = FinanceTouchFreezeList.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "finance_freeze";

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		super.initViews(view);
	}
	
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		// TODO Auto-generated method stub
		return new FinanceFreeze(getActivity());
	}
	
	@Override
	protected String getCacheKey() {
		// TODO Auto-generated method stub
		return TAG;
	}
	
	public String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	@Override
	protected ListEntity parseList(InputStream is) throws Exception {
		// TODO Auto-generated method stub
		FinanceFreezeList financeFreezeList = FinanceFreezeList.parse(is);
		return financeFreezeList;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {
		// TODO Auto-generated method stub
		return (FinanceFreezeList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		// TODO Auto-generated method stub
		FinanceFreezeApi.getFinanceFreeze(coach_id, 2, start, 10, getResponseHandler());
	}
	
	@Override
	public void onItemClick(View view) {
		// TODO Auto-generated method stub
		super.onItemClick(view);
	}
	
}
