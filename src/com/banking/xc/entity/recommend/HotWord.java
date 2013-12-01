package com.banking.xc.entity.recommend;

import android.text.Spanned;

/**
 * 辅助类，用于存放 热门词汇 信息
 */

public class HotWord {


		// 热门关键词等级，越小，字体越大
		private Integer level = 1;
		private CharSequence text;
		// 字体颜色
		private Integer color = 0;
		// 是否需要加粗
		private Boolean needBold = false;

		private Spanned htmlText = null;

		public boolean isNeedBold() {
			return needBold;
		}

		public HotWord(int level, CharSequence text, int color, boolean needBold) {
			super();
			this.level = level;
			this.text = text;
			this.color = color;
			this.needBold = needBold;
		}

		public Integer getLevel() {
			if (level == null) {
				level = 1;
			}
			return level;
		}

		/**
		 * 获取带下划线的 热门词汇 (暂时不需要下划线了)
		 * 
		 * @return
		 */
		public CharSequence getText() {
			// if (htmlText == null) {
			// StringBuilder builder = new StringBuilder();
			// builder.append("<u>");
			// builder.append(text);
			// builder.append("</u>");
			// htmlText = Html.fromHtml(builder.toString());
			// }
			// return htmlText;
			return text;
		}

		/**
		 * 根据热门词汇的等级，返回字体的大小
		 * 
		 * @return 字体的大小
		 */
		public Integer getTextSize() {
			if (level == null) {
				level = 1;
			}
			switch (level) {
			case 0:
				return 20;
			case 1:
				return 18;
			case 2:
				return 16;
			case 3:
				return 14;
			default:
				return 12;
			}
		}

		/**
		 * 根据color的值，获取字体颜色
		 * 
		 * @return 字体颜色
		 */
		public int getColor() {
			if (color == null) {
				color = 1;
			}
			switch (color) {
			case 0:
				return 0xFFcc0000;
			case 1:
				return 0xFF0163dd;
			case 2:
				return 0xFFee5a00;
			case 3:
				return 0xFF3bb301;
			case 4:
				return 0xFF9b00c7;
			case 5:
				return 0xFF404040;
			default:
				return 0xFF003399;
			}
		}


}
