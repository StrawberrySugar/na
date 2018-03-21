package com.zizaihome.api.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;


public class StringToListUtils {
	public Object[] stringToList(String listStr){
		listStr = listStr.substring(1,listStr.length()-1);
		Object[] objectList = null;
		try{
			objectList = listStr.split(",");
		}
		catch(Exception e){
			return null;
		}
		return objectList;
	}
	
	public List<JSONObject> stringListToJSONList(String listStr){
		listStr = listStr.substring(1,listStr.length()-1);
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		Object[] objectList = null;
		try{
			objectList = listStr.split("},");
			for(Object jsonObject : objectList){
				JSONObject json = JSONObject.fromObject(jsonObject.toString()+"}");
				jsonList.add(json);
			}
		}
		catch(Exception e){
			return null;
		}
		return jsonList;
	}
//	
//	public static void main(String args[]){
//		StringToListUtils stl = new StringToListUtils();
//		Object[] abc = stl.stringToList("[1,2,3]");
//		for(Object a : abc){
//			LogUtils.log.info(Integer.parseInt(a.toString()));
//		}
//	}
}
