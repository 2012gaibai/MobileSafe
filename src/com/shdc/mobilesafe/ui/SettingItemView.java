package com.shdc.mobilesafe.ui;

import com.shdc.mobilesafe.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * �Զ�����Ͽؼ���������������textview������һ��checkBox������һ��view
 * 
 * @author Administrator
 * 
 */
public class SettingItemView extends RelativeLayout {
	private CheckBox cb_status;
	private TextView tv_title;
	private TextView tv_desc;
	

	/**
	 * ��ʼ�������ļ�
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		// ��һ�������ļ�-->view���Ҽ�����SettingItemView
		View.inflate(context, R.layout.setting_item_view, this);
		tv_desc=(TextView) findViewById(R.id.tv_desc);
		tv_title=(TextView) findViewById(R.id.tv_title);
		cb_status=(CheckBox) findViewById(R.id.cb_status);
	}

	public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);// ��ʼ�������ļ�

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
	 * �����Ͽؼ��Ƿ�ѡ��
	 */
	public boolean isChecked(){
		return cb_status.isChecked();
	}
	
	/**
	 * ������Ͽؼ���״̬
	 */
	public void setChecked(boolean checked){
		cb_status.setChecked(checked);
	}
	/**
	 * ������Ͽؼ���������Ϣ
	 */
	public void setDesc(String text){
		tv_desc.setText(text);
	}

}
