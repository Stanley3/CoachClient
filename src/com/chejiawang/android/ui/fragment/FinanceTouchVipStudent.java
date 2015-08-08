package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.adapter.VipStudentAdapter;
import com.chejiawang.android.api.EvaluateApi;
import com.chejiawang.android.api.VipStudentApi;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.list.VipStudentList;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.R.layout;
import com.chejiawang.android.utils.ListEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FinanceTouchVipStudent extends BaseRecycleViewFragment {
	private static final String CACHE_KEY_PREFIX = "FinanceTouchVipStudent";
	private int coach_id = 23;				// 这个要从qit
	private String sort = "begin_vip_date";
	private String dir = "desc";
	private final String TAG ="FinanceTouchVipStudent";
	

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		super.initViews(view);
//		sendRequestData();
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
		return new VipStudentAdapter();
	}

	
	@Override
	protected String getCacheKey() {
		// TODO Auto-generated method stub
		return TAG;
	}
	@Override
	protected ListEntity parseList(InputStream is) throws Exception {
		// TODO Auto-generated method stub
		VipStudentList studentList = VipStudentList.parse(is);
		return studentList;
	}
	
	
	@Override
	protected ListEntity readList(Serializable seri) {
		// TODO Auto-generated method stub
		return (VipStudentList)seri;
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
		VipStudentApi.getVipStudent(coach_id, mCurrentPage, sort, dir, getResponseHandler());
	}
	
	
}
