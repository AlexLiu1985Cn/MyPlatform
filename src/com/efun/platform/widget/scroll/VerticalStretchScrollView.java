package com.efun.platform.widget.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 垂直彈性ScrollView
 * 
 * @author Jesse
 * 
 */
public class VerticalStretchScrollView extends ScrollView {
	private static final int MSG_REST_POSITION = 0x01;
	private static final int MAX_SCROLL_HEIGHT = 400;
	private static final float SCROLL_RATIO = 0.4f;
	private View mChildRootView;
	private float mTouchY;
	private boolean mTouchStop = false;
	private int mScrollY = 0;
	private int mScrollDy = 0;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (MSG_REST_POSITION == msg.what) {
				if (mScrollY != 0 && mTouchStop) {
					mScrollY -= mScrollDy;
					if ((mScrollDy < 0 && mScrollY > 0)
							|| (mScrollDy > 0 && mScrollY < 0)) {
						mScrollY = 0;
					}
					mChildRootView.scrollTo(0, mScrollY);
					sendEmptyMessageDelayed(MSG_REST_POSITION, 20);
				}
			}
		}
	};

	public VerticalStretchScrollView(Context context) {
		super(context);
		init();
	}

	public VerticalStretchScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public VerticalStretchScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@SuppressLint("NewApi")
	private void init() {
		// set scroll mode
		setOverScrollMode(OVER_SCROLL_NEVER);
	}

	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			// when finished inflating from layout xml, get the first child view
			mChildRootView = getChildAt(0);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			mTouchY = ev.getY();
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (null != mChildRootView) {
			doTouchEvent(ev);
		}
		return super.onTouchEvent(ev);
	}

	private void doTouchEvent(MotionEvent ev) {
		int action = ev.getAction();

		switch (action) {
		case MotionEvent.ACTION_UP:
			mScrollY = mChildRootView.getScrollY();
			if (mScrollY != 0) {
				mTouchStop = true;
				mScrollDy = (int) (mScrollY / 10.0f);
				mHandler.sendEmptyMessage(MSG_REST_POSITION);
			}
			break;

		case MotionEvent.ACTION_MOVE:
			float nowY = ev.getY();
			int deltaY = (int) (mTouchY - nowY);
			mTouchY = nowY;
			if (isNeedMove()) {
				int offset = mChildRootView.getScrollY();
				if (offset < MAX_SCROLL_HEIGHT && offset > -MAX_SCROLL_HEIGHT) {
					mChildRootView.scrollBy(0, (int) (deltaY * SCROLL_RATIO));
					mTouchStop = false;
				}
			}
			break;

		default:
			break;
		}
	}

	private boolean isNeedMove() {
		int viewHeight = mChildRootView.getMeasuredHeight();
		int scrollHeight = getHeight();
		int offset = viewHeight - scrollHeight;
		int scrollY = getScrollY();
		return scrollY == 0 || scrollY == offset;
	}
}