package com.chejiawang.android.ui.fragment;


import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import org.apache.http.Header;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.chejiawang.android.adapter.MyPagerAdapter;
import com.chejiawang.android.api.TurnApi;
import com.chejiawang.android.app.AppContext;
import com.chejiawang.android.app.FragmentController;
import com.chejiawang.android.app.Logger;
import com.chejiawang.android.base.Contants;
import com.chejiawang.android.bean.CoachLoginSuccessInfo;
import com.chejiawang.android.ui.R;
import com.chejiawang.android.ui.UserInfoActivity;
import com.chejiawang.android.utils.StringUtils;
import com.chejiawang.android.utils.UIHelper;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class TurnFragment extends Fragment implements OnClickListener{
	
	private static String TAG = "TurnFragment";
	private Button bt_one, bt_two, bt_three, bt_four;

	private Button bt_user_info;
	private ViewPager vp_turn;
	private ImageView iv_line;
	
	private ArrayList<Fragment> mArray;
	int lineWidth = 0;
	int currentIndex = 0;
	private int buttonNum = 4;
	private Resources resource;
	
	private Fragment one, two, three, four;
	
	private FragmentController mController = FragmentController.getInstance(); 
	private int coach_id;
	private String startDate;
	private String endDate;
//	private Handler mHandler = new Handler(){
//		
//	}
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			Logger.e(TAG, "Turn Fragment 收到消息！" );
			onRefresh();
		}
		
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_turn, container, false);
		resource = this.getResources();
		initRequestInfo();
		initBroadCastReceiver();
		initView(view);
		initWidth();
		initFragment();
		initAction();
		bt_one.setTextColor(resource.getColor(R.color.bt_background_selected));
		return view;
	}

	public void initRequestInfo() {
		this.coach_id = ((CoachLoginSuccessInfo)AppContext.getInfo("coach_info")).getCoach_id();
		this.startDate = "2015-06-20";
		this.endDate   = "2015-06-24";
	}
	private void initBroadCastReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Contants.GETTURNDATE);
		this.getActivity().registerReceiver(mBroadcastReceiver, filter);
		
	}
	
	protected void onRefresh() {
		TurnApi.getTurnInfo(coach_id, startDate, endDate, getHandler());
	}

	private AsyncHttpResponseHandler getHandler() {
		return new MyHttpHandler(this);
	}

	private void initAction() {
		bt_one.setOnClickListener(this);
		bt_two.setOnClickListener(this);
		bt_three.setOnClickListener(this);
		bt_four.setOnClickListener(this);
		bt_user_info.setOnClickListener(this);
		
		vp_turn.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mArray));
		vp_turn.setOnPageChangeListener(new MyPagerChangerListener());
		vp_turn.setOffscreenPageLimit(mArray.size());//设置初始预览界面的个数
		vp_turn.setCurrentItem(0);
		
	}

	private void initFragment() {
		mArray = new ArrayList<Fragment>();
		one = new TurnOneFragment();
		two = new TurnTwoFragment();
		three = new TurnThreeFragment();
		four = new TurnFourFragment();
		mArray.add(one);
		mArray.add(two);
		mArray.add(three);
		mArray.add(four);
	}

	private void initView(View view) {
		bt_user_info = (Button) view.findViewById(R.id.bt_user_info);
		bt_one = (Button) view.findViewById(R.id.bt_today);
		bt_two = (Button) view.findViewById(R.id.bt_tomorrow);
		bt_three = (Button) view.findViewById(R.id.bt_after_tomorrow);
		bt_four = (Button) view.findViewById(R.id.bt_after_three_day);
		vp_turn = (ViewPager) view.findViewById(R.id.vp_turn);
		iv_line = (ImageView) view.findViewById(R.id.iv_line);
			
	}
	
	private void initWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        lineWidth = screenW / buttonNum;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_today:
			vp_turn.setCurrentItem(0);
			break;
		case R.id.bt_tomorrow:
			vp_turn.setCurrentItem(1);
			break;
		case R.id.bt_after_tomorrow:
			vp_turn.setCurrentItem(2);
			break;
		case R.id.bt_after_three_day:
			vp_turn.setCurrentItem(3);
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
					UIHelper.setButtonTextColor(resource, bt_two,resource.getColor(R.color.bt_background_unselected)
							);
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_three,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth, 0, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_four,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_one,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_one,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth,
							lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_three,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth,
							lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_four,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_two,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 2:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_one,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth,
							2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_two,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(3 * lineWidth,
							2 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_four,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_three,
						resource.getColor(R.color.bt_background_selected));
				break;
			case 3:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_one,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(lineWidth,
							3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_two,
							resource.getColor(R.color.bt_background_unselected));
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(2 * lineWidth,
							3 * lineWidth, 0, 0);
					UIHelper.setButtonTextColor(resource, bt_three,
							resource.getColor(R.color.bt_background_unselected));
				}
				UIHelper.setButtonTextColor(resource, bt_four,
						resource.getColor(R.color.bt_background_selected));
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(150);
			iv_line.startAnimation(animation);
		}
	}
	
	public static class MyHttpHandler extends AsyncHttpResponseHandler {

		private WeakReference<TurnFragment> mInstance;

		public MyHttpHandler(TurnFragment instance) {
			super();
			mInstance = new WeakReference<TurnFragment>(instance);
		}

		@Override
		public void onSuccess(int i, Header[] header, byte[] responseBytes) {
			TurnFragment fragment = mInstance.get();
			if (fragment != null) {
				//成功的话，通知其观察的对象进行对象更新
				try {
					String result = StringUtils.inStream2String(new ByteArrayInputStream(responseBytes));
					Logger.e(TAG, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		@Override
		public void onFailure(int i, Header[] header, byte[] responseBytes,
				Throwable throwable) {
			TurnFragment fragment = mInstance.get();
			if (fragment != null) {
				
			}
		}
	};
	@Override
	public void onDestroy() {
		if(mBroadcastReceiver != null){
			this.getActivity().unregisterReceiver(mBroadcastReceiver);
		}
		super.onDestroy();
	}
}
