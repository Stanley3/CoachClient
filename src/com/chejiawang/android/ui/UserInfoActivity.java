package com.chejiawang.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserInfoActivity extends Activity implements OnClickListener {
	private Button bt_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initView();
		initAction();
	}
	/**
	 * 初始化所有动作
	 */
	private void initAction() {
		bt_back.setOnClickListener(this);
		
	}
	private void initView() {
		bt_back = (Button) findViewById(R.id.bt_back);
		
	}
	/**
	 * 按钮的点击事件
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
		case R.id.bt_back:
			onBackPressed();
		}
		
	}
}
