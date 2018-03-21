package com.zizaihome.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZizaihomeUtils {

	public static boolean isMobile(String mobile){
		if(mobile.startsWith("+86")){
			mobile = mobile.substring(3);
		}
		mobile = mobile.trim();
//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Pattern p = Pattern.compile("^1\\d{10}$");  
		Matcher m = p.matcher(mobile);  
		return m.matches(); 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogUtils.log.info(isMobile("18280079671")+"");
	}

}
