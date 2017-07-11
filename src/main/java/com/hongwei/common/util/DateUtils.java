package com.hongwei.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 时间工具类 */
public class DateUtils {

	/** 获取当前时间字符串 */
	public final static String format(){
		return format(new Date());
	}

	/** 根据date转换string类型 */
	public final static String format(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
}
