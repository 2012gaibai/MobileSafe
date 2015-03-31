package com.shdc.mobilesafe;

import com.shdc.mobilesafe.ui.SettingItemView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends Activity {

	private SettingItemView siv_update;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		boolean update=sp.getBoolean("update", false);
		siv_update=(SettingItemView) findViewById(R.id.siv_update);
		if(update){
			siv_update.setChecked(true);
			siv_update.setDesc("自动更新已经开启");
		}else {
			siv_update.setChecked(false);
			siv_update.setDesc("自动更新已经关闭");
		}
		siv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editor editor=sp.edit();
				//判断是否有选中
				if(siv_update.isChecked()){//已经打开自动升级
					//关闭自动升级
					siv_update.setChecked(false);
					siv_update.setDesc("自动更新已经关闭");
					editor.putBoolean("update", false);
				}else {//没有打开自动升级
					//打开自动升级
					siv_update.setChecked(true);
					siv_update.setDesc("自动更新已经开启");
					editor.putBoolean("update", true);
				}
				editor.commit();
				
			}
		});
	}

}
