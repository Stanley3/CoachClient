package com.chejiawang.android.ui.fragment;

import java.util.ArrayList;

import com.chejiawang.android.adapter.MyPagerAdapter;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.UserInfoActivity;
import com.chejiawang.android.ui.R.color;
import com.chejiawang.android.ui.R.id;
import com.chejiawang.android.ui.R.layout;
import com.chejiawang.android.utils.UIHelper;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class OrderFragment extends Fragment implements OnClickListener{
	/**
	 * 用户信息
	 */
	private Button bt_user_info;
	/**
	 * 已经预约按钮
	 */
	private Button bt_have_order;
	/**
	 * 训练完成按钮
	 */
	private Button bt_practice_finish;
	/**
	 * 取消预约按钮
	 */
	private Button bt_cancel_order;
	/**
	 * 存放三个fragment的容器
	 */
	private ViewPager vp_order;
	/**
	 * 按钮下切换的下划线
	 */
	private ImageView iv_line;
	/**
	 * 存放需要切换的三个Fragment
	 */
	private ArrayList<Fragment> mArray;
	/**
	 * 已经预约Fragment
	 */
	private OrderHavaOrderFragment haveOrder;
	/**
	 * 训练完成Fragment
	 */
	private OrderPracticeFinishFragment practiceFinish;
	/**
	 * 取消预约Fragment
	 */
	private OrderCancelOrderFragment cancelOrder;
	/**
	 * 当前第一几个按钮被选中
	 */
	int currentIndex = 0;
	/**
	 * 线宽
	 */
	int lineWidth = 0;
	/**
	 * 按钮的数量
	 */
	private int buttonNum = 3;
	/**
	 * 资源文件
	 */
	private Resources resource;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		resource = this.getResources();
		initView(view);
		initWidth();
		initFragment();
		initAction();
		bt_have_order.setTextColor(resource.getColor(R.color.bt_background_selected));
		return view;
	}
	private void initAction() {
		bt_user_info.setOnClickListener(this);
		bt_have_order.setOnClickListener(this);
		bt_practice_finish.setOnClickListener(this);
		bt_cancel_order.setOnClickListener(this);
		vp_order.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mArray));
		vp_order.setOnPageChangeListener(new MyPagerChangerListener());
		vp_order.setOffscreenPageLimit(mArray.size());//设置初始预览界面的个数
		vp_order.setCurrentItem(0);
		
	}
	private void initWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        lineWidth = screenW / buttonNum;
		
	}
	private void initFragment() {
		mArray = new ArrayList<Fragment>();
		haveOrder = new OrderHavaOrderFragment();
		practiceFinish = new OrderPracticeFinishFragment();
		cancelOrder = new OrderCancelOrderFragment();
		mArray.add(haveOrder);
		mArray.add(practiceFinish);
		mArray.add(cancelOrder);
		
	}
	private void initView(View view) {
		bt_user_info = (Button) view.findViewById(R.id.bt_user_info);
		bt_have_order = (Button) view.findViewById(R.id.bt_have_order);
		bt_practice_finish = (Button) view.findViewById(R.id.bt_practice_finish);
		bt_cancel_order = (Button) view.findViewById(R.id.bt_order_cancel);
		vp_order = (ViewPager) view.findViewById(R.id.vp_order);
		iv_line = (ImageView) view.findViewById(R.id.iv_line);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
		case R.id.bt_have_order:
			vp_order.setCurrentItem(0);
			break;
		case R.id.bt_practice_finish:
			vp_order.setCurrentItem(1);
			break;
		case R.id.bt_order_cancel:
			vp_order.setCurrentItem(2);
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
			switch(arg0){
			case 0:
				if(currentIndex == 1){
					animation = new TranslateAnimation(lineWidth, 0, 0, 0);
					bt_practice_finish.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}else if(currentIndex == 2){
					animation = new TranslateAnimation(2 * lineWidth, 0, 0, 0);
					bt_cancel_order.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_have_order.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if(currentIndex == 0){
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					bt_have_order.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}else if(currentIndex == 2){
					animation = new TranslateAnimation(2 * lineWidth, lineWidth, 0, 0);
					bt_cancel_order.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_practice_finish.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			case 2:
				if(currentIndex == 0 ){
					animation = new TranslateAnimation(0, 2 * lineWidth, 0, 0);
					bt_have_order.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}else if(currentIndex == 1){
					animation = new TranslateAnimation(lineWidth, 2 * lineWidth, 0, 0);
					bt_practice_finish.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_cancel_order.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(200);
			iv_line.startAnimation(animation);
		}
	}
}
