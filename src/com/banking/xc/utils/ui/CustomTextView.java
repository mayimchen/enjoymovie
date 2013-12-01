package com.banking.xc.utils.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.widget.TextView;

import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.Log;
public class CustomTextView extends TextView {

	private static final String TAG = "MyTextView";
	private String content;
	private int width; // 图片宽度

	public CustomTextView(Context context) {
		super(context);
	}

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		width = getWidth();
		content = getText().toString();

		int margin = DPIUtil.dip2px(10);
		Paint contentP = getPaint();
		contentP.setColor(Color.argb(255, 0, 0, 0));
		contentP.setTextSize(DPIUtil.dip2px(14));
		// contentP.setAntiAlias(true);

		FontMetrics fm = contentP.getFontMetrics();
		float fontHeight = fm.descent - fm.ascent; // 计算字体高度
		float x = (width / 2) - DPIUtil.dip2px(1);
		float y = fontHeight - DPIUtil.dip2px(1);

		float textWidth = 0;
		int num = 0;// 记录每行开始的位置
		float[] widths = new float[content.length()]; // 记录每个字符的宽度

		contentP.getTextWidths(content, widths); // 计算每个字符所占宽度
		if (Log.D) {
//			Log.d(TAG, " onDraw -->> content.length()： " + content.length());
		}
		float measureText = contentP.measureText(content);
		if (measureText <= width - margin) { // 如果子符宽度只满足一行
			contentP.setTextAlign(Align.CENTER);// 只可以画一行时字符居中显示
			x = (width / 2) - DPIUtil.dip2px(1);// 重新设置画字的起点
			canvas.drawText(content, x, y, contentP);
		} else { // 字符宽度满足画两行
			contentP.setTextAlign(Align.LEFT);// 可以画两行时字符居左显示
			x = 0;// 重新设置画字的起点
			int k;
			for (int i = 0; i < 2; i++) {

				if (num >= widths.length) { //
					break;
				}
				int start = num;
				textWidth = 0;
				for (k = num; k < widths.length; k++) { // 计算每行可以画几个字符
					if (textWidth < width - margin) {
						textWidth += widths[k];
						num = k;
					} else {
						break;
					}
				}
				num++;
				if (Log.D) {
					Log.d(TAG, "onDraw --> ======num :" + num + "," + content.length());
				}
				canvas.drawText(content.substring(start, num), x, y + i * fontHeight, contentP);
			}
		}
	}

}
