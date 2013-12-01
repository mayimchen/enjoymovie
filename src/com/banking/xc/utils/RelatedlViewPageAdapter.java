package com.banking.xc.utils;

import java.util.List;

import skytv_com.banking.enjoymovie.MyApplication;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class RelatedlViewPageAdapter extends PagerAdapter{
	private final String TAG = RelatedlViewPageAdapter.class.getSimpleName();
	private MyActivity mMyActivity;
	//服务端下发的滑屏内容
	//private List<JdSlidingData> jdSlidingData;
	//滑屏的view 数组
	private View[] viewArray;
	
	public RelatedlViewPageAdapter(MyActivity myActivity) {
		if (Log.D) {
			Log.d("Temp", "OrderDetialGalleryAdapter position-->> ");
		}
		this.mMyActivity = myActivity;
		//setViewPagerData();
		viewArray = new View[2];
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

	@Override
	public int getCount() {
		return viewArray.length;
	}

	@Override
	public synchronized Object instantiateItem(final ViewGroup container, int position) {
		if (Log.D) {
			Log.d(TAG, "instantiateItem position-->> " + position);
			Log.d(TAG, "instantiateItem container-->> " + container);
		}
		View item = viewArray[position];
		if (item == null){
			try {
				/*final SlideScreen slideScreen = new SlideScreen(mMyActivity);
				slideScreen.setViewPager((ViewPager) container);*/
				final LocalActivityManager manager = MyApplication.getInstance().getMainActivity().getLocalActivityManager();
				Intent intent = null;
				switch(position){
					case 0:
						item = manager.startActivity("filter-" + position + System.currentTimeMillis(), intent).getDecorView();
						break;
						//暂时不做团购
					/*case 1:
						intent = new Intent(mMyActivity, GroupPurchaseListActivity.class);
						item = manager.startActivity("filter-" + position + System.currentTimeMillis(), intent).getDecorView();
						break;*/
					case 1:
						item = manager.startActivity("filter-" + position + System.currentTimeMillis(), intent).getDecorView();
				}
				
				if(null == item){
					return null;
				}
				viewArray[position] = item;
				((ViewPager) container).addView(item);
			} catch (Exception e) {
				if(Log.D){
					e.printStackTrace();
				}
			}
		}
		return item;
	}

	@Override
	public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
		//设置remove的状态，内容到一定容量时remove TODO：
//		((ViewPager) arg0).removeView((View) arg2);
	}
	
	@Override  
	public int getItemPosition(Object object) {  
	    return POSITION_NONE;  
	}
	
}
