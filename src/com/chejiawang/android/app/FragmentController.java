package com.chejiawang.android.app;

import java.util.ArrayList;

import com.chejiawang.android.base.BaseTurnFragment;


public class FragmentController {

	private static ArrayList<BaseTurnFragment> fragmentList;
	private static FragmentController instance;
	
	private FragmentController(){}
	/**
	 * 单一实例
	 */
	public static FragmentController getInstance(){
		if(instance==null){
			instance=new FragmentController();
		}
		return instance;
	}
	/**
	 * 添加Fragment到堆栈
	 */
	public void addFragment(BaseTurnFragment fragment){
		if(fragmentList==null){
			fragmentList=new ArrayList<BaseTurnFragment>();
		}
		fragmentList.add(fragment);
	}

	/**
	 * 删除指定的Fragment
	 */
	public void DeleteFragment(BaseTurnFragment fragment){
		if(fragment!=null){
			fragmentList.remove(fragment);
			fragment=null;
		}
	}
	
	/**
	 * 通知所有Fragment进行更新
	 */
	public void NoticeAllFragmentRefresh(){
		
	}
}
