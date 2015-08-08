package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.adapter.AllEvaluateRecycleAdapter;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.EvaluateApi;
import com.chejiawang.android.base.EvaluateBaseRecycleViewFragment;
import com.chejiawang.android.bean.EvaluationAndOrderInfo;
import com.chejiawang.android.list.EvaluationList;
import com.chejiawang.android.list.MyEvalution;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.R.layout;
import com.chejiawang.android.utils.ListEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EvaluateBadFragment extends EvaluateBaseRecycleViewFragment {
	/**************************************************/
	//目前做测试参数
	private int coach_id = 1;
	private String sort = "evaluation_time";
	private int evaluation = 2;
	private String dir = "desc";
	/**************************************************/
	protected static final String TAG = EvaluateAllFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "bad_evaluate_";
	
	@Override
	protected void initViews(View view) {
		
		super.initViews(view);
	}
	
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new AllEvaluateRecycleAdapter(this.getActivity());
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	protected ListEntity parseList(InputStream is) throws Exception{
		MyEvalution evalution = EvaluationList.parse(is);
		return evalution;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (MyEvalution)seri;
	}
	
	@Override
	protected void sendRequestData() {
		EvaluateApi.getEvalution(1, mCurrentPage, sort, evaluation, dir, getResponseHandler());
	}
	
	@Override
	public void onItemClick(View view, int position) {
		EvaluationAndOrderInfo evaluation = (EvaluationAndOrderInfo) mAdapter.getItem(position);
		if(evaluation != null){
			
			
		}
		super.onItemClick(view);
	}
}
