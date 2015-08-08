package com.chejiawang.android.ui;

import java.util.ArrayList;

import com.chejiawang.android.adapter.MyPagerAdapter;
import com.chejiawang.android.app.Logger;
import com.chejiawang.android.ui.fragment.FinanceTouchFreezeList;
import com.chejiawang.android.ui.fragment.FinanceTouchIncomeLogin;
import com.chejiawang.android.ui.fragment.FinanceTouchTeachLogin;
import com.chejiawang.android.ui.fragment.FinanceTouchVipStudent;
import com.chejiawang.android.utils.UIHelper;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FinanceTouchActivity extends FragmentActivity implements
		OnClickListener {
	/**
	 * 返回按钮
	 */
	private Button bt_back;
	/**
	 * vip学员
	 */
	private Button bt_vip_student;
	/**
	 * 授课记录
	 */
	private Button bt_teach_login;
	/**
	 * 收入记录
	 */
	private Button bt_income_login;
	/**
	 * 冻结记录
	 */
	private Button bt_freeze_login;
	/**
	 * 按钮切换线
	 */
	private ImageView iv_line;
	/**
	 * 按钮数量
	 */
	private int lineNum = 4;
	/**
	 * Fragment容器
	 */
	private ViewPager vp_finance;
	/**
	 * Vip学员Fragment
	 */
	private Fragment financeTouchVipStudent;
	/**
	 * 收入记录Fragment
	 */
	private Fragment financeTouchIncomeLogin;
	/**
	 * 授课记录Fragment
	 */
	private Fragment financeTouchTeachLogin;
	/**
	 * 冻结列表Fragment
	 */
	private Fragment financeTouchFreezeList;
	/**
	 * Fragment数组
	 */
	private ArrayList<Fragment> mArray;
	/**
	 * 线宽
	 */
	private int lineWidth;
	/**
	 * 当前第一几个按钮被选中
	 */
	private int currentIndex = 2;
	/**
	 * 资源文件
	 */
	private Resources resource;
	private String Tag = "FinanceTouchActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finance_touch);
		resource = this.getResources();
		initView();
		initLineWidth();
		initFragment();
		initAction();
	}

	/**
	 * 初始化所有动作
	 */
	private void initAction() {
		bt_back.setOnClickListener(this);
		bt_vip_student.setOnClickListener(this);
		bt_income_login.setOnClickListener(this);
		bt_teach_login.setOnClickListener(this);
		bt_freeze_login.setOnClickListener(this);
		currentIndex = getIntent().getExtras().getInt("select_num") - 1;
		initButtonText(currentIndex);
		// Logger.e(Tag, currentIndex + " ");
		vp_finance.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),
				mArray));
		vp_finance.setCurrentItem(currentIndex);
		vp_finance.setOnPageChangeListener(new MyPagerChangeListener());

	}

	private void initButtonText(int currentIndex) {
		Animation animation = null;
		switch (currentIndex) {
		case 0:
			UIHelper.setButtonTextColor(resource, bt_vip_student,
					resource.getColor(R.color.bt_background_selected));
			return;
		case 1:
			animation = new TranslateAnimation(0, lineWidth, 0, 0);
			UIHelper.setButtonTextColor(resource, bt_teach_login,
					resource.getColor(R.color.bt_background_selected));
			break;
		case 2:

			animation = new TranslateAnimation(0, 2 * lineWidth, 0, 0);
			UIHelper.setButtonTextColor(resource, bt_income_login,
					resource.getColor(R.color.bt_background_selected));
			break;
		case 3:
			animation = new TranslateAnimation(0, 3 * lineWidth, 0, 0);
			UIHelper.setButtonTextColor(resource, bt_freeze_login,
					resource.getColor(R.color.bt_background_selected));
			break;
		}
		animation.setFillAfter(true);
		animation.setDuration(50);
		iv_line.startAnimation(animation);
	}

	/**
	 * 初始化Fragment
	 */
	private void initFragment() {
		mArray = new ArrayList<Fragment>();
		financeTouchVipStudent = new FinanceTouchVipStudent();
		financeTouchIncomeLogin = new FinanceTouchIncomeLogin();
		financeTouchTeachLogin = new FinanceTouchTeachLogin();
		financeTouchFreezeList = new FinanceTouchFreezeList();
		mArray.add(financeTouchVipStudent);
		mArray.add(financeTouchTeachLogin);
		mArray.add(financeTouchIncomeLogin);
		mArray.add(financeTouchFreezeList);

	}

	/**
	 * 初始化线宽
	 */
	private void initLineWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWith = dm.widthPixels;
		lineWidth = screenWith / lineNum;
	}

	/**
	 * 初始化所有控件
	 */
	private void initView() {
		bt_back = (Button) findViewById(R.id.bt_back);
		bt_vip_student = (Button) findViewById(R.id.bt_vip_student);
		bt_teach_login = (Button) findViewById(R.id.bt_teach_login);
		bt_income_login = (Button) findViewById(R.id.bt_income_login);
		bt_freeze_login = (Button) findViewById(R.id.bt_freeze_login);
		iv_line = (ImageView) findViewById(R.id.iv_line);
		vp_finance = (ViewPager) findViewById(R.id.vp_finance);
	}

	class MyPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// Logger.e(Tag, "我第一次进入的时候被点击");
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_teach_login,
							resource.getColor(R.color.bt_background_unselected));

				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_income_login,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_freeze_login,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_vip_student,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_vip_student,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth,
							lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_income_login,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth,
							lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_freeze_login,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_teach_login,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 2:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_vip_student,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth,
							2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_teach_login,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth,
							2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_freeze_login,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_income_login,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 3:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_vip_student,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth,
							3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_teach_login,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth,
							3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_income_login,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_freeze_login,
						resource.getColor(R.color.bt_background_selected));
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(150);
			iv_line.startAnimation(animation);

		}

	}

	/**
	 * 所有按钮的点击事件
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_back:
			this.onBackPressed();
			break;
		case R.id.bt_vip_student:
			vp_finance.setCurrentItem(0);
			break;
		case R.id.bt_teach_login:
			vp_finance.setCurrentItem(1);
			break;
		case R.id.bt_income_login:
			vp_finance.setCurrentItem(2);
			break;
		case R.id.bt_freeze_login:
			vp_finance.setCurrentItem(3);
			break;
		}
	}
}
