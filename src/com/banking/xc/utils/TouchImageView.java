/**
 * PhotoSorterView.java
 * 
 * (c) Luke Hutchison (luke.hutch@mit.edu)
 * 
 * TODO: Add OpenGL acceleration.
 * 
 * Released under the Apache License v2.
 */
package com.banking.xc.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.banking.xc.utils.MultiTouchController.MultiTouchObjectCanvas;
import com.banking.xc.utils.MultiTouchController.PointInfo;
import com.banking.xc.utils.MultiTouchController.PositionAndScale;

public class TouchImageView extends ImageView implements MultiTouchObjectCanvas<TouchImageView.Img> {

	private Img img = null;
	// --
	private MultiTouchController<Img> multiTouchController = new MultiTouchController<Img>((MultiTouchObjectCanvas<Img>) this);
	// --
	private PointInfo currTouchPoint = new PointInfo();
	private boolean mShowDebugInfo = true;
	private static final int UI_MODE_ROTATE = 1, UI_MODE_ANISOTROPIC_SCALE = 2;
	private int mUIMode = UI_MODE_ROTATE;
	// --
//	private Paint mLinePaintTouchPointCircle = new Paint();

	// ---------------------------------------------------------------------------------------------------

	private static float SCREEN_MARGIN_WIDTH_RIGHT = 100;
	private static float SCREEN_MARGIN_WIDTH_LEFT = 100;
	private static float SCREEN_MARGIN_HEIGHT_BOTTOM = 100;
	private static float SCREEN_MARGIN_HEIGHT_TOP = 100;
	private static final int BOTTOM_FIX = 32;
	private int galleryHeight;
	
	public TouchImageView(Context context) {
		this(context, null);
	}

	public TouchImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TouchImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

    public void init(Context context, Bitmap bitmap, int galleryHeight) {
		Resources res = context.getResources();
		this.galleryHeight = galleryHeight;
		SCREEN_MARGIN_HEIGHT_BOTTOM += (float)galleryHeight + BOTTOM_FIX;
		img = null;
		img = new Img(bitmap, res);
		loadImages(context);
//		mLinePaintTouchPointCircle.setColor(Color.YELLOW);
//		mLinePaintTouchPointCircle.setStrokeWidth(5);
//		mLinePaintTouchPointCircle.setStyle(Style.STROKE);
//		mLinePaintTouchPointCircle.setAntiAlias(true);
		setBackgroundColor(Color.WHITE);
	}
    
	/** Called by activity's onResume() method to load the images */
	public void loadImages(Context context) {
		Resources res = context.getResources();
		img.load(res);
	}

