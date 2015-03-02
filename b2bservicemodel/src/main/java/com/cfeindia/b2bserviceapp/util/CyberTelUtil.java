package com.cfeindia.b2bserviceapp.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CyberTelUtil {
	public static String generateTransId(String param1, String param2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		StringBuilder builder = new StringBuilder();
		if (param1 != null) {
			builder.append(param1);
		}
		if (param2 != null) {
			builder.append(param2);
		}
		builder.append(formattedDate);
		return builder.toString();

	}

	public static long getStrInLong(String str) {
		long l = Long.parseLong(str);
		return l;
	}

	public static String twoDecimalPlaceNumber(double amount) {
		// Double val = Double.parseDouble(amount);
		DecimalFormat format = new DecimalFormat("0.00");
		return format.format(amount);
	}

	public static String removeDecimalFromNumber(double amount) {
		// Double val = Double.parseDouble(amount);
		DecimalFormat format = new DecimalFormat("0");
		return format.format(amount);
	}

	public static Integer getGeneratedPassword() {

		Random rand = new Random();

		return (rand.nextInt(1000000));

	}

	public static Double twoDecimalPlaceValue(double amount) {
		// Double val = Double.parseDouble(amount);
		DecimalFormat format = new DecimalFormat("0.00");
		return (Double.parseDouble(format.format(amount)));

	}
}
