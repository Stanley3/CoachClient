package com.chejiawang.android.ui.fragment;

import java.util.ArrayList;

import com.chejiawang.android.adapter.MyPagerAdapter;
import com.chejiawang.android.app.Logger;
import com.chejiawang.android.base.Contants;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.UserInfoActivity;
import com.chejiawang.android.utils.UIHelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EvaluateFragment extends Fragment implements OnClickListener {
	
	private static final String TAG = "EvaluateFragment";
	/**
	 * 用户信息
	 */
	private Button bt_user_info;
	/**
	 * 全部评价数量
	 */
	private TextView tv_all_num;
	/**
	 * 全部评价按钮
	 */
	private Button bt_all_evaluate;
	/**
	 * 好评数量
	 */
	private TextView tv_good_num;
	/**
	 * 好评按钮
	 */
	private Button bt_good_evaluate;
	/**
	 * 中评数量
	 */
	private TextView tv_middle_num;
	/**
	 * 中评按钮
	 */
	private Button bt_middle_evaluate;
	/**
	 * 差评数量
	 */
	private TextView tv_bad_num;
	/**
	 * 差评按钮
	 */
	private Button bt_bad_evaluate;
	/**
	 * 按钮下边选中的线
	 */
	private ImageView iv_line;
	/**
	 * 评价容器
	 */
	private ViewPager vp_evaluate;
	/**
	 * 存放需要切换的四个Fragment
	 */
	private ArrayList<Fragment> mArray;
	/**
	 * 所有评价Fragment
	 */
	private EvaluateAllFragment evaluateAll;
	/**
	 * 好评Fragment
	 */
	private EvaluateGoodFragment evaluateGood;
	/**
	 * 中评Fragment
	 */
	private EvaluateMiddleFragment evaluateMiddle;
	/**
	 * 差评Fragment
	 */
	private EvaluateBadFragment evaluateBad;

	/**
	 * 当前第一几个按钮被选中
	 */
	int currentIndex = 0;
	/**
	 * 线宽
	 */
	int lineWidth = 0;
	/**
	 * 资源文件
	 */
	private Resources resource;
	/**
	 * 按钮的数量
	 */
	private int buttonNum = 4;
	
	private MyBroadCast changeEvaluteNum;

	public Handler handler = new Handler(){
		public void handleMessage(Message message) {
			switch(message.what){
			
			case 0x111:
				Bundle bundler = (Bundle) message.obj;
				tv_all_num.setText(bundler.getString("total"));
				tv_good_num.setText(bundler.getString("goodEvaluation"));
				tv_middle_num.setText(bundler.getString("midEvaluation"));
				tv_bad_num.setText(bundler.getString("badEvaluation"));
			}
		};
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_evaluate, container,
				false);
		resource = this.getResources();
		initView(view);
		initWidth();
		initFragment();
		initAction();
		initBroadCast();
		return view;
	}

	private void initBroadCast() {
		changeEvaluteNum = new MyBroadCast();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Contants.EvalutionBroadCast);
		this.getActivity().registerReceiver(changeEvaluteNum, intentFilter);
	}

	/**
	 * 初始化所有动作
	 */
	private void initAction() {
		bt_user_info.setOnClickListener(this);
		bt_all_evaluate.setOnClickListener(this);
		bt_good_evaluate.setOnClickListener(this);
		bt_middle_evaluate.setOnClickListener(this);
		bt_bad_evaluate.setOnClickListener(this);
		tv_all_num.setOnClickListener(this);
		tv_good_num.setOnClickListener(this);
		tv_middle_num.setOnClickListener(this);
		tv_bad_num.setOnClickListener(this);
		vp_evaluate.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mArray));
		vp_evaluate.setOnPageChangeListener(new MyPagerChangerListener());
		vp_evaluate.setCurrentItem(0);

	}

	/**
	 * 初始化所有Fragment
	 */
	private void initFragment() {
		mArray = new ArrayList<Fragment>();
		evaluateAll = new EvaluateAllFragment();
		evaluateGood = new EvaluateGoodFragment();
		evaluateMiddle = new EvaluateMiddleFragment();
		evaluateBad = new EvaluateBadFragment();
		mArray.add(evaluateAll);
		mArray.add(evaluateGood);
		mArray.add(evaluateMiddle);
		mArray.add(evaluateBad);

	}
	/**
	 * 初始化线宽
	 */
	private void initWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		lineWidth = screenW / buttonNum;

	}

	/**
	 * 初始化所有控件
	 * 
	 * @param view
	 */
	private void initView(View view) {
		bt_user_info = (Button) view.findViewById(R.id.bt_user_info);
		tv_all_num = (TextView) view.findViewById(R.id.tv_all_num);
		tv_good_num = (TextView) view.findViewById(R.id.tv_good_num);
		tv_middle_num = (TextView) view.findViewById(R.id.tv_middle_num);
		tv_bad_num = (TextView) view.findViewById(R.id.tv_bad_num);
		bt_all_evaluate = (Button) view.findViewById(R.id.bt_all_evaluate);
		bt_good_evaluate = (Button) view.findViewById(R.id.bt_good_evaluate);
		bt_middle_evaluate = (Button) view
				.findViewById(R.id.bt_middle_evaluate);
		bt_bad_evaluate = (Button) view.findViewById(R.id.bt_bad_evaluate);
		iv_line = (ImageView) view.findViewById(R.id.iv_line);
		vp_evaluate = (ViewPager) view.findViewById(R.id.vp_evaluate);

	}

	/**
	 * 按钮点击事件
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_all_evaluate:
		case R.id.tv_all_num:
			vp_evaluate.setCurrentItem(0);
			break;
		case R.id.bt_good_evaluate:
		case R.id.tv_good_num:
			vp_evaluate.setCurrentItem(1);
			break;
		case R.id.bt_middle_evaluate:
		case R.id.tv_middle_num:
			vp_evaluate.setCurrentItem(2);
			break;
		case R.id.bt_bad_evaluate:
		case R.id.tv_bad_num:
			vp_evaluate.setCurrentItem(3);
			break;
		case R.id.bt_user_info:
			UIHelper.startActivityUtil(this.getActivity(), UserInfoActivity.class);
			break;
		}
	}

	class MyPagerChangerListener implements OnPageChangeListener {

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
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_good_evaluate,resource.getColor(R.color.bt_background_unselected)
							);
					UIHelper.setTextViewTextColor(resource, tv_good_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_middle_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_middle_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_bad_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_bad_num,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_all_evaluate,
						resource.getColor(R.color.bt_background_selected));
				UIHelper.setTextViewTextColor(resource, tv_all_num,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_all_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_all_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth,
							lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_middle_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_middle_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth,
							lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_bad_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_bad_num,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_good_evaluate,
						resource.getColor(R.color.bt_background_selected));
				UIHelper.setTextViewTextColor(resource, tv_good_num,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 2:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_all_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_all_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth,
							2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_good_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_good_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth,
							2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_bad_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_bad_num,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_middle_evaluate,
						resource.getColor(R.color.bt_background_selected));
				UIHelper.setTextViewTextColor(resource, tv_middle_num,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 3:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_all_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_all_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth,
							3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_good_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_good_num,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth,
							3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_middle_evaluate,
							resource.getColor(R.color.bt_background_unselected));
					UIHelper.setTextViewTextColor(resource, tv_middle_num,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_bad_evaluate,
						resource.getColor(R.color.bt_background_selected));
				UIHelper.setTextViewTextColor(resource, tv_bad_num,
						resource.getColor(R.color.bt_background_selected));
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(150);
			iv_line.startAnimation(animation);
		}
		
	}
	class MyBroadCast extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Logger.e(TAG, "receive the broadcast" + intent.getAction());
			if(intent.getAction().equals(Contants.EvalutionBroadCast)){
				Bundle bundle = intent.getExtras();
				Message msg = new Message();
				msg.what = 0x111;
				msg.obj = bundle;
				handler.sendMessage(msg);
			}
		}
		
	}
	
	@Override
	public void onDestroy() {
		this.getActivity().unregisterReceiver(this.changeEvaluteNum);
		super.onDestroy();
	}
}
