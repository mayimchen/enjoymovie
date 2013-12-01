/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.banking.xc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ListView.FixedViewInfo;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.banking.xc.utils.adapter.SimpleImageProcessor;
import com.banking.xc.utils.adapter.UIRunnable;

/**
 * An easy adapter to map static data to views defined in an XML file. You can specify the data backing the list as an ArrayList of Maps. Each entry in the ArrayList corresponds to one row in the
 * list. The Maps contain the data for each row. You also specify an XML file that defines the views used to display the row, and a mapping from keys in the Map to specific views.
 * 
 * Binding data to views occurs in two phases. First, if a {@link android.widget.SimpleAdapter.ViewBinder} is available, {@link ViewBinder#setViewValue(android.view.View, Object, String)} is invoked.
 * If the returned value is true, binding has occurred. If the returned value is false, the following views are then tried in order:
 * <ul>
 * <li>A view that implements Checkable (e.g. CheckBox). The expected bind value is a boolean.
 * <li>TextView. The expected bind value is a string and {@link #setViewText(TextView, String)} is invoked.
 * <li>ImageView. The expected bind value is a resource id or a string and {@link #setViewImage(ImageView, int)} or {@link #setViewImage(ImageView, String)} is invoked.
 * </ul>
 * If no appropriate binding can be found, an {@link IllegalStateException} is thrown.
 */
public class SimpleBeanAdapter extends BaseAdapter implements Filterable {

	private int[] mTo;
	private String[] mFrom;

	private SubViewBinder mViewBinder;

	private List<?> mData;

	private int mResource;
	private int mDropDownResource;
	private LayoutInflater mInflater;

	private SimpleFilter mFilter;
	private List<?> mUnfilteredData;

	private AdapterHelper adapterHelper = new AdapterHelper();

	/**
	 * 本实例是否允许无图版功能生效
	 */
	private boolean allowNoImage = true;

	private boolean isAssetsCache = false; // 获取打包模式缓存数据

	/**
	 * header与footer
	 */
	private ArrayList<FixedViewInfo> headerViewInfos;
	private ArrayList<FixedViewInfo> footerViewInfos;
	private HeaderViewListAdapter headerViewListAdapter;

	/**
	 * 延迟通知标志位 用于确保已经有延迟通知时不要重复添加延迟任务
	 */
	private Vector uiRunnables = new Vector();

