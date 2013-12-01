package com.banking.xc.utils.transutil;

import java.util.ArrayList;
import java.util.Random;

import skytv_com.banking.enjoymovie.R;

import com.banking.xc.entity.HomeItem;
import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.MyActivity;
import com.banking.xc.utils.MySimpleAdapter;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class VacationTransformUtil implements Runnable{

	public final static int LEFT_TO_RIGHT = 1;
	public final static int RIGHT_TO_LEFT = 2;
	public final static int UP_TO_DOWN = 3;
	public final static int DOWM_TO_UP = 4;
	public final static int DOWNLEFT_TO = 5;
	public final static int DOWNRIGHT_TO = 6;
	public final static int UPLEFT_TO = 7;
	public final static int UPRIGHT_TO = 8;
	
	GridView mGridView;
	ArrayList<HomeItem> mRecommendDataList;
	/*Animation leftXAnimation;
	Animation rightXAnimation;
	Animation upYAnimation;
	Animation downYAnimation;*/
	//Animation dispareAnimation;
	MyActivity myActivity;
	MySimpleAdapter mAdapter;
	//RelativeLayout[] views = new 
	
	public VacationTransformUtil(GridView gridView,ArrayList<HomeItem> recommendDataList,MySimpleAdapter adapter,MyActivity myActivity){
		mGridView = gridView;
		mRecommendDataList=recommendDataList;
		this.myActivity = myActivity;
		this.mAdapter = adapter;
		//initAnimation();
	}
	@Override
	public void run() {
		//是否需要tag判断
		while(true){
			try {
				Thread.currentThread().sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			{    myActivity.post(new Runnable(){
					@Override
					public void run() {
						//transformRecycle();
						twoPicChange();
					}
				});
			}
		}
	}
	public int[] getRandomTransformNumbers(){
		//012
		//345
		//678 position
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 3;
		return result;
	}
	/*public void initAnimation(){
		leftXAnimation= new TranslateAnimation(0, -DPIUtil.dip2px(90), 0, 0);
		leftXAnimation.setDuration(700);
		rightXAnimation= new TranslateAnimation(0, DPIUtil.dip2px(90), 0, 0);
		rightXAnimation.setDuration(700);
		upYAnimation = new TranslateAnimation(0, 0, 0, -DPIUtil.dip2px(85));
		upYAnimation.setDuration(700);
		downYAnimation = new TranslateAnimation(0, 0, 0, DPIUtil.dip2px(85));
		downYAnimation.setDuration(700);
		
		
	}*/
	/*public void transformDataList(){
		//012
		//345
		//678 position
		ArrayList<HomeItem> newDataList = new ArrayList<HomeItem>();
		newDataList.add(mRecommendDataList.get(1));
		newDataList.add(mRecommendDataList.get(2));
		newDataList.add(mRecommendDataList.get(5));
		newDataList.add(mRecommendDataList.get(0));
		newDataList.add(mRecommendDataList.get(4));
		newDataList.add(mRecommendDataList.get(8));
		newDataList.add(mRecommendDataList.get(3));
		newDataList.add(mRecommendDataList.get(6));
		newDataList.add(mRecommendDataList.get(7));
		mRecommendDataList = null;
		mRecommendDataList = newDataList;
	}*/
	public void changeData(int i,int j){
		HomeItem item1 = mRecommendDataList.get(i);
		HomeItem item2 = mRecommendDataList.get(j);
		mRecommendDataList.add(j, item1);
		mRecommendDataList.remove(j+1);
		mRecommendDataList.add(i, item2);
		mRecommendDataList.remove(i+1);
		mAdapter.notifyDataSetChanged();
	}
	public void doAnimation(final RelativeLayout target,final int tag,final int i,final int j){
		
		Animation dispareAnimation = new AlphaAnimation(100, 0);
		dispareAnimation.setDuration(1000);
		dispareAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				myActivity.post(new Runnable() {
					@Override
					public void run() {
						target.getChildAt(0).setVisibility(View.GONE);
						target.getChildAt(1).setVisibility(View.GONE);
						target.setBackgroundDrawable(new VacationRecommendTransBg(myActivity,target,tag));
						
					}
				});
				myActivity.post(new Runnable(){
					@Override
					public void run() {
						target.setBackgroundDrawable(null);
						target.getChildAt(0).setVisibility(View.VISIBLE);
						target.getChildAt(1).setVisibility(View.VISIBLE);
						changeData(i,j);
					}
				}, 1500);
				
				
			}
		});
		target.startAnimation(dispareAnimation);
		
	}
	public void doAnimation(final RelativeLayout target,final int tag){
		Animation dispareAnimation = new AlphaAnimation(100, 0);
		dispareAnimation.setDuration(1000);
		dispareAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				myActivity.post(new Runnable() {
					@Override
					public void run() {
						target.getChildAt(0).setVisibility(View.GONE);
						target.getChildAt(1).setVisibility(View.GONE);
						target.setBackgroundDrawable(new VacationRecommendTransBg(myActivity,target,tag));
						
					}
				});
				myActivity.post(new Runnable(){
					@Override
					public void run() {
						target.setBackgroundDrawable(null);
						target.getChildAt(0).setVisibility(View.VISIBLE);
						target.getChildAt(1).setVisibility(View.VISIBLE);
					}
				}, 1500);
			}
		});
		target.startAnimation(dispareAnimation);
		
	}
	public void twoPicChange(){
		Random random = new Random();
		int i = random.nextInt(9);
		int j = i;
		while(j==i){
			j=random.nextInt(9);
		}
		final RelativeLayout targetView1 = (RelativeLayout) mGridView.getChildAt(i);
		final RelativeLayout targetView2 = (RelativeLayout) mGridView.getChildAt(j);
		//012
		//345
		//678 position
		int ix = i%3;
		int iy = i/3;
		int jx = j%3;
		int jy = j/3;
		int tagx = 0;
		int tagy = 0;
		if(ix>jx){
			if(iy>jy){
				tagx = DOWNRIGHT_TO;
				tagy = UPLEFT_TO;
			}else{
				if(iy==jy){
					tagx = RIGHT_TO_LEFT;
					tagy = LEFT_TO_RIGHT;
				}else{
					tagx = UPRIGHT_TO;
					tagy = DOWNLEFT_TO;
				}
			}
		}else{
			if(ix==jx){
				if(iy>jy){
					tagx = DOWM_TO_UP;
					tagy = UP_TO_DOWN;
				}else{
					tagx = UP_TO_DOWN;
					tagy = DOWM_TO_UP;
				}
			}else{
				//ix<jx
				if(iy>jy){
					tagx = DOWNLEFT_TO;
					tagy = UPRIGHT_TO;
				}else{
					if(iy==jy){
						tagx = LEFT_TO_RIGHT;
						tagy = RIGHT_TO_LEFT;
					}else{
						tagx = UPLEFT_TO;
						tagy = DOWNRIGHT_TO;
					}
				}
			}
		}
		doAnimation(targetView1,tagx);
		doAnimation(targetView2,tagy,i, j);
		
		
	}
	/*public void transformRecycle(){
		final ImageView[] imageViews = new ImageView[9];
		for(int i=0;i<9;i++){
			imageViews[i] = (ImageView) mGridView.getChildAt(i).findViewById(R.id.vacation_recommend_iv);
		}
		imageViews[0].startAnimation(downYAnimation);
		imageViews[1].startAnimation(leftXAnimation);
		imageViews[2].startAnimation(leftXAnimation);
		imageViews[3].startAnimation(downYAnimation);
		imageViews[5].startAnimation(upYAnimation);
		imageViews[6].startAnimation(rightXAnimation);
		imageViews[7].startAnimation(rightXAnimation);
		imageViews[8].startAnimation(upYAnimation);
		
	}*/
}
