package com.chejiawang.android.ui.fragment;



import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.chejiawang.android.adapter.HaveOrderAdapter;
import com.chejiawang.android.adapter.HaveOrderRecycleAdapter;
import com.chejiawang.android.adapter.RecycleBaseAdapter;
import com.chejiawang.android.api.OrderApi;
import com.chejiawang.android.app.AppContext;
import com.chejiawang.android.base.BaseRecycleViewFragment;
import com.chejiawang.android.bean.CoachLoginSuccessInfo;
import com.chejiawang.android.bean.CoachPrecontractRecord;
import com.chejiawang.android.bean.CoachPrecontractRecordVo;
import com.chejiawang.android.bean.OrderRecord;
import com.chejiawang.android.list.HaveOrderList;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.R.id;
import com.chejiawang.android.ui.R.layout;
import com.chejiawang.android.ui.subview.PullToRefreshListView;
import com.chejiawang.android.utils.ListEntity;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OrderHavaOrderFragment extends BaseRecycleViewFragment {
	/**************************************************/
	/**************************************************/
	protected static final String TAG = OrderHavaOrderFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "haveOrder_";
	
	public int coach_id = 1;
	public String sort = "order_time";
	public int order_status = 0;
	public String dir = "desc";
	
	@Override
	protected void initViews(View view) {
		initRequestInfo();
		super.initViews(view);
	}
	
	public void initRequestInfo() {
		this.coach_id = ((CoachLoginSuccessInfo)AppContext.getInfo("coach_info")).getCoach_id();
		this.dir = "desc";
		this.order_status = 0;
		this.sort = "order_time";
	}

	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new HaveOrderRecycleAdapter();
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	protected ListEntity parseList(InputStream is) throws Exception{
		HaveOrderList list = HaveOrderList.parse(is);
		return list;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (HaveOrderList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		OrderApi.getOrderList(coach_id, mCurrentPage, sort, order_status, dir, getResponseHandler());
	}
	
	@Override
	public void onItemClick(View view, int position) {
		CoachPrecontractRecordVo haveOrder = (CoachPrecontractRecordVo) mAdapter.getItem(position);
		if(haveOrder != null){
			
		}
		super.onItemClick(view);
	}

	
	
}
