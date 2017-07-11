package com.hongwei.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public final static String format(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
}
