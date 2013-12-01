package com.banking.xc.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class JDGridView extends GridView {

	public JDGridView(Context context) {
		super(context);
	}

	public JDGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public JDGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置不滚动
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
