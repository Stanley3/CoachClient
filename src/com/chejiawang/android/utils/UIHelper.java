package com.chejiawang.android.utils;

import com.chejiawang.android.app.AppManager;
import com.chejiawang.android.ui.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UIHelper {
	
	public static void startActivityUtil(Context context, Class<?> clazz){
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}
	/**
	 * 设置按钮的字体颜色
	 * @param resource
	 * @param view
	 * @param colorId
	 */
	public static void setButtonTextColor(Resources resource, Button view, int colorId){
		view.setTextColor(colorId);
	}
	/**
	 * 设置TextView的字体颜色
	 * @param resource
	 * @param view
	 * @param colorId
	 */
	public static void setTextViewTextColor(Resources resource, TextView view, int colorId){
		view.setTextColor(colorId);
	}
	
	/**
	 * 发送App异常崩溃报告
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont, final String crashReport)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_error);
		builder.setMessage(R.string.app_error_message);
		builder.setPositiveButton(R.string.submit_report, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//发送异常报告
				Intent i = new Intent(Intent.ACTION_SEND);
				//i.setType("text/plain"); //模拟器
				i.setType("message/rfc822") ; //真机
				i.putExtra(Intent.EXTRA_EMAIL, new String[]{"beidameng01@163.com"});
				i.putExtra(Intent.EXTRA_SUBJECT,"合合集团教练端 - 错误报告");
				i.putExtra(Intent.EXTRA_TEXT,crashReport);
				cont.startActivity(Intent.createChooser(i, "发送错误报告"));
				//退出
				AppManager.getAppManager().AppExit(cont);
			}
		});
		builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//退出
				AppManager.getAppManager().AppExit(cont);
			}
		});
		builder.show();
	}
	/**
	 * 弹出消息
	 * @param context
	 * @param msg
	 */
	public static void ToastMessage(Context context,String msg)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

}
