package com.zizaihome.api.utils;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
	
	public static Map<String,String> string2Map(String str){
		Map<String,String> map = new HashMap<>();
		if(str!=null && !str.equals("")){
			String[] s = str.split("\\&");
			for(String ss:s){
				if(ss.split("=").length > 1){
					map.put(ss.split("=")[0], ss.split("=")[1]);
				}
			}
		}
		return map;
	}
	
	public static String banString(int start,int end,String str){
		if(str.length()<end){
			return "-";
		}
		else{
			StringBuffer result = new StringBuffer();
			for(int i=0;i<str.length();i++){
				if(i>=start && i<=end){
					result.append("*");
				}
				else{
					result.append(str.charAt(i));
				}
			}
			return result.toString();
		}
	}
	
	public static String banString(int leave,String str){
		if(leave>str.length()){
			return "-";
		}
		StringBuffer result = new StringBuffer();
		for(int i=0;i<str.length();i++){
			if(i < str.length()-leave){
				result.append("*");
			}
			else{
				result.append(str.charAt(i));
			}
		}
		return result.toString();
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return 为空返回<b>true</b>，不为空返回<b>false</b>
	 */
	public static boolean isblank(String str){
		if (str==null || "".equals(str)) {
			return true;
		}else {
			return false;
		}
	}
}