	/**
	 * Constructor
	 * 
	 * @param context
	 *            The context where the View associated with this SimpleAdapter is running
	 * @param data
	 *            A List of Maps. Each entry in the List corresponds to one row in the list. The Maps contain the data for each row, and should include all the entries specified in "from"
	 * @param resource
	 *            Resource identifier of a view layout that defines the views for this list item. The layout file should include at least those named views defined in "to"
	 * @param from
	 *            A list of column names that will be added to the Map associated with each item.
	 * @param to
	 *            The views that should display column in the "from" parameter. These should all be TextViews. The first N views in this list are given the values of the first N columns in the from
	 *            parameter.
	 */
	public SimpleBeanAdapter(Context context, List<?> data, int resource, String[] from, int[] to) {
		mData = data;
		mResource = mDropDownResource = resource;
		mFrom = from;
		mTo = to;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 添加所有Bean
	 */
	public void addAllBean(Collection list) {
		mData.addAll(list);
	}

	/**
	 * 清除当前所有Bean，使用新加入的Bean
	 */
	public void replaceAllBean(Collection list) {
		// beanList.clear();// 如果用此方法会导致订单列表的gallery高度为0
		int size = mData.size();
		addAllBean(list);
		for (int i = 0; i < size; i++) {
			mData.remove(0);
		}
	}

	/**
	 * 更新数据源
	 */
	public void setData(List<Object> data) {
		this.mData = data;
	}

	/**
	 * 本实例是否允许无图版功能生效
	 */
	public boolean isAllowNoImage() {
		return allowNoImage;
	}

	/**
	 * 本实例是否允许无图版功能生效
	 */
	public boolean allowNoImageAndIsNoImage() {
		return allowNoImage && NoImageUtils.needNoImage();
	}

	/**
	 * 设置本实例是否允许无图版功能生效
	 */
	public void setAllowNoImage(boolean allowNoImage) {
		this.allowNoImage = allowNoImage;
	}

	public boolean isAssetsCache() {
		return isAssetsCache;
	}

	public void setAssetsCache(boolean isAssetsCache) {
		this.isAssetsCache = isAssetsCache;
	}

	/**
	 * 
	 */
	public AdapterHelper getAdapterHelper() {
		return adapterHelper;
	}

	/**
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
		if (mData != null) {
			return mData.size();
		} else {
			return 0;
		}
	}

	/**
	 * @see android.widget.Adapter#getItem(int)
	 */
	public Object getItem(int position) {
		if (null != mData && position >= 0 && position < mData.size()) {
			return mData.get(position);
		} else {
			return null;
		}
	}

	/**
	 * @see android.widget.Adapter#getItemId(int)
	 */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @see android.widget.Adapter#getView(int, View, ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		if (parent instanceof AdapterView) {
			adapterHelper.setAdapterView((AdapterView) parent);
		}
		return getViewFromResource(position, convertView, parent, mResource);
	}

	private View getViewFromResource(int position, View convertView, ViewGroup parent, int resource) {

		View v;

		if (convertView == null) {// 
			v = createViewFromResource(parent, resource);
		} else {
			v = convertView;
		}

		try {
			bindView(position, v);
		} catch (Exception e) {
			if (Log.D) {
				Log.d(SimpleBeanAdapter.class.getName(), "bindView() Exception -->> " + e.getMessage(), e);
			}
		}

		return v;
	}

	private View createViewFromResource(ViewGroup parent, int resource) {
		final View v = ImageUtil.inflate(resource, parent, false);

		final int[] to = mTo;
		final int count = to.length;

		Map<Integer, View> map = new HashMap<Integer, View>();
		for (int i = 0; i < count; i++) {
			map.put(to[i], v.findViewById(to[i]));
		}
		adapterHelper.putSubViews(v, map);
		return v;
	}

	/**
	 * <p>
	 * Sets the layout resource to create the drop down views.
	 * </p>
	 * 
	 * @param resource
	 *            the layout resource defining the drop down views
	 * @see #getDropDownView(int, android.view.View, android.view.ViewGroup)
	 */
	public void setDropDownViewResource(int resource) {
		this.mDropDownResource = resource;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getViewFromResource(position, convertView, parent, mDropDownResource);
	}

	private void bindView(int position, View view) {
		Object dataSet = null;
		dataSet = getItem(position);
		if (dataSet == null || null == view) {
			return;
		}

		final SubViewBinder binder = getViewBinder();

		final String[] from = mFrom;
		final int[] to = mTo;
		final int count = to.length;

		for (int i = 0; i < count; i++) {
			if (Log.D) {
				Log.d(SimpleBeanAdapter.class.getName(), "bindView() for from[i] -->> " + from[i]);
			}
			View v = adapterHelper.getSubView(view, to[i]);

			if (v != null) {
				Object data = null;

				data = BeanUtil.getValue(dataSet, from[i]);

				String text = data == null ? "" : data.toString();
				if (text == null) {
					text = "";
				}

				if (binder != null) {
					SubViewHolder h = new SubViewHolder();
					h.setAdapter(this);
					h.setItemData(dataSet);
					h.setSubData(data);
					h.setItemView(view);
					h.setSubView(v);
					h.setPosition(position);
					h.setSubViewId(to[i]);

					binder.bind(h);
				}
			}
		}
	}

	public SubViewBinder getViewBinder() {
		if (null == mViewBinder) {
			mViewBinder = new SimpleSubViewBinder(new SimpleImageProcessor());
		}
		return mViewBinder;
	}

	public void setViewBinder(SubViewBinder viewBinder) {
		mViewBinder = viewBinder;
	}

	/**
	 * 如果需要置顶或置底view，setAdapter的时候请使用本方法
	 */
	public HeaderViewListAdapter getHeaderViewListAdapter() {
		if (null == headerViewListAdapter) {
			headerViewInfos = new ArrayList<FixedViewInfo>();
			footerViewInfos = new ArrayList<FixedViewInfo>();
			headerViewListAdapter = new HeaderViewListAdapter(headerViewInfos, footerViewInfos, this);
		}
		return headerViewListAdapter;
	}

	/**
	 * 添加置顶view
	 */
	public void addHeaderView(ListView listView, View view) {
		if (Log.D) {
			Log.d(SimpleBeanAdapter.class.getName(), "addHeaderView() view -->> " + view);
		}
		if (MyApplication.getInstance().getUiThread() != Thread.currentThread()) {
			throw new RuntimeException("must run in ui thread");
		}

		if (null == headerViewListAdapter) {
			return;
		}

		FixedViewInfo info = listView.new FixedViewInfo();
		info.view = view;
		info.data = null;
		info.isSelectable = true;
		headerViewInfos.add(info);
		notifyDataSetChanged();
	}

	/**
	 * 移除置顶view
	 */
	public boolean removeHeaderView(View view) {
		if (MyApplication.getInstance().getUiThread() != Thread.currentThread()) {
			throw new RuntimeException("must run in ui thread");
		}

		if (null == headerViewListAdapter) {
			return false;
		}

		if (headerViewInfos.size() > 0) {
			boolean result = false;
			if (headerViewListAdapter.removeHeader(view)) {
				notifyDataSetChanged();
				result = true;
			}
			removeFixedViewInfo(view, headerViewInfos);
			return result;
		}
		return false;
	}

	/**
	 * 添加置底view
	 */
	public void addFooterView(ListView listView, View view) {
		if (Log.D) {
			Log.d(SimpleBeanAdapter.class.getName(), "addFooterView() view -->> " + view);
		}
		if (MyApplication.getInstance().getUiThread() != Thread.currentThread()) {
			throw new RuntimeException("must run in ui thread");
		}

		if (null == footerViewInfos) {
			return;
		}

		FixedViewInfo info = listView.new FixedViewInfo();
		info.view = view;
		info.data = null;
		info.isSelectable = true;
		footerViewInfos.add(info);

		notifyDataSetChanged();
	}

	/**
	 * 移除置底view
	 */
	public boolean removeFooterView(View view) {
		if (MyApplication.getInstance().getUiThread() != Thread.currentThread()) {
			throw new RuntimeException("must run in ui thread");
		}

		if (null == headerViewListAdapter) {
			return false;
		}

		if (footerViewInfos.size() > 0) {
			boolean result = false;
			if (headerViewListAdapter.removeFooter(view)) {
				notifyDataSetChanged();
				result = true;
			}
			removeFixedViewInfo(view, footerViewInfos);
			return result;
		}
		return false;
	}

	/**
	 * 辅助移除置顶和置底view
	 */
	private void removeFixedViewInfo(View v, ArrayList<FixedViewInfo> where) {
		int len = where.size();
		for (int i = 0; i < len; ++i) {
			FixedViewInfo info = where.get(i);
			if (info.view == v) {
				where.remove(i);
				break;
			}
		}
	}

	public synchronized void UIWorkCentralized(UIRunnable runnable) {
		if (uiRunnables.size() <= 0) {
			uiRunnables.add(runnable);
			MyApplication.getInstance().getHandler().postDelayed(new Runnable() {
				public void run() {
					synchronized (SimpleBeanAdapter.this) {
						for (Iterator itr = uiRunnables.iterator(); itr.hasNext();) {
							UIRunnable r = (UIRunnable) itr.next();
							r.run();
						}
						uiRunnables.clear();
					}
				}
			}, 600);
		} else {
			uiRunnables.add(runnable);
		}
	}

	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new SimpleFilter();
		}
		return mFilter;
	}

	/**
	 * 携带参数贯穿adapter的多层机制<br />
	 * 请勿长期持有，如需长期持有，请尽量减持
	 */
	public static class SubViewHolder {

		public static final String MORE_PARAMETER_MANUAL_GET_IMAGE = "manualGetImage";
		public static final String MORE_PARAMETER_LOCAL_LOAD_IMAGE = "localLoadImage";
		public static final String MORE_PARAMETER_LOADED = "loaded";

		private SimpleBeanAdapter adapter;

		private View itemView;
		private View subView;

		private int position = 0;
		private int subViewId = 0;
		private Object itemData;
		private Object subData;

		/**
		 * 用于携带额外的参数<br />
		 * 无图版当用户长按图片后会在这带一个参数
		 */
		private Map<String, Object> moreParameter;

		public SubViewHolder() {
		}

		/**
		 * 不要滥用此方法，错误地多带参数很可能会出问题，最好自己用空构造函数，只把必须的参数加进去
		 */
		public SubViewHolder(SubViewHolder sourceSubViewHolder) {
			setAdapter(sourceSubViewHolder.getAdapter());
			setPosition(sourceSubViewHolder.getPosition());
			setSubViewId(sourceSubViewHolder.getSubViewId());
			setItemData(sourceSubViewHolder.getItemData());
			setSubData(sourceSubViewHolder.getSubData());
			setItemView(sourceSubViewHolder.getItemView());
			setSubView(sourceSubViewHolder.getSubView());
		}

		public SimpleBeanAdapter getAdapter() {
			return adapter;
		}

		public void setAdapter(SimpleBeanAdapter adapter) {
			this.adapter = adapter;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public int getSubViewId() {
			return subViewId;
		}

		public void setSubViewId(int subViewId) {
			this.subViewId = subViewId;
		}

		public View getItemView() {
			return itemView;
		}

		public void setItemView(View itemView) {
			this.itemView = itemView;
		}

		public Object getItemData() {
			return itemData;
		}

		public void setItemData(Object itemData) {
			this.itemData = itemData;
		}

		public View getSubView() {
			return subView;
		}

		public void setSubView(View subView) {
			this.subView = subView;
		}

		public Object getSubData() {
			return subData;
		}

		public void setSubData(Object subData) {
			this.subData = subData;
		}

		/**
		 * 获得携带的额外的参数
		 */
		public Object getMoreParameter(String key) {
			if (null == moreParameter) {
				return null;
			}
			return moreParameter.get(key);
		}

		/**
		 * 用于携带额外的参数
		 */
		public void putMoreParameter(String key, Object value) {
			if (null == moreParameter) {
				moreParameter = new HashMap<String, Object>();
			}
			moreParameter.put(key, value);
		}

		@Override
		public String toString() {
			return "SubViewHolder [adapter=" + adapter + ", itemData=" + itemData + ", itemView=" + itemView + ", position=" + position + ", subData=" + subData + ", subView=" + subView + ", subViewId=" + subViewId + "]";
		}

	}

	/**
	 * <p>
	 * An array filters constrains the content of the array adapter with a prefix. Each item that does not start with the supplied prefix is removed from the list.
	 * </p>
	 */
	private class SimpleFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			FilterResults results = new FilterResults();

			if (mUnfilteredData == null) {
				mUnfilteredData = mData;// XXX new ArrayList<Map<String, ?>>(mData);
			}

			if (prefix == null || prefix.length() == 0) {
				List<?> list = mUnfilteredData;
				results.values = list;
				results.count = list.size();
			} else {
				String prefixString = prefix.toString().toLowerCase();

				List<?> unfilteredValues = mUnfilteredData;
				int count = unfilteredValues.size();

				List<Object> newValues = new LinkedList<Object>();// new ArrayList<Map<String, ?>>(count);

				for (int i = 0; i < count; i++) {
					Object h = null;

					h = unfilteredValues.get(i);

					if (h != null) {

						int len = mTo.length;

						for (int j = 0; j < len; j++) {
							String str = null;

							str = (String) BeanUtil.getValue(h, mFrom[j]);

							String[] words = str.split(" ");
							int wordCount = words.length;

							for (int k = 0; k < wordCount; k++) {
								String word = words[k];

								if (word.toLowerCase().startsWith(prefixString)) {
									newValues.add(h);
									break;
								}
							}
						}
					}
				}

				results.values = newValues;
				results.count = newValues.size();
			}

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			// noinspection unchecked
			mData = (List<Object>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
	}

	public void gc() {
		mViewBinder = null;
		mData = null;
		mUnfilteredData = null;
		// adapterHelper = null;// TODO 由于后退会崩溃因此先注释掉
	}

}
