package com.banking.xc.utils;

import skytv_com.banking.enjoymovie.MyApplication;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.banking.xc.utils.SimpleBeanAdapter.SubViewHolder;
import com.banking.xc.utils.adapter.SimpleImageProcessor;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.banking.xc.utils.cache.GlobalImageCache.ImageState;

public class SimpleSubViewBinder implements SubViewBinder {

	private static final String TAG = "SimpleSubViewBinder";
	private static final ExceptionDrawable noPictureDrawable = new ExceptionDrawable(MyApplication.getInstance(),"",ExceptionDrawable.NOPICTURE);
	private SimpleImageProcessor imageProcessor;

	public SimpleSubViewBinder(SimpleImageProcessor imageProcessor) {
		this.imageProcessor = imageProcessor;
	}

	@Override
	public boolean bind(SubViewHolder h) {
		if (Log.D) {
			Log.d(SimpleSubViewBinder.class.getName(), "bind() -->> ");
		}

		if (subBind(h)) {
			return true;
		}

		View v = h.getSubView();
		Object data = h.getSubData();

		if (v instanceof Checkable && data instanceof Boolean) {
			// Checkable 自动绑定
			if (Log.D) {
				Log.d(SimpleSubViewBinder.class.getName(), "bind() checkable -->> ");
			}
			((Checkable) v).setChecked((Boolean) data);
			return true;
		} else if (v instanceof TextView && data instanceof CharSequence) {
			// TextView 自动绑定
			if (Log.D) {
				Log.d(SimpleSubViewBinder.class.getName(), "bind() text -->> ");
			}
			((TextView) v).setText((CharSequence) data);
			return true;
		}
		//new added
		else if(v instanceof RatingBar&& data instanceof Float){
			((RatingBar)v).setRating((Float)data);
		}
		else if (v instanceof ImageView) {
			if(data == null){
				((ImageView) v).setImageDrawable(noPictureDrawable);
				return true;
			}
			if (Log.D) {
				Log.d(SimpleSubViewBinder.class.getName(), "bind() image data -->>"+data);
			}
			// ImageView 自动绑定
			if (data instanceof Integer) {
				// id形式
				if (Log.D) {
					Log.d(SimpleSubViewBinder.class.getName(), "bind() image id 1 -->> ");
				}
				((ImageView) v).setImageResource((Integer) data);
				//((ImageView) v).setBackgroundResource(Integer.parseInt((String) data));
				return true;
			} else if (data instanceof String) {
				try {
					// id形式
					if (Log.D) {
						Log.d(SimpleSubViewBinder.class.getName(), "bind() image id 2 -->> ");
					}
					((ImageView) v).setImageResource(Integer.parseInt((String) data));
					//((ImageView) v).setBackgroundResource(Integer.parseInt((String) data));
					return true;
				} catch (NumberFormatException e) {
					//还有汉字情况...搜索“都”,parser问题
					//TODO 
					String str = (String) data;
					if(Log.D){
						Log.d(TAG, "String ImageData"+str);
					}
					if(str.startsWith("//")){
						//处理拿到的数据少了http的情况
						str = "http:"+str;
					}
					if (!str.startsWith("http")) {
						// uri形式
						if (Log.D) {
							Log.d(SimpleSubViewBinder.class.getName(), "bind() image uri -->> ");
						}
						((ImageView) v).setImageURI(Uri.parse(str));
						return true;
					} else {
						// url形式
						if (Log.D) {
							Log.d(SimpleSubViewBinder.class.getName(), "bind() image url -->> ");
						}
						BitmapDigest bitmapDigest = new GlobalImageCache.BitmapDigest(str);
						if (Log.D) {
							Log.d(SimpleSubViewBinder.class.getName(), "bind() bitmapDigest -->> " + bitmapDigest);
						}

						LayoutParams layoutParams = v.getLayoutParams();
						if (layoutParams == null || layoutParams.width < 1 || layoutParams.height < 1) {
							v.measure(DPIUtil.dip2px(ImageUtil.IMAGE_MAX_WIDTH), DPIUtil.dip2px(ImageUtil.IMAGE_MAX_HEIGHT));
							layoutParams = v.getLayoutParams();
						}
						if (layoutParams.width > 0) {
							if (Log.D) {
								Log.d(SimpleSubViewBinder.class.getName(), "layoutParams.width -->> " + layoutParams.width);
							}
							bitmapDigest.setWidth(layoutParams.width);
						}
						if (layoutParams.height > 0) {
							if (Log.D) {
								Log.d(SimpleSubViewBinder.class.getName(), "layoutParams.height -->> " + layoutParams.height);
							}
							bitmapDigest.setHeight(layoutParams.height);
						}

						ImageState imageState = GlobalImageCache.getImageState(bitmapDigest);
						if (Log.D) {
							Log.d(SimpleSubViewBinder.class.getName(), "bind() imageState -->> " + imageState);
						}
						imageProcessor.show(h, imageState);
						return true;
					}
				}
			}
		}
		if (Log.D) {
			Log.d(SimpleSubViewBinder.class.getName(), "bind() return false -->> ");
		}
		return false;
	}

	public SimpleImageProcessor getSimpleImageProcessor() {
		return imageProcessor;
	}

	protected boolean subBind(SubViewHolder h) {
		return false;
	}

}
