package org.orakzai.lab.shop.web.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.orakzai.lab.shop.domain.constants.Constants;



public class DateUtil {

	public static String formatDate(LocalDate now) {
		if (now == null) {
			return null;
		}
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT);
		return now.format(pattern);
	}

	public static LocalDate getDate(String dt) throws Exception {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT);
		return LocalDate.parse(dt, pattern);

	}

	public static String getPresentYear() {
		var cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR));
	}

}
