package com.chejiawang.android.ui.fragment;

import com.chejiawang.android.adapter.TurnBaseAdapter;
import com.chejiawang.android.base.BaseTurnFragment;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class TurnThreeFragment extends BaseTurnFragment {
	private GridView gv_turn;
	private TurnBaseAdapter mAdapter;
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_turn_tempele3, container, false);
//		
//		String turnData = "111110000011111000001010";
//		gv_turn = (GridView) view.findViewById(R.id.gv_turn);
//		mAdapter = new TurnBaseAdapter(this.getActivity(), turnData);
//		gv_turn.setAdapter(mAdapter);
//		
//		
//		return view;
//
//	}
}
