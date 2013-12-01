/**
 * Copyright 2012 Novoda Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.novoda.imageloader.core.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.novoda.imageloader.core.cache.util.LruCache;

/**
 * LruBitmapCache overcome the issue with soft reference cache. It is in fact keeping all the certain amount of images in memory. The size of the memory used for cache depends on the memory that the
 * android SDK provide to the application and the percentage specified (default percentage is 25%).
 */
public class LruBitmapCache {

	public static final int DEFAULT_MEMORY_CACHE_PERCENTAGE = 25;
	private static final int DEFAULT_MEMORY_CAPACITY_FOR_DEVICES_OLDER_THAN_API_LEVEL_4 = 12;
	private LruCache<BitmapDigest, Bitmap> cache;
	private long capacity;

	/**
	 * It is possible to set a specific percentage of memory to be used only for images.
	 * 
	 * @param context
	 * @param percentageOfMemoryForCache
	 *            1-80
	 */
	public LruBitmapCache(Context context, int percentageOfMemoryForCache) {
		int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "LruBitmapCache() memClass1 -->> " + memClass);
		}

		if (memClass == 0) {
			memClass = DEFAULT_MEMORY_CAPACITY_FOR_DEVICES_OLDER_THAN_API_LEVEL_4;
		}

		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "LruBitmapCache() memClass2 -->> " + memClass);
		}

		if (percentageOfMemoryForCache < 0) {
			percentageOfMemoryForCache = 0;
		}
		if (percentageOfMemoryForCache > 81) {
			percentageOfMemoryForCache = 80;
		}
		this.capacity = (1024L * 1024L * (memClass * percentageOfMemoryForCache)) / 100L;
		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "LruBitmapCache() capacity1 -->> " + capacity);
		}
		if (this.capacity <= 0) {
			this.capacity = 1024 * 1024 * 4;
			if (Log.D) {
				Log.d(LruBitmapCache.class.getName(), "LruBitmapCache() capacity2 -->> " + capacity);
			}
		}
		reset();
	}

	/**
	 * Setting the default memory size to 25% percent of the total memory available of the application.
	 * 
	 * @param context
	 */
	public LruBitmapCache(Context context) {
		this(context, DEFAULT_MEMORY_CACHE_PERCENTAGE);
	}

	private void reset() {
		if (cache != null) {
			cache.evictAll();
		}
		cache = new LruCache<BitmapDigest, Bitmap>(capacity) {
			@Override
			protected long sizeOf(BitmapDigest key, Bitmap bitmap) {
				if (Log.D) {
					Log.d(LruBitmapCache.class.getName(), "sizeOf() bitmapRowBytes=" + bitmap.getRowBytes() + //
							" bitmapWidth="+bitmap.getWidth()+//
							" bitmapHeight=" + bitmap.getHeight() + //
							" size=" + (bitmap.getWidth() * bitmap.getHeight() * 4L) + //
							" -->> ");
				}
				return bitmap.getWidth() * bitmap.getHeight() * 4L;
			}

			@Override
			protected void entryRemoved(boolean evicted, BitmapDigest key, Bitmap oldValue, Bitmap newValue) {
				if (Log.D) {
					Log.d(LruBitmapCache.class.getName(), "entryRemoved() bitmapDigest -->> " + key);
					Log.d(LruBitmapCache.class.getName(), "entryRemoved() bitmap -->> " + oldValue);
				}
				if(key.isAllowRecycle()){
					oldValue.recycle();
				}
				if(evicted){
					GlobalImageCache.remove(key);
				}
			}
		};
	}

	public Bitmap get(BitmapDigest bd) {
		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "get() bitmapDigest -->> " + bd);
		}
		return cache.get(bd);
	}

	public void put(BitmapDigest bd, Bitmap bmp) {
		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "put() bitmapDigest -->> " + bd);
		}
		cache.put(bd, bmp);
	}

	public void clean() {
		reset();
	}
	
	public void cleanMost(){
		long maxSize = Math.round(capacity * 0.1D);
		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "cleanMost() maxSize -->> " + maxSize);
		}
		cache.evict(maxSize);
	}
	
	public void remove(BitmapDigest bd){
		if (Log.D) {
			Log.d(LruBitmapCache.class.getName(), "remove() bitmapDigest -->> " + bd);
		}
		cache.remove(bd);
	}

}
