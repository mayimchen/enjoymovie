package com.banking.xc.utils.transutil;

import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.Log;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
/**
 * 画图是以像素dp为单位的
 * @author zhangyinhang
 *
 */
public class VacationRecommendTransBg extends Drawable{

	
	//private final int leftStar = 1;
	//private final int rightStar = 2;
	private final int progressPointTag = 3;
	private final String TAG = "StartImageDrawable";
	/*private final float leftStarToTop = 0.2f;
	private final float leftStarToLeft = 0.2f;
	private finalfloat rightStarToT op = 0.2f;
	private final float rightStarT=*/
	private final float starToTop = 0.2f;
	private final float starToBorder = 0.2f;
	private final float targetToBottom = 0.2f;
	private final int gap = 30;//每隔多少毫秒刷帧数 。300有明显停顿，不合适。100至少
	private final int timeAll = 1500;
	private final int mCount = timeAll/gap;
	private final int PROGRESS_LINE_WIDTH = 12;
	private final int D = PROGRESS_LINE_WIDTH/2;
	
	/*private Bitmap smallStar;
	private Bitmap bigStar;*/
	private Bitmap bg;
	//private Bitmap progressLine;
	private Bitmap progressPoint;
	private Context context;
	private Resources resources;
	Paint bitmapPaint ;
	private View allView;
	private VacationRecommendTransBg startImageDrawable;
	
	private int allHeight = 0;
	private int allWidth = 0;
	//private float leftStarX;
	//private float leftStarY;
	//private float rightStarX;
	//private float rightStarY;
	private float progressLineLength;
	private Rect progressLineRect;
	private float progressPointInitX;
	private float progressPointInitY;
	private float progressPointFinalX;
	private float progressPointFinalY;
	int tag;
	private float pointNowX;
	private float pointNowY;
	private float pointXDistance;
	private float pointYDistance;
	
