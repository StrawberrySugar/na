package com.zizaihome.api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * java bean 转成json
 * @author luochangming
 *
 */
public class JSONFromBean {

	
	private Object object;
	private List<List<String>> filterPropertyArray = new ArrayList<List<String>>();//需要过滤的属性
	List<Map<String , Object>> addProperties = new ArrayList< Map<String , Object>>();//需要增加的属性
	
	
	public JSONFromBean(Object object , List<String> filter){
		this.object = object;
		filterPropertyArray.add(filter);
	}
	
	
	//为非Collection类型的Object增加一个属性
	public void addPropertyObj(String name , Object value , List<String> filter){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value);
		addPropertyColl(name, valueList, filter);
	}
	
	//为Collection类型的Object增加一个属性
	public void addPropertyColl(String name , List value , List<String> filter){
		int size = 1;
		if(this.object instanceof Collection){
			Collection temp = (Collection)this.object;
			size = temp.size();
		}
		
		for(int i = 0 ; i<size ; i++){
			
			Map<String , Object> oneModel= null;
			if(i<addProperties.size()){
				oneModel = addProperties.get(i);
			}
			if(oneModel == null){
				oneModel = new LinkedHashMap<String, Object>();
				addProperties.add(oneModel);
			}
			Object oneValue = value.get(i);
			oneModel.put(name, oneValue);//增加一个属性
			if(!(oneValue instanceof String) && !(oneValue.getClass().isPrimitive())){//\如果是基本数据类型就不需要增加过滤器
				filterPropertyArray.add(filter);//属性过滤器
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param object  要转换的object   
	 * @param filterProperty  需要过滤掉的字段
	 * @param arrayJSONProperty  需要增加的属性  List< Map<String , Object>>  object参数是集合类型的话，外层list要与object集合一一对应。如果object非集合类型，就只一个 Map<String , Object> 放入list中即可
	 * @return 一个转换好的json对象
	 * @throws Exception
	 */
    public String buildString() throws Exception{
        String jsonString = null;   
        //日期值处理器   
        JsonConfig jsonConfig = new JsonConfig();   
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());   
        //属性过滤
        PropertyFilter filter = new PropertyFilter() {  
            public boolean apply(Object source, String name, Object value) {  
                if (filterPropertyArray!=null && filterPropertyArray.size()>0 && filterPropertyArray.get(0).contains(name)) {  
                    return true;  
                }  
                return false;  
            }  
        };  
        jsonConfig.setJsonPropertyFilter(filter);  
        if(object != null){   
            if(object instanceof Collection || object instanceof Object[]){   
            	JSONArray jsonArray =  JSONArray.fromObject(object, jsonConfig);
            	if(addProperties!=null && addProperties.size()>0)
            		//for(Map<String, Object> addProperty :arrayJSONProperty){
			        	for(int i = 0 ; i<jsonArray.size() ; i++){
			        		JSONObject jsonObject = jsonArray.getJSONObject(i);
			        		Map<String, Object> addProperty = addProperties.get(i);
			        		
			        		for(String addKey  : addProperty.keySet()){
			        			if(addProperty.get(addKey) instanceof String || addProperty.get(addKey) instanceof Number)
			        				jsonObject.put(addKey, addProperty.get(addKey));
			        			else{
			        				if(filterPropertyArray!=null)
			        					filterPropertyArray.remove(0);
			        				jsonObject.put(addKey, getJSONString(addProperty.get(addKey),filterPropertyArray,null).toString());
			        			}
			        		}
			        		
			        	//}
            		}
            	
            	jsonString = jsonArray.toString();
                //jsonString = JSONArray.fromObject(object, jsonConfig).toString();   
            }else{   
            	JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);   
            	if(addProperties!=null)
            		for(Map<String, Object> addProperty :addProperties){
		                for(String addKey  : addProperty.keySet()){
		                	if(addProperty.get(addKey) instanceof String || addProperty.get(addKey) instanceof Number)
		        				jsonObject.put(addKey, addProperty.get(addKey));
		        			else{
		        				if(filterPropertyArray!=null){
		        					filterPropertyArray.remove(0);
		        				}
		        				jsonObject.put(addKey, getJSONString(addProperty.get(addKey),filterPropertyArray,null).toString());
		        			}
		        		}
            		}
                jsonString = jsonObject.toString();
            }   
        }   
        return jsonString == null ? "{}" : jsonString;   
    }   
	
	
	
	
	
	/**
	 * 
	 * @param object  要转换的object   
	 * @param filterProperty  需要过滤掉的字段
	 * @param arrayJSONProperty  需要增加的属性  List< Map<String , Object>>  object参数是集合类型的话，外层list要与object集合一一对应。如果object非集合类型，就只一个 Map<String , Object> 放入list中即可
	 * @return 一个转换好的json对象
	 * @throws Exception
	 */
    public static String getJSONString(Object object , final List<List<String>> filterPropertyArray  ,List< Map<String , Object>> arrayJSONProperty) throws Exception{   
        String jsonString = null;   
        //日期值处理器   
        JsonConfig jsonConfig = new JsonConfig();   
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());   
        //属性过滤
        PropertyFilter filter = new PropertyFilter() {  
            public boolean apply(Object source, String name, Object value) {  
                if (filterPropertyArray!=null && filterPropertyArray.size()>0 && filterPropertyArray.get(0).contains(name)) {  
                    return true;  
                }  
                return false;  
            }  
        };  
        jsonConfig.setJsonPropertyFilter(filter);  
        if(object != null){   
            if(object instanceof Collection || object instanceof Object[]){   
            	JSONArray jsonArray =  JSONArray.fromObject(object, jsonConfig);
            	if(arrayJSONProperty!=null && arrayJSONProperty.size()>0)
            		//for(Map<String, Object> addProperty :arrayJSONProperty){
			        	for(int i = 0 ; i<jsonArray.size() ; i++){
			        		JSONObject jsonObject = jsonArray.getJSONObject(i);
			        		Map<String, Object> addProperty = arrayJSONProperty.get(i);
			        		
			        		for(String addKey  : addProperty.keySet()){
			        			if(addProperty.get(addKey) instanceof String || addProperty.get(addKey) instanceof Number)
			        				jsonObject.put(addKey, addProperty.get(addKey));
			        			else{
			        				if(filterPropertyArray!=null)
			        					filterPropertyArray.remove(0);
			        				jsonObject.put(addKey, getJSONString(addProperty.get(addKey),filterPropertyArray,null).toString());
			        			}
			        		}
			        		
			        	//}
            		}
            	
            	jsonString = jsonArray.toString();
                //jsonString = JSONArray.fromObject(object, jsonConfig).toString();   
            }else{   
            	JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);   
            	if(arrayJSONProperty!=null)
            		for(Map<String, Object> addProperty :arrayJSONProperty){
		                for(String addKey  : addProperty.keySet()){
		                	if(addProperty.get(addKey) instanceof String || addProperty.get(addKey) instanceof Number)
		        				jsonObject.put(addKey, addProperty.get(addKey));
		        			else{
		        				if(filterPropertyArray!=null){
		        					filterPropertyArray.remove(0);
		        				}
		        				jsonObject.put(addKey, getJSONString(addProperty.get(addKey),filterPropertyArray,null).toString());
		        			}
		        		}
            		}
                jsonString = jsonObject.toString();
            }   
        }   
        return jsonString == null ? "{}" : jsonString;   
    }   
	
    
    
    /**  
     * 从一个JSON 对象字符格式中得到一个java对象，形如：  
     * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}}  
     * @param object  
     * @param clazz  
     * @return  
     */  
    public static Object getDTO(String jsonString, Class clazz){   
        JSONObject jsonObject = null;   
        try{   
            jsonObject = JSONObject.fromObject(jsonString);   
        }catch(Exception e){   
            e.printStackTrace();   
        }   
        return JSONObject.toBean(jsonObject, clazz);   
    }   
       
    /**  
     * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如：  
     * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...},  
     * beansList:[{}, {}, ...]}  
     * @param jsonString  
     * @param clazz  
     * @param map 集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" : Bean.class)  
     * @return  
     */  
    public static Object getDTO(String jsonString, Class clazz, Map map){   
        JSONObject jsonObject = null;   
        try{   
            jsonObject = JSONObject.fromObject(jsonString);   
        }catch(Exception e){   
            e.printStackTrace();   
        }   
        return JSONObject.toBean(jsonObject, clazz, map);   
    }   
       
    /**  
     * 从一个JSON数组得到一个java对象数组，形如：  
     * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]  
     * @param object  
     * @param clazz  
     * @return  
     */  
    public static Object[] getDTOArray(String jsonString, Class clazz){   
        JSONArray array = JSONArray.fromObject(jsonString);   
        Object[] obj = new Object[array.size()];   
        for(int i = 0; i < array.size(); i++){   
            JSONObject jsonObject = array.getJSONObject(i);   
            obj[i] = JSONObject.toBean(jsonObject, clazz);   
        }   
        return obj;   
    }   
       
    /**  
     * 从一个JSON数组得到一个java对象数组，形如：  
     * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]  
     * @param object  
     * @param clazz  
     * @param map  
     * @return  
     */  
    public static Object[] getDTOArray(String jsonString, Class clazz, Map map){   
        JSONArray array = JSONArray.fromObject(jsonString);   
        Object[] obj = new Object[array.size()];   
        for(int i = 0; i < array.size(); i++){   
            JSONObject jsonObject = array.getJSONObject(i);   
            obj[i] = JSONObject.toBean(jsonObject, clazz, map);   
        }   
        return obj;   
    }   
       
    /**  
     * 从一个JSON数组得到一个java对象集合  
     * @param object  
     * @param clazz  
     * @return  
     */  
	public static List getDTOList(String jsonString, Class clazz){   
        JSONArray array = JSONArray.fromObject(jsonString);   
        List list = new LinkedList();   
        for(Iterator iter = array.iterator(); iter.hasNext();){   
            JSONObject jsonObject = (JSONObject)iter.next();   
            list.add(JSONObject.toBean(jsonObject, clazz));   
        }   
        return list;   
    }   
       
    /**  
     * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性  
     * @param object  
     * @param clazz  
     * @param map 集合属性的类型  
     * @return  
     */  
    public static List getDTOList(String jsonString, Class clazz, Map map){   
        JSONArray array = JSONArray.fromObject(jsonString);   
        List list = new ArrayList();   
        for(Iterator iter = array.iterator(); iter.hasNext();){   
            JSONObject jsonObject = (JSONObject)iter.next();   
            list.add(JSONObject.toBean(jsonObject, clazz, map));   
        }   
        return list;   
    }   
       
    /**  
     * 从json HASH表达式中获取一个map，该map支持嵌套功能  
     * 形如：{"id" : "johncon", "name" : "小强"}  
     * 注意commons-collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap  
     * @param object  
     * @return  
     */  
    public static Map getMapFromJson(String jsonString) {   
        JSONObject jsonObject = JSONObject.fromObject(jsonString);   
        Map map = new HashMap();   
        for(Iterator iter = jsonObject.keys(); iter.hasNext();){   
            String key = (String)iter.next();   
            map.put(key, jsonObject.get(key));   
        }   
        return map;   
    }   
       
    /**  
     * 从json数组中得到相应java数组  
     * json形如：["123", "456"]  
     * @param jsonString  
     * @return  
     */  
    public static Object[] getObjectArrayFromJson(String jsonString) {   
        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        return jsonArray.toArray();   
    }   
  
  
    /**  
     * 把数据对象转换成json字符串  
     * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}  
     * 数组对象形如：[{}, {}, {}, ...]  
     * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}  
     * @param object  
     * @return  
     */  
    public static String getJSONString(Object object) throws Exception{   
        String jsonString = null;   
        //日期值处理器   
        JsonConfig jsonConfig = new JsonConfig();   
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());   
        if(object != null){   
            if(object instanceof Collection || object instanceof Object[]){   
                jsonString = JSONArray.fromObject(object, jsonConfig).toString();   
            }else{   
                jsonString = JSONObject.fromObject(object, jsonConfig).toString();   
            }   
        }   
        return jsonString == null ? "{}" : jsonString;   
    }   
    
    /**
	 * 将JSONObjec对象转换成Map-List集合
	 * @see JSONHelper#reflect(JSONArray)
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> reflect(JSONObject json){
		HashMap<String, Object> map = new HashMap<String, Object>();
		Set<?> keys = json.keySet();
		for(Object key : keys){
			Object o = json.get(key);
			if(o instanceof JSONArray)
				map.put((String) key, reflect((JSONArray) o));
			else if(o instanceof JSONObject)
				map.put((String) key, reflect((JSONObject) o));
			else
				map.put((String) key, o);
		}
		return map;
	}

	/**
	 * 将JSONArray对象转换成Map-List集合
	 * @see JSONHelper#reflect(JSONObject)
	 * @param json
	 * @return
	 */
	public static Object reflect(JSONArray json){
		List<Object> list = new ArrayList<Object>();
		for(Object o : json){
			if(o instanceof JSONArray)
				list.add(reflect((JSONArray) o));
			else if(o instanceof JSONObject)
				list.add(reflect((JSONObject) o));
			else
				list.add(o);
		}
		return list;
	}
    
    
    
    public static void main(String[] args){
//    	String str = "[{\"questionId\":1010,\"answer\":\"test\"},{\"questionId\":1011,\"answer\":\"test2\"}]";
//    	Object[] objs= JSONUtils.getDTOArray(str, AnswerTempModel.class);
//    	LogUtils.log.info(objs.length);
    }
    
}
