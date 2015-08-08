package com.chejiawang.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import android.view.View;

import com.chejiawang.android.adapter.OrderCancelRecycleAdapter;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.OrderApi;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.list.HaveOrderList;
import com.chejiawang.android.list.OrderCancelList;
import com.chejiawang.android.utils.ListEntity;

public class OrderCancelOrderFragment extends BaseRecycleViewFragment {
	/**************************************************/
	//目前做测试参数
	private int coach_id = 1;
	private String sort = "order_time";
	private int order_status = 3;
	private String dir = "desc";
	/**************************************************/
	protected static final String TAG = OrderCancelOrderFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "order_Cancel_";
	
	@Override
	protected void initViews(View view) {
		
		super.initViews(view);
	}
	
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new OrderCancelRecycleAdapter();
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	protected ListEntity parseList(InputStream is) throws Exception{
		OrderCancelList list = OrderCancelList.parse(is);
		return list;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (OrderCancelList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		OrderApi.getOrderCancelList(coach_id, mCurrentPage, sort, order_status, dir, getResponseHandler());
	}
	
	@Override
	public void onItemClick(View view, int position) {
		CoachPrecontractRecordVo haveOrder = (CoachPrecontractRecordVo) mAdapter.getItem(position);
		if(haveOrder != null){
			
		}
		super.onItemClick(view);
	}

	

}