	public VacationRecommendTransBg(Context context,View view,int tag){
		this.context = context;
		this.resources = context.getResources();
		this.tag = tag;
		//this.tag = VacationTransformUtil.UPLEFT_TO;
		bitmapPaint = new Paint();
		allView = view;
		//initialize();
		startImageDrawable = this;
	}
	public void initialize(){
		if(allHeight==0){
			
			Rect viewRect = this.getBounds();
			allHeight = viewRect.bottom-viewRect.top;
			allWidth = viewRect.right-viewRect.left;
			if(Log.D){
				Log.d(TAG, "viewRect.top"+allHeight+" "+allWidth);
			}
			allHeight =  90;//DPIUtil.px2dip();//DPIUtil.px2dip(context, DPIUtil.getHeight());//viewRect.bottom-viewRect.right;
			allWidth =  100;//viewRect.right-viewRect.left;
			if(Log.D){
				Log.d(TAG,"allHeight"+allHeight);
				Log.d(TAG,"allWidth"+allWidth);
			}
			//这里需要考虑星星宽度，先设置12dp
			progressLineLength = (float) (0.4*allWidth);//+-12
			//progressLine = BitmapFactory.decodeResource(resources, R.drawable.vacation_change_loadingbg);
			progressPoint = BitmapFactory.decodeResource(resources, R.drawable.vacation_recommend_loadingitem);
			
			progressLineRect = new Rect();
			switch(tag){
				case VacationTransformUtil.DOWM_TO_UP:
					progressPointInitX = 0.5f*allWidth;
					progressPointInitY = 0.9f*allHeight;
					progressPointFinalX = 0.5f*allWidth;
					progressPointFinalY = 0.1f*allHeight;
					progressLineRect.set((int)(progressPointFinalX-6),1,(int)(0.7f*allWidth),(int)(0.75f*allHeight));
					pointXDistance =  0;
					pointYDistance = -0.8f*allHeight;
				break;
				
				case VacationTransformUtil.UP_TO_DOWN:
					progressPointInitX = 0.5f*allWidth;
					progressPointInitY = 0.1f*allHeight;
					pointXDistance =  0;
					pointYDistance = 0.8f*allHeight;
					break;
				case VacationTransformUtil.LEFT_TO_RIGHT:
					progressPointInitX = 0.1f*allWidth;
					progressPointInitY = 0.5f*allHeight;
					pointXDistance =  0.8f*allWidth;
					pointYDistance = 0;
					break;
				case VacationTransformUtil.RIGHT_TO_LEFT:
					progressPointInitX = 0.9f*allWidth;
					progressPointInitY = 0.5f*allHeight;
					pointXDistance = -0.8f*allWidth;
					pointYDistance = 0;
					break;
				case VacationTransformUtil.DOWNRIGHT_TO:
					progressPointInitX = 0.9f*allWidth;
					progressPointInitY = 0.9f*allHeight;
					pointXDistance =  -0.8f*allWidth;
					pointYDistance = -0.8f*allHeight;
					break;
				case VacationTransformUtil.UPLEFT_TO:
					progressPointInitX = 0.1f*allWidth;
					progressPointInitY = 0.1f*allHeight;
					progressPointFinalX = 0.9f*allWidth; //需要旋转,暂时取消0.1
					progressPointFinalY = 0.9f*allHeight;
					pointXDistance =  0.8f*allWidth;
					pointYDistance = 0.8f*allHeight;
					//progressLineRect.set((int)(progressPointFinalX-6),1,(int)(0.7f*allWidth),(int)(0.75f*allHeight));
				break;
				case VacationTransformUtil.DOWNLEFT_TO:
					progressPointInitX = 0.1f*allWidth;
					progressPointInitY = 0.9f*allHeight;
					pointXDistance =  0.8f*allWidth;
					pointYDistance = -0.8f*allHeight;
					break;
				case VacationTransformUtil.UPRIGHT_TO:
					progressPointInitX = 0.9f*allWidth;
					progressPointInitY = 0.1f*allHeight;
					pointXDistance = -0.8f*allWidth;
					pointYDistance = 0.8f*allHeight;
					break;
			}
			
			pointNowX = progressPointInitX;
			pointNowY = progressPointInitY;
			
			//progressLineRect.set((int)(0.3f*allWidth),(int)(0.7f*allHeight),(int)(0.7f*allWidth),(int)(0.75f*allHeight));
			//progressLineRect.set((int)(0.3f*allWidth),(int)(0.7f*allHeight),(int)(0.7f*allWidth),(int)(0.75f*allHeight));
			
			

			
			Thread thread = new Thread(new StarRunable());
			thread.start();
		}
		
		
		/*smallStar = BitmapFactory.decodeResource(resources, R.drawable.android_ratingbar_single_light);
		bigStar = BitmapFactory.decodeResource(resources, R.drawable.android_ratingbar_single);*/
		//bg = BitmapFactory.decodeResource(resources, R.drawable.android_jd_buy_loading);
		
		//Rect viewRect = new Rect();
		//allView.getGlobalVisibleRect(viewRect);
		/*if(allHeight==0){
			allHeight = DPIUtil.px2dip(context, DPIUtil.getHeight());//viewRect.bottom-viewRect.right;
			allWidth = DPIUtil.px2dip(context, DPIUtil.getWidth());//viewRect.right-viewRect.left;
			if(Log.D){
				Log.d(TAG,"allHeight"+allHeight);
				Log.d(TAG,"allWidth"+allWidth);
			}
		}*/
		
		
	}
	@Override
	public void draw(Canvas canvas) {
		if(Log.D){
			Log.d(TAG,"on Draw");
		}
		if(allHeight == 0){
			initialize();
		}
		//Rect viewRect = this.getBounds();
		
		
		//canvas.drawBitmap(bg,0,0,bitmapPaint);
		/**
		 * 这个方法可以
		 */
		//canvas.drawBitmap(bg,null,viewRect,bitmapPaint);
		canvas.drawColor(R.color.red);
		Matrix matrix = new Matrix();
		RectF initRect = new RectF();
		//Rect finalRect = new Rect();
		initRect.set((int)(progressPointInitX-6),(int)(progressPointInitY),(int)(progressPointFinalX+6),(int)(progressPointFinalY));
		matrix.setRotate(-45);
		matrix.mapRect(initRect);
		//canvas.drawBitmap(progressLine,null, initRect, bitmapPaint);
		//canvas.d
		canvas.drawBitmap(progressPoint, pointNowX, pointNowY, bitmapPaint);
		//RectF rectF = new RectF();
		
		
		//canvas.drawBitmap(smallStar, leftStarX, leftStarY, bitmapPaint);
		//canvas.drawBitmap(smallStar, rightStarX, leftStarY, bitmapPaint);//rightStarY
	}
	/**
	 * 抛物线运行轨迹，很难计算
	 * @author zhangyinhang
	 *
	 */
	public class StarRunable implements Runnable{
		int count = 0;
		@Override
		public void run() {
			//先等待其他页面画出来
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(;count<mCount;count++){
				try {
					Thread.currentThread().sleep(gap);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//starNowPoint(count,leftStar);
				//starNowPoint(count,rightStar);
				starNowPoint(count,progressPointTag);
				MyApplication.getInstance().getHandler().post(new Runnable() {

					@Override
					public void run() {
						if(Log.D){
							Log.d(TAG, "post run invalidateSelf-->> "  );
						}
						startImageDrawable.invalidateSelf();
					}

				});
				
			}
		}
		
	}
	
	/**
	 * 
	 * @param count 第N次
	 * @param which 哪一个星星
	 */
	public void starNowPoint(int count,int which){
		if(count>mCount) {
			return;
		}
		switch(which){
		/*case leftStar:
			leftStarX += 0.2*allWidth/mCount;//0.3
			//leftStarY += 0.6*allHeight/mCount;
		case rightStar:*/
			//rightStarX -= 0.2*allWidth/mCount;
			//rightStarY += 0.6*allHeight/mCount;
		case progressPointTag:
			pointNowX = pointNowX+pointXDistance/mCount;
			pointNowY = pointNowY+pointYDistance/mCount;
			
		}
		
	}
	
	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
