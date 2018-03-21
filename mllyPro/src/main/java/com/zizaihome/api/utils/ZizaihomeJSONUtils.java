package com.zizaihome.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class ZizaihomeJSONUtils {
	
	public static List<String> emptyNullFilter(){
		List<String> filterPropertyTemp = new ArrayList<String>();
		return filterPropertyTemp;
	}
	
	public static List<String> emptyBuddnistCeremonyCommodityFilter(){
		List<String> filterPropertyTemp = new ArrayList<String>();
		filterPropertyTemp.add("add_time");
		filterPropertyTemp.add("update_time");
		return filterPropertyTemp;
	}
	
	public static List<String> emptyBuddnistCeremonyCommodityOrderFilter(){
		List<String> filterPropertyTemp = new ArrayList<String>();
		filterPropertyTemp.add("user_id");
		return filterPropertyTemp;
	}
	
	public static List<String> emptyArticleFilter(){
		List<String> filterPropertyTemp = new ArrayList<String>();
		filterPropertyTemp.add("user_id");
		return filterPropertyTemp;
	}
	
	public static List<String> emptyLiveTelecastGiftFilter(){
		List<String> filterPropertyTemp = new ArrayList<String>();
		filterPropertyTemp.add("user_id");
		return filterPropertyTemp;
	}
	
	public static List<String> emptyUserFilter(){
		List<String> filterPropertyTemp = new ArrayList<String>();
		filterPropertyTemp.add("id");
		return filterPropertyTemp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
