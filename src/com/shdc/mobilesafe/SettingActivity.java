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
			siv_update.setDesc("�Զ������Ѿ�����");
		}else {
			siv_update.setChecked(false);
			siv_update.setDesc("�Զ������Ѿ��ر�");
		}
		siv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editor editor=sp.edit();
				//�ж��Ƿ���ѡ��
				if(siv_update.isChecked()){//�Ѿ����Զ�����
					//�ر��Զ�����
					siv_update.setChecked(false);
					siv_update.setDesc("�Զ������Ѿ��ر�");
					editor.putBoolean("update", false);
				}else {//û�д��Զ�����
					//���Զ�����
					siv_update.setChecked(true);
					siv_update.setDesc("�Զ������Ѿ�����");
					editor.putBoolean("update", true);
				}
				editor.commit();
				
			}
		});
	}

}
