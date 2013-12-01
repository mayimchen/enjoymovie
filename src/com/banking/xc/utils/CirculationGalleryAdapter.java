package com.banking.xc.utils;

import java.util.List;

import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import skytv_com.banking.enjoymovie.R;

import com.banking.xc.utils.adapter.circulation.CirculationSimpleImageProcessor;

public class CirculationGalleryAdapter extends MySimpleAdapter {

	private List<?> beanList;

	public CirculationGalleryAdapter(MyActivity myActivity, List<?> beanList, int itemId, String[] from, int[] to) {
		super(myActivity, beanList, itemId, from, to);
		setViewBinder(new SimpleSubViewBinder(new CirculationSimpleImageProcessor()));
		this.beanList = beanList;
		TypedArray a = myActivity.obtainStyledAttributes(R.styleable.gallery_bg);
		a.recycle();
	}

	@Override
	public int getCount() {
		if (getActualCount() <= 3) {
			return getActualCount();
		} else {
			return Integer.MAX_VALUE;// 欺骗总数
		}
	}

	/**
	 * 真实数量
	 */
	public int getActualCount() {
		return (null == beanList ? 0 : beanList.size());
	}

	/**
	 * 真实位置
	 */
	public int toActualPosition(int position) {
		return position % getActualCount();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(Log.D){
			Log.d("----", "CirculationGalleryAdapter position-->> " + position);
		}
		return super.getView(toActualPosition(position), convertView, parent);
	}

	@Override
	public Object getItem(int position) {
		if (null == beanList || beanList.size() < 1) {
			return null;
		} else {
			return beanList.get(toActualPosition(position));
		}
	}

	public void init(Gallery gallery) {
		int mediant = Math.abs(Integer.MAX_VALUE / 2);
		//int select = mediant - (mediant % getActualCount());
		gallery.setSelection(mediant);
	}

}
