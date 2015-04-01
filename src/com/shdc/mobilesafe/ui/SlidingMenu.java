package com.shdc.mobilesafe.ui;

import com.shdc.mobilesafe.R;
import com.shdc.mobilesafe.utils.ScreenUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {

	// ��Ļ���
	private int mScreenWidth;

	// dp
	private int mMenuRightPadding;

	// �˵����
	private int mMenWidth;
	// ����˵����
	private int mHalfMenWidth;

	// ��¼�˵��Ĵ�״̬
	private boolean isOpen;

	// ��¼�Ƿ����ò˵����
	private boolean once;

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mScreenWidth = ScreenUtils.getScreenWdith(context);
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);

		int n = ta.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = ta.getIndex(i);
			switch (attr) {
			case R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding = ta.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50f,
								getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}
		}
		ta.recycle();
	}

	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingMenu(Context context) {
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		/**
		 * ��ʾ������һ�����
		 */
		if (!once) {
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);
			ViewGroup content = (ViewGroup) wrapper.getChildAt(1);

			mMenWidth = mScreenWidth - mMenuRightPadding;
			mHalfMenWidth = mMenWidth / 2;
			menu.getLayoutParams().width = mMenWidth;
			content.getLayoutParams().width = mScreenWidth;

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if (scrollX > mHalfMenWidth) {
				// �رղ˵�
				this.smoothScrollTo(mMenWidth, 0);
				isOpen = false;
			} else {
				// �򿪲˵�
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}

			return true;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		super.onLayout(changed, l, t, r, b);
		if (changed) {
			// ���ٽ��˵�����
			this.scrollTo(mMenWidth, 0);
			once=true;
		}
	}

}