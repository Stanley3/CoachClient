package com.chejiawang.android.ui;

import com.chejiawang.android.ui.fragment.EvaluateFragment;
import com.chejiawang.android.ui.fragment.FinanceFragment;
import com.chejiawang.android.ui.fragment.OrderFragment;
import com.chejiawang.android.ui.fragment.TurnFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	/**
	 * Fragment容器
	 */
	private FrameLayout fl_fragmentRoot;
	/**
	 * 底部四个导航选项
	 */
	private RadioButton rb_order;// 预约
	private RadioButton rb_evaluate;// 评价
	private RadioButton rb_finance;// 财政
	private RadioButton rb_turn;// 排班

	private OrderFragment orderFragment;
	private EvaluateFragment evaluateFragment;
	private FinanceFragment financeFragment;
	private TurnFragment turnFragment;

	private FragmentManager fm;
	/**
	 * 退出开始时间
	 */
	private long exitStartTime = System.currentTimeMillis();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fm = this.getSupportFragmentManager();
		initView();
		initOnClickListener();
		initFragment();
	}

	private void initOnClickListener() {
		rb_order.setOnClickListener(this);
		rb_evaluate.setOnClickListener(this);
		rb_finance.setOnClickListener(this);
		rb_turn.setOnClickListener(this);

	}

	private void initFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		orderFragment = new OrderFragment();
		ft.replace(R.id.fl_fragmentRoot, orderFragment, "Order");
		ft.commit();
		rb_order.setChecked(true);
	}

	private void initView() {
		fl_fragmentRoot = (FrameLayout) findViewById(R.id.fl_fragmentRoot);
		rb_order = (RadioButton) findViewById(R.id.rb_order);
		rb_evaluate = (RadioButton) findViewById(R.id.rb_evaluate);
		rb_finance = (RadioButton) findViewById(R.id.rb_finance);
		rb_turn = (RadioButton) findViewById(R.id.rb_turn);
	}

	@Override
	public void onClick(View v) {
		popAllRadioButton();
		((RadioButton) v).setChecked(true);
		int id = v.getId();
		FragmentTransaction ft = fm.beginTransaction();
		switch (id) {
		case R.id.rb_order:
			orderFragment = new OrderFragment();
			ft.replace(R.id.fl_fragmentRoot, orderFragment, "Order");
			ft.commit();
			break;
		case R.id.rb_evaluate:
			evaluateFragment = new EvaluateFragment();
			ft.replace(R.id.fl_fragmentRoot, evaluateFragment, "Evaluate");
			ft.commit();
			break;
		case R.id.rb_finance:
			financeFragment = new FinanceFragment();
			ft.replace(R.id.fl_fragmentRoot, financeFragment, "Finance");
			ft.commit();
			break;
		case R.id.rb_turn:
			turnFragment = new TurnFragment();
			ft.replace(R.id.fl_fragmentRoot, turnFragment, "Turn");
			ft.commit();
			break;
		}

	}

	/**
	 * 弹起所有按钮，方便管理
	 */
	public void popAllRadioButton() {
		rb_order.setChecked(false);
		rb_evaluate.setChecked(false);
		rb_finance.setChecked(false);
		rb_turn.setChecked(false);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		long exitFinishTime = System.currentTimeMillis();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (exitFinishTime - exitStartTime > 2000) {
				Toast.makeText(this, "再按一次返回键关闭程序", Toast.LENGTH_SHORT).show();
				;
				exitStartTime = exitFinishTime;
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
