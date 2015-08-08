package com.chejiawang.android.ui.fragment;


import com.chejiawang.android.ui.FinanceTouchActivity;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.UserInfoActivity;
import com.chejiawang.android.ui.R.id;
import com.chejiawang.android.ui.R.layout;
import com.chejiawang.android.utils.UIHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FinanceFragment extends Fragment implements OnClickListener{
	/**
	 * 用户信息按钮
	 */
	private Button bt_user_info;
	/**
	 * 教练姓名和科目
	 */
	private TextView tv_coach_name_subject;
	/**
	 * 学校名字和教练电话
	 */
	private TextView tv_school_name_tel;
	/**
	 * 教练总收入
	 */
	private TextView tv_total_income;
	/**
	 * Vip学员数量
	 */
	private TextView tv_vip_num;
	/**
	 * 授课次数
	 */
	private TextView tv_teach_num;
	/**
	 * 科目二授课次数
	 */
	private TextView tv_teach_two_num;
	/**
	 * 科目三授课次数
	 */
	private TextView tv_teach_three_num;
	/**
	 * 冻结金额
	 */
	private TextView tv_freeze_num;
	/**
	 * 名下ViP
	 */
	private TextView bt_vip_login;
	/**
	 * 授课记录
	 */
	private TextView bt_teach_login;
	/**
	 * 收入记录
	 */
	private TextView bt_income_login;
	/**
	 * 冻结列表
	 */
	private TextView bt_freeze_list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_finance, container, false);
		initView(view);
		initAction();
		return view;
	}
	/**
	 * 初始化所有按钮的动作
	 */
	private void initAction() {
		bt_user_info.setOnClickListener(this);
		bt_vip_login.setOnClickListener(this);
		bt_teach_login.setOnClickListener(this);
		bt_income_login.setOnClickListener(this);
		bt_freeze_list.setOnClickListener(this);
		
	}
	/**
	 * 初始化所有控件
	 * @param view
	 */
	private void initView(View view) {
		bt_user_info = (Button) view.findViewById(R.id.bt_user_info);
		tv_coach_name_subject = (TextView) view.findViewById(R.id.tv_coach_name_subject);
		tv_school_name_tel = (TextView) view.findViewById(R.id.tv_school_name_tel);
		tv_total_income = (TextView) view.findViewById(R.id.tv_total_income);
		tv_vip_num = (TextView) view.findViewById(R.id.tv_vip_number);
		tv_teach_num =(TextView) view.findViewById(R.id.tv_teach_num);
		tv_teach_two_num = (TextView) view.findViewById(R.id.tv_teach_two_num);
		tv_teach_three_num = (TextView) view.findViewById(R.id.tv_teach_three_num);
		tv_freeze_num = (TextView) view.findViewById(R.id.tv_freeze_num);
		bt_vip_login = (TextView) view.findViewById(R.id.bt_vip_login);
		bt_income_login = (TextView) view.findViewById(R.id.bt_income_login);
		bt_teach_login = (TextView) view.findViewById(R.id.bt_teach_login);
		bt_freeze_list = (TextView) view.findViewById(R.id.bt_freeze_list);
	}
	/**
	 * 所有鼠标的点击事件
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent(getActivity(), FinanceTouchActivity.class);
		switch(id){
		case R.id.bt_user_info:
			UIHelper.startActivityUtil(this.getActivity(), UserInfoActivity.class);
			return;
		case R.id.bt_vip_login:
			intent.putExtra("select_num", 1);
			break;
		case R.id.bt_teach_login:
			intent.putExtra("select_num", 2);
			break;
		case R.id.bt_income_login:
			intent.putExtra("select_num", 3);
			break;
		case R.id.bt_freeze_list:
			intent.putExtra("select_num", 4);
			break;
		}
		this.startActivity(intent);
		return;
		
	}
	
}
