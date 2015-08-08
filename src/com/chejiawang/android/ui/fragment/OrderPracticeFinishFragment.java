package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.adapter.PracticerFinishRecycleAdapter;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.OrderApi;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.list.PracticeFinishList;
import com.chejiawang.android.utils.ListEntity;

import android.view.View;

public class OrderPracticeFinishFragment extends BaseRecycleViewFragment {
	/**************************************************/
	//目前做测试参数
	private int coach_id = 1;
	private String sort = "order_time";
	private int order_status = 3;
	private String dir = "desc";
	/**************************************************/
	protected static final String TAG = OrderPracticeFinishFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "practice_finish_";
	
	@Override
	protected void initViews(View view) {
		
		super.initViews(view);
	}
	
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new PracticerFinishRecycleAdapter();
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	protected ListEntity parseList(InputStream is) throws Exception{
		PracticeFinishList list = PracticeFinishList.parse(is);
		return list;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (PracticeFinishList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		OrderApi.getPracticeFinishList(coach_id, mCurrentPage, sort, order_status, dir, getResponseHandler());
	}
	@Override
	public void onItemClick(View view, int position) {
		CoachPrecontractRecordVo practiceFinish = (CoachPrecontractRecordVo) mAdapter.getItem(position);
		if(practiceFinish != null){
			
		}
		super.onItemClick(view);
	}

}
