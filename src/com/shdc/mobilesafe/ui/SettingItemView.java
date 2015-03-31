package com.shdc.mobilesafe.ui;

import com.shdc.mobilesafe.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义组合控件，它里面有两个textview，还有一个checkBox，还有一个view
 * 
 * @author Administrator
 * 
 */
public class SettingItemView extends RelativeLayout {
	private CheckBox cb_status;
	private TextView tv_title;
	private TextView tv_desc;
	

	/**
	 * 初始化布局文件
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		// 把一个布局文件-->view并且加载在SettingItemView
		View.inflate(context, R.layout.setting_item_view, this);
		tv_desc=(TextView) findViewById(R.id.tv_desc);
		tv_title=(TextView) findViewById(R.id.tv_title);
		cb_status=(CheckBox) findViewById(R.id.cb_status);
	}

	public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);// 初始化布局文件

	}

	public SettingItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public SettingItemView(Context context) {
		super(context);
		initView(context);
	}
	
	/**
	 * 检查组合控件是否被选中
	 */
	public boolean isChecked(){
		return cb_status.isChecked();
	}
	
	/**
	 * 设置组合控件的状态
	 */
	public void setChecked(boolean checked){
		cb_status.setChecked(checked);
	}
	/**
	 * 设置组合控件的描述信息
	 */
	public void setDesc(String text){
		tv_desc.setText(text);
	}

}
