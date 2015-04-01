package com.shdc.mobilesafe.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;




public class ScreenUtils {
	private ScreenUtils(){
		//无法被实例化
		throw new UnsupportedOperationException("无法被实例化");
	}
	
	/**
	 * 获得屏幕宽度
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
