package com.cfeindia.b2bserviceapp.util;

public class ExtractorUtil {
	public static String extractIdFromString(String str) {
		String subStr = str.substring(1);
		long result = Long.parseLong(subStr);
		subStr = String.valueOf(result);
		return subStr;
	}

	public static String generateIdFromString(String str, String typeOfUser) {
		str.trim();
		int length = str.length();
		for (int i = 0; i < 9 - length; i++) {
			typeOfUser = typeOfUser.concat("0");
		}
		String result = typeOfUser.concat(str);
		return result;
	}

	public static double getRoundedDouble(Double amount) {
		if (amount == null) {
			return 0.0d;
		}
		if (amount < 0) {
			return amount;
		}

		double roundedAmount = (Math.round(amount * 100.0) / 100.0);
		return roundedAmount;
	}
}
