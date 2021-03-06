package com.banking.xc.utils.adapter.circulation;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.banking.xc.utils.AdapterHelper;
import com.banking.xc.utils.CirculationGalleryAdapter;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.SimpleBeanAdapter;
import com.banking.xc.utils.SimpleBeanAdapter.SubViewHolder;
import com.banking.xc.utils.adapter.SimpleImageProcessor;
import com.banking.xc.utils.adapter.UIRunnable;
import com.banking.xc.utils.cache.GlobalImageCache.ImageState;

public class CirculationSimpleImageProcessor extends SimpleImageProcessor {

	@Override
	protected UIRunnable provideUIRunnable(SubViewHolder subViewHolder, ImageState imageState) {
		return new CirculationUIRunnable(subViewHolder, imageState);
	}

	private class CirculationUIRunnable extends UIRunnable {

		public CirculationUIRunnable(SubViewHolder subViewHolder, ImageState imageState) {
			super(subViewHolder, imageState);
		}

		protected View getItemView() {
			if (Log.D) {
				Log.d(CirculationSimpleImageProcessor.class.getName(), "getItemView() -->> ");
			}
			SimpleBeanAdapter adapter = getSubViewHolder().getAdapter();
			AdapterHelper adapterHelper = adapter.getAdapterHelper();
			AdapterView<Adapter> adapterView = adapterHelper.getAdapterView();

			if (Log.D) {
				Log.d(CirculationSimpleImageProcessor.class.getName(), "getItemView() adapter -->> " + adapter);
			}
			if (adapter instanceof CirculationGalleryAdapter) {
				CirculationGalleryAdapter a = (CirculationGalleryAdapter) adapter;

				if (Log.D) {
					Log.d(CirculationSimpleImageProcessor.class.getName(), "getSubViewHolder().getPosition() -->> " + getSubViewHolder().getPosition());
					Log.d(CirculationSimpleImageProcessor.class.getName(), "a.getActualCount() -->> " + a.getActualCount());
				}

				int firstVisiblePosition = adapterView.getFirstVisiblePosition() % a.getActualCount();
				if (Log.D) {
					Log.d(CirculationSimpleImageProcessor.class.getName(), "getItemView() firstVisiblePosition -->> " + firstVisiblePosition);
				}
				int childCount = adapterView.getChildCount();
				if (Log.D) {
					Log.d(CirculationSimpleImageProcessor.class.getName(), "getItemView() childCount -->> " + childCount);
				}
				int position = getSubViewHolder().getPosition();
				
				// 当前要处理的position处于无限循环的临界点时，有可能小于firstVisiblePosition但依然属于显示范围
				// 所以做如下处理
				if(position < firstVisiblePosition){
					position += a.getActualCount();
				}
				
				Integer index = AdapterHelper.getItemViewIndex(firstVisiblePosition, childCount, position);
				if (Log.D) {
					Log.d(CirculationSimpleImageProcessor.class.getName(), "getItemView() index -->> " + index);
				}
				if (null != index) {
					return adapterView.getChildAt(index);
				}
			}

			return null;
		}
		
	}

}
