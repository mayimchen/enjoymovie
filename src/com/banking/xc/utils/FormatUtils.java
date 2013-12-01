package com.banking.xc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date parseDate(String dateStr) throws ParseException {
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			if (Log.E) {
				Log.e(FormatUtils.class.getName(), "parseDate() dateStr -->> " + dateStr);
			}
			throw e;
		}
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

}
