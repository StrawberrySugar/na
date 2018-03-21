package com.zizaihome.api.common.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class GetObjectUtils {

	/**
	 * 将数字形式的对象转换成int型的。并在为空时可以指定默认值。
	 * 
	 * @param obj
	 * @param defaultInt
	 * @return
	 */
	public static int toInt(Object obj, int defaultInt) {
		return (obj == null || StringUtils.isEmpty(obj.toString())) ? defaultInt
				: Integer.parseInt(obj.toString());
	}

	/**
	 * 将对象转换成String型的。并在为空时可以指定默认值。
	 * 
	 * @param obj
	 * @param defaultInt
	 * @return
	 */
	public static String toString(Object obj, String defaultStr) {
		return obj == null ? defaultStr : (String) obj;
	}

	/**
	 * 将对象转换成Date型的。并在为空时可以指定默认值。
	 * 
	 * @param obj
	 * @param defaultInt
	 * @return
	 */
	public static Date toDate(Object obj, Date defaultDate) {
		return obj == null ? defaultDate : (Date) obj;
	}

	/**
	 * 将对象转换成double型的。并在为空时可以指定默认值。
	 * 
	 * @param obj
	 * @param defaultInt
	 * @return
	 */
	public static double toDouble(Object obj, double defaultDouble) {
		return (obj == null || StringUtils.isEmpty(obj.toString())) ? defaultDouble
				: Double.valueOf(obj.toString());
	}

}
