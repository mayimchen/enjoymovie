
package com.banking.xc.utils.frame;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.Drawable;
import skytv_com.banking.enjoymovie.R;

import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.frame.TabBarButton.StateController;

public class RadioStateDrawable extends Drawable {

	private Bitmap bitmap;
	private Bitmap highlightBitmap;
	private Shader shader;
	private Shader textShader;
	Context context;
	public static int width;
	public static int screen_width;
	private boolean highlight;
	private String label;

	private StateController stateController;// 状态控制器

	public static final int SHADE_GRAY = 0;
	public static final int SHADE_BLUE = 1;
	public static final int SHADE_MAGENTA = 2;
	public static final int SHADE_YELLOW = 3;
	public static final int SHADE_GREEN = 4;
	public static final int SHADE_RED = 5;
	public static final int SHADE_ORANGE = 6;

	public RadioStateDrawable(Context context, int imageID, String label, boolean highlight, int shade) {
		super();
		this.highlight = highlight;
		this.context = context;
		this.label = label;
		InputStream is = context.getResources().openRawResource(imageID);
		bitmap = BitmapFactory.decodeStream(is);
		setShade(shade);

		//highlightBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bottom_bar_highlight);
	}

	public RadioStateDrawable(Context context, int imageID, String label, boolean highlight, int startGradientColor, int endGradientColor) {
		super();
		this.highlight = highlight;
		this.context = context;
		this.label = label;
		InputStream is = context.getResources().openRawResource(imageID);
		bitmap = BitmapFactory.decodeStream(is);
		int[] shades = new int[] { startGradientColor, endGradientColor };
		shader = new LinearGradient(0, 0, 0, bitmap.getHeight(), shades, null, Shader.TileMode.MIRROR);
	}

	public void setShade(int shade) {
		int[] shades = new int[2];
		switch (shade) {
		case SHADE_GRAY: {
			shades = new int[] { Color.LTGRAY, Color.DKGRAY };
			break;
		}
		case SHADE_BLUE: {
			shades = new int[] { Color.CYAN, Color.BLUE };
			break;
		}
		case SHADE_RED: {
			shades = new int[] { Color.MAGENTA, Color.RED };
			break;
		}
		case SHADE_MAGENTA: {
			shades = new int[] { Color.MAGENTA, Color.rgb(292, 52, 100) };
			break;
		}
		case SHADE_YELLOW: {
			shades = new int[] { Color.YELLOW, Color.rgb(255, 126, 0) };
			break;
		}
		case SHADE_ORANGE: {
			shades = new int[] { Color.rgb(255, 126, 0), Color.rgb(255, 90, 0) };
			break;
		}
		case SHADE_GREEN: {
			shades = new int[] { Color.GREEN, Color.rgb(0, 79, 4) };
			break;
		}
		}

		shader = new LinearGradient(0, 0, 0, bitmap.getHeight(), shades, null, Shader.TileMode.MIRROR);

		if (highlight)
			textShader = new LinearGradient(0, 0, 0, 10, new int[] { Color.WHITE, Color.LTGRAY }, null, Shader.TileMode.MIRROR);
		else
			textShader = new LinearGradient(0, 0, 0, 10, new int[] { Color.LTGRAY, Color.DKGRAY }, null, Shader.TileMode.MIRROR);
	}

	@Override
	public void draw(Canvas canvas) {
		// canvas.setDensity((int) context.getResources().getDisplayMetrics().density);
		// bitmap.setDensity((int) context.getResources().getDisplayMetrics().density);
		int iconWidth = DPIUtil.dip2px(28);
		int iconHeight = DPIUtil.dip2px(26);
		/*
		 * if (width==0) { if (screen_width==0) screen_width = 320; width=screen_width/5; }
		 */
		int x = ((width - iconWidth) / 2)-DPIUtil.dip2px(1);
		int y = DPIUtil.dip2px(2);

		canvas.drawColor(Color.TRANSPARENT);

		// 画数字准备
		Paint numP = new Paint();
		numP.setColor(Color.WHITE);
		numP.setStyle(Paint.Style.FILL);
		numP.setTextSize(DPIUtil.dip2px(13));
		numP.setTypeface(Typeface.DEFAULT_BOLD);
		numP.setFakeBoldText(true);
		numP.setTextAlign(Align.CENTER);
		numP.setAntiAlias(true);

		Rect rect = new Rect(x, y, x + iconWidth, y + iconHeight);

		// 画图标
		canvas.drawBitmap(bitmap, null, rect, null);

		// 画标题准备
		Paint titleP = new Paint();
		titleP.setColor(Color.WHITE);
		titleP.setStyle(Paint.Style.FILL);
		titleP.setTextSize(DPIUtil.dip2px(13));
		titleP.setTypeface(Typeface.DEFAULT_BOLD);
		titleP.setTextAlign(Align.CENTER);
		titleP.setAntiAlias(true);

		// 画 > 标题
		canvas.drawText(label, (width / 2)-DPIUtil.dip2px(1), y + iconHeight + DPIUtil.dip2px(11), titleP);

		if (null != stateController && null != stateController.getNum()) {

			String num = stateController.getNum().toString();

			// 画数字准备
			float centerX = width - DPIUtil.dip2px(23);
			float centerY = y + DPIUtil.dip2px(12);
			// 计算数字的宽度
			float textWidth = 0;
			float[] widths = new float[num.length()];
			numP.getTextWidths(num, widths);
			for (int i = 0; i < widths.length; i++) {
				textWidth += widths[i];
			}
			// 计算数字的高度
			FontMetrics fm = numP.getFontMetrics();// 得到系统默认字体属性
			float textHeight = fm.descent - fm.top;// 获得字体高度

			// 画圆角矩形准备
			float rRectWidth = Math.max(textHeight, textWidth + DPIUtil.dip2px(10));
			float rRectHeight = textHeight;

			Paint rRectP = new Paint();
			rRectP.setAntiAlias(true);

			RectF rectF = new RectF();
			rectF.left = centerX - rRectWidth / 2;
			rectF.top = centerY - DPIUtil.dip2px(4) - rRectHeight / 2;
			rectF.right = rectF.left + rRectWidth;
			rectF.bottom = rectF.top + rRectHeight;
			float rx = rRectHeight/2;// 圆角
			float ry = rx;// 圆角

			// 画 > 圆角矩形
			// 渐变填充
			Shader mShader = new LinearGradient(0, 0, 0, textHeight,//
					0xFFF50000, 0xFFB10202,//
					Shader.TileMode.CLAMP);
			rRectP.setStyle(Paint.Style.FILL);
			rRectP.setShader(mShader);
			canvas.drawRoundRect(rectF, rx, ry, rRectP);

			// 描边
			rRectP.setStyle(Paint.Style.STROKE);
			rRectP.setShader(null);
			rRectP.setColor(Color.WHITE);
			rRectP.setStrokeWidth(DPIUtil.dip2px(2));
			canvas.drawRoundRect(rectF, rx, ry, rRectP);

			// 画 > 数字
			canvas.drawText(num, centerX, centerY, numP);
		}
	}

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
	}

	public void setStateController(StateController stateController) {
		this.stateController = stateController;
	}

}
