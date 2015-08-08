package com.chejiawang.android.base;

import com.chejiawang.android.adapter.TurnBaseAdapter;
import com.chejiawang.android.app.FragmentController;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.subview.EmptyLayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.GridView;

public class BaseTurnFragment extends Fragment {
	
	protected static final int STATE_NONE = 0;
	protected static final int STATE_REFRESH = 1;
	protected static final int STATE_LOADMORE = 2;
	protected int mState = STATE_NONE;

	private GridView mGridView;
	private TurnBaseAdapter mAdapter;
	protected EmptyLayout mErrorLayout;
	
	protected int mStoreEmptyState = -1;
	protected String mStoreEmptyMessage = "";
	private FragmentController mController = FragmentController.getInstance();
	
	private Handler mHandler = new Handler(){
		String turnInfo = null;
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mController.addFragment(this);
		View view = inflater.inflate(getLayoutRes(), container, false);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		mGridView = (GridView) view.findViewById(R.id.gv_turn);
		
		mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
		mErrorLayout.setOnLayoutClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mState = STATE_REFRESH;
				mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
				requestData();
			}
		});
		
		if (mAdapter != null) {
			mGridView.setAdapter(mAdapter);
			mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
		} else {
			mAdapter = getGirdAdapter();
			mGridView.setAdapter(mAdapter);
			if (requestDataIfViewCreated()) {
				mState = STATE_REFRESH;
				mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
				requestData();
				
			} else {
				mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
				
			}

		}
		if (mStoreEmptyState != -1) {
			mErrorLayout.setErrorType(mStoreEmptyState);
		}
		if (!TextUtils.isEmpty(mStoreEmptyMessage)) {
			mErrorLayout.setErrorMessage(mStoreEmptyMessage);
		}
	}
	/**
	 * 请求数据 需要子类去实现
	 */
	protected void requestData() {
		
	}

	private TurnBaseAdapter getGirdAdapter() {
		return new TurnBaseAdapter(this.getActivity()) ;
	}

	private boolean requestDataIfViewCreated() {
		return true;
	}

	private int getLayoutRes() {
		return R.layout.fragment_turn_tempele1;
	}
	@Override
	public void onDestroy() {
		mController.DeleteFragment(this);
		super.onDestroy();
	}
}
