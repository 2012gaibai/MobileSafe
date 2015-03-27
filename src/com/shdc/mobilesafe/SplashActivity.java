package com.shdc.mobilesafe;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.shdc.mobilesafe.R.string;
import com.shdc.mobilesafe.utils.StreamTools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {

	protected static final String TAG = "SplashActivity";
	protected static final int ENTER_HOME = 0;
	protected static final int SHOW_UPDATE_DIALOG = 1;
	protected static final int URL_ERROR = 2;
	protected static final int NETWORK_ERROR = 3;
	protected static final int JSON_ERROR = 4;
	private TextView tv_splash_version;
	private String description;
	private String apkurl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("�汾��:" + getVersionName(this));

		checkUpdate();
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(500);
		findViewById(R.id.rl_root_splash).startAnimation(aa);

	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_UPDATE_DIALOG:// ��ʾ�����Ի���
				Log.i(TAG, "��ʾ�����Ի���");
				break;

			case ENTER_HOME:// ������ҳ��
				enterHome();
				break;
			case URL_ERROR:// URL����
				enterHome();
				Toast.makeText(getApplicationContext(), "URL����", 0).show();
				break;
			case NETWORK_ERROR:// �����쳣
				enterHome();
				Toast.makeText(getApplicationContext(), "�����쳣", 0).show();
				break;
			case JSON_ERROR:// json��������
				enterHome();
				Toast.makeText(SplashActivity.this, "json��������", 0).show();
				break;
			default:
				break;
			}
		}

		protected void enterHome() {
			Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
			startActivity(intent);
			// �ر�֮ǰ��activity
			finish();
		}

	};

	/**
	 * ����Ƿ����°汾������о�����
	 */
	private void checkUpdate() {
		new Thread() {
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				Message msg = Message.obtain();
				try {
					URL url = new URL(getString(R.string.serverurl));
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setRequestMethod("GET");// ��������ʽ
					conn.setConnectTimeout(3000);// ���ó�ʱʱ��
					// conn.setReadTimeout(2000);
					// ��ȡ��Ӧ��
					int code = conn.getResponseCode();
					if (code / 100 == 2) { // 200��ʾ�����ɹ�
						InputStream in = conn.getInputStream();
						String result = StreamTools.readFromStream(in);// ����ת����string����
						Log.i(TAG, "�����ɹ�" + result);
						// json����
						JSONObject obj = new JSONObject(result);
						String version = (String) obj.get("version");
						description = (String) obj.get("description");
						apkurl = (String) obj.get("apkurl");

						if (getVersionName(SplashActivity.this).equals(version)) {// �汾һ�²�������
							msg.what = ENTER_HOME;
						} else {// �汾��һ��������
							msg.what = SHOW_UPDATE_DIALOG;
						}

					}
				} catch (MalformedURLException e) {
					msg.what = URL_ERROR;
					e.printStackTrace();

				} catch (IOException e) {
					msg.what = NETWORK_ERROR;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = JSON_ERROR;
					e.printStackTrace();
				} finally {
					long endTime = System.currentTimeMillis();
					// ����ʱ��
					long dTime = endTime - startTime;
					if (dTime < 2000) {
						try {
							Thread.sleep(2000 - dTime);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}
					handler.sendMessage(msg);
				}
			}

		}.start();
	}

	/**
	 * ��ȡӦ�ó���İ汾����
	 * 
	 * @param context
	 * @return
	 */
	private String getVersionName(Context context) {
		// �����ֻ�apk
		PackageManager pm = context.getPackageManager();
		// ��ȡ�����嵥�ļ�

		try {
			PackageInfo packageInfo = pm.getPackageInfo("com.shdc.mobilesafe",
					0);

			return packageInfo.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

}
