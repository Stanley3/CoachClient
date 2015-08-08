package com.chejiawang.android.base;

import android.os.Environment;

public class Contants {

	//存储卡上的基本目录
	public final static String BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "ChengJW";
	//缓存目录
	public final static String CACHE_DIR = BASE_DIR + ".cache/";
	public final static long CACHE_EXPIRE_ONE_DAY = 3600000;
	public final static String EvalutionBroadCast = "com.chengjiawang.android.ui.GETEVALUATION";
	public final static String GETTURNDATE = "com.chejiawang.android.ui.GETTURNDATA";
}