	// ---------------------------------------------------------------------------------------------------

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		img.draw(canvas);
		if (mShowDebugInfo)
			drawMultitouchDebugMarks(canvas);
	}

	// ---------------------------------------------------------------------------------------------------

	public void trackballClicked() {
		mUIMode = (mUIMode + 1) % 3;
		invalidate();
	}

	private void drawMultitouchDebugMarks(Canvas canvas) {
		if (currTouchPoint.isDown()) {
//			float[] xs = currTouchPoint.getXs();
//			float[] ys = currTouchPoint.getYs();
//			float[] pressures = currTouchPoint.getPressures();
//			int numPoints = Math.min(currTouchPoint.getNumTouchPoints(), 2);
//			for (int i = 0; i < numPoints; i++)
//				canvas.drawCircle(xs[i], ys[i], 50 + pressures[i] * 80, mLinePaintTouchPointCircle);
		}
	}

	// ---------------------------------------------------------------------------------------------------

	/** Pass touch events to the MT controller */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return multiTouchController.onTouchEvent(event);
	}

	/** Get the image that is under the single-touch point, or return null (canceling the drag op) if none */
	public Img getDraggableObjectAtPoint(PointInfo pt) {
//		float x = pt.getX(), y = pt.getY();
//		if (img.containsPoint(x, y))
			return img;
//		return null;
	}

	/**
	 * Select an object for dragging. Called whenever an object is found to be under the point (non-null is returned by getDraggableObjectAtPoint())
	 * and a drag operation is starting. Called with null when drag op ends.
	 */
	public void selectObject(Img img, PointInfo touchPoint) {
		currTouchPoint.set(touchPoint);
		if (img != null) {
			// Move image to the top of the stack when selected
		} else {
			// Called with img == null when drag stops.
		}
		invalidate();
	}

	/** Get the current position and scale of the selected image. Called whenever a drag starts or is reset. */
	public void getPositionAndScale(Img img, PositionAndScale objPosAndScaleOut) {
		// FIXME affine-izem (and fix the fact that the anisotropic_scale part requires averaging the two scale factors)
		objPosAndScaleOut.set(img.getCenterX(), img.getCenterY(), (mUIMode & UI_MODE_ANISOTROPIC_SCALE) == 0,
				(img.getScaleX() + img.getScaleY()) / 2, (mUIMode & UI_MODE_ANISOTROPIC_SCALE) != 0, img.getScaleX(), img.getScaleY(),
				(mUIMode & UI_MODE_ROTATE) != 0);
	}

	/** Set the position and scale of the dragged/stretched image. */
	public boolean setPositionAndScale(Img img, PositionAndScale newImgPosAndScale, PointInfo touchPoint) {
		currTouchPoint.set(touchPoint);
		boolean ok = img.setPos(newImgPosAndScale);
		if (ok)
			invalidate();
		return ok;
	}

	// ----------------------------------------------------------------------------------------------

	public class Img {
		private Bitmap bitmap;

		private Drawable drawable;

		private boolean firstLoad;

		private int width, height, displayWidth, displayHeight;

		private float centerX, centerY, scaleX, scaleY;

		private float minX, maxX, minY, maxY;

		public Img(Bitmap bitmap, Resources res) {
			this.bitmap = bitmap;
			this.firstLoad = true;
			getMetrics(res);
		}

		private void getMetrics(Resources res) {
			DisplayMetrics metrics = res.getDisplayMetrics();
			// The DisplayMetrics don't seem to always be updated on screen rotate, so we hard code a portrait
			// screen orientation for the non-rotated screen here...
			this.displayWidth = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math.max(metrics.widthPixels,
					metrics.heightPixels) : Math.min(metrics.widthPixels, metrics.heightPixels);
			this.displayHeight = res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? Math.min(metrics.widthPixels,
					metrics.heightPixels) : Math.max(metrics.widthPixels, metrics.heightPixels) - galleryHeight;
		}

		/** Called by activity's onResume() method to load the images */
		public void load(Resources res) {
			getMetrics(res);
			this.drawable = new BitmapDrawable(bitmap);
			this.width = drawable.getIntrinsicWidth();
			this.height = drawable.getIntrinsicHeight();
			float cx, cy, sx, sy;
			if (firstLoad) {
				cx = displayWidth / 2;
				cy = displayHeight / 2;
//				float sc = (displayWidth / width + displayHeight / displayHeight) / 2;
				sx = sy = 1;
				firstLoad = false;
				setPos(cx, cy, sx, sy);
			}
		}

		public void zoomOut() {
			boolean ok = setPos(centerX, centerY, scaleX + 0.8f, scaleY + 0.8f);
			if(ok) invalidate();
		}

		public void zoomIn() {
			boolean ok = setPos(centerX, centerY, scaleX - 0.8f, scaleY - 0.8f);
			if(ok) invalidate();
		}
		
		/** Called by activity's onPause() method to free memory used for loading the images */
		public void unload() {
			this.drawable = null;
		}

		/** Set the position and scale of an image in screen coordinates */
		public boolean setPos(PositionAndScale newImgPosAndScale) {
			return setPos(newImgPosAndScale.getXOff(), newImgPosAndScale.getYOff(), (mUIMode & UI_MODE_ANISOTROPIC_SCALE) != 0 ? newImgPosAndScale
					.getScaleX() : newImgPosAndScale.getScale(), (mUIMode & UI_MODE_ANISOTROPIC_SCALE) != 0 ? newImgPosAndScale.getScaleY()
					: newImgPosAndScale.getScale());
		}

		/** Set the position and scale of an image in screen coordinates */
		private boolean setPos(float centerX, float centerY, float scaleX, float scaleY) {
			resetScreenMargin();
			if(scaleX == scaleY && scaleX > 0.5 && scaleX < 8) {
				float ws = (width / 2) * scaleX, hs = (height / 2) * scaleY;
				float newMinX = centerX - ws, newMinY = centerY - hs, newMaxX = centerX + ws, newMaxY = centerY + hs;
				
				if (newMinX > displayWidth - SCREEN_MARGIN_WIDTH_RIGHT) {
					this.minX =  displayWidth - SCREEN_MARGIN_WIDTH_RIGHT;
					this.maxX = minX + ws * 2;
				} else if(newMaxX < SCREEN_MARGIN_WIDTH_LEFT) {
					this.maxX = SCREEN_MARGIN_WIDTH_LEFT;
					this.minX = maxX - ws * 2;
				} else {
					this.minX = newMinX;
					this.maxX = newMaxX;
				}
				if(newMinY > displayHeight - SCREEN_MARGIN_HEIGHT_BOTTOM) {
					this.minY = displayHeight - SCREEN_MARGIN_HEIGHT_BOTTOM;
					this.maxY = minY + hs * 2;
				} else if(newMaxY < SCREEN_MARGIN_HEIGHT_TOP) {
					this.maxY = SCREEN_MARGIN_HEIGHT_TOP;
					this.minY = maxY - hs * 2;
				} else {
					this.minY = newMinY;
					this.maxY = newMaxY;
				}
				
				this.centerX = minX + (maxX - minX) / 2 ;
				this.centerY = minY + (maxY - minY) / 2 ;
				
				this.scaleX = scaleX;
				this.scaleY = scaleY;
			}
			return true;
		}

		private void resetScreenMargin() {
			if(width * scaleX > displayWidth) {
				SCREEN_MARGIN_WIDTH_LEFT = displayWidth;
				SCREEN_MARGIN_WIDTH_RIGHT = displayWidth;
			} else {
				SCREEN_MARGIN_WIDTH_LEFT = width * scaleX;
				SCREEN_MARGIN_WIDTH_RIGHT = width * scaleX;
			}
			
			if(height * scaleY > displayHeight) {
				SCREEN_MARGIN_HEIGHT_TOP = displayHeight - BOTTOM_FIX;
				SCREEN_MARGIN_HEIGHT_BOTTOM = displayHeight + BOTTOM_FIX;
			} else {
				SCREEN_MARGIN_HEIGHT_TOP = height * scaleY - BOTTOM_FIX;
				SCREEN_MARGIN_HEIGHT_BOTTOM = height * scaleY + BOTTOM_FIX;
			}
		}

		/** Return whether or not the given screen coords are inside this image */
		public boolean containsPoint(float scrnX, float scrnY) {
			// FIXME: need to correctly account for image rotation
			return (scrnX >= minX && scrnX <= maxX && scrnY >= minY && scrnY <= maxY);
		}

		public void draw(Canvas canvas) {
			canvas.save();
			drawable.setBounds((int) minX, (int) minY, (int) maxX, (int) maxY);
			
			
			if(drawable instanceof BitmapDrawable){
				Bitmap b = ((BitmapDrawable)drawable).getBitmap();
				if(null != b && b.isRecycled()){
					canvas.restore();
					return;
				}
			}
			drawable.draw(canvas);
			canvas.restore();
		}

		public Drawable getDrawable() {
			return drawable;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public float getCenterX() {
			return centerX;
		}

		public float getCenterY() {
			return centerY;
		}

		public float getScaleX() {
			return scaleX;
		}

		public float getScaleY() {
			return scaleY;
		}
		// FIXME: these need to be updated for rotation
		public float getMinX() {
			return minX;
		}

		public float getMaxX() {
			return maxX;
		}

		public float getMinY() {
			return minY;
		}

		public float getMaxY() {
			return maxY;
		}
	}
	public Img getImg() {
		return img;
	}
}
