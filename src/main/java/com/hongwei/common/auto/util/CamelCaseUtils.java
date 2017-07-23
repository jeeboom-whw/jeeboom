package com.hongwei.common.auto.util;

public class CamelCaseUtils {
	public static String toUnderlineName(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			boolean upperCase = false;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				boolean nextUpperCase = true;
				if (i < (str.length() - 1)) {
					nextUpperCase = Character.isUpperCase(str.charAt(i + 1));
				}
				if ((i >= 0) && Character.isUpperCase(c)) {
					if (!upperCase || !nextUpperCase) {
						if (i > 0)
							sb.append("_");
					}
					upperCase = true;
				} else {
					upperCase = false;
				}
				sb.append(Character.toLowerCase(c));
			}
			return sb.toString();
		}
	}

	public static String toCamelCase(String str) {
		if (str == null || "".equals(str.trim())) {
			return null;
		} else {
			str = str.toLowerCase();
			StringBuilder sb = new StringBuilder(str.length());
			boolean upperCase = false;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c == '_') {
					if( i == 0 ){
						sb.append(c);
					}else{
						upperCase = true;
					}
				} else if (upperCase) {
					sb.append(Character.toUpperCase(c));
					upperCase = false;
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}
	}
}