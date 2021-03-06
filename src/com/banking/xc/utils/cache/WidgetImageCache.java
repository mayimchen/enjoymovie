package com.banking.xc.utils.cache;

import java.util.HashMap;
import java.util.Iterator;

import android.graphics.Bitmap;

public class WidgetImageCache {
	
	protected static HashMap<String, Bitmap> bitmapCache = new HashMap<String, Bitmap>();
	
	public static synchronized void clearAllBitmap() {
		Bitmap reference ;
	Iterator<String>  iterator = bitmapCache.keySet().iterator();
		while(iterator.hasNext()){
			reference = bitmapCache.get(iterator.next());
			if(reference!=null&&reference!=null){
				reference.recycle();
			}
			
		}
		bitmapCache.clear();
	}

	public static synchronized boolean containsKeyBitmap(Object key) {
		return bitmapCache.containsKey(key);
	}

	public static synchronized Bitmap getBitmap(Object key) {
		return bitmapCache.get(key);
	}

	public static synchronized Bitmap putBitmap(String key, Bitmap value) {
		return bitmapCache.put(key, value);
	}

}
