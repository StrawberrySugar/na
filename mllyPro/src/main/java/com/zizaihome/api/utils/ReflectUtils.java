package com.zizaihome.api.utils;

import java.lang.reflect.Method;

public class ReflectUtils {

	/**
    * @param obj
    *            操作的对象
    * @param att
    *            操作的属性
    * @return 
    * */
   public static Object getter(Object obj, String att) {
       try {
           Method method = obj.getClass().getMethod("get" + Character.toUpperCase(att.charAt(0))+att.substring(1));
           //LogUtils.log.info(method.invoke(obj));
           return method.invoke(obj);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }

   /**
    * @param obj
    *            操作的对象
    * @param att
    *            操作的属性
    * @param value
    *            设置的值
    * @param type
    *            参数的属性
    * */
   public static void setter(Object obj, String att, Object value,
           Class<?> type) {
       try {
           Method method = obj.getClass().getMethod("set" + Character.toUpperCase(att.charAt(0))+att.substring(1), type);
           method.invoke(obj, value);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	
}
