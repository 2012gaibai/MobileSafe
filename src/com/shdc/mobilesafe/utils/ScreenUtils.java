package com.shdc.mobilesafe.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;




public class ScreenUtils {
	private ScreenUtils(){
		//�޷���ʵ����
		throw new UnsupportedOperationException("�޷���ʵ����");
	}
	
	/**
	 * �����Ļ���
	 * @param context
	 * @return
	 */
	public static int getScreenWdith(Context context){
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
}
