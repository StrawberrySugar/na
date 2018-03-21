package com.zizaihome.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

public class RedisCached {
	
	private static int expire_time = 60*60*24*30;//一个月
	
	private static Object ByteToObject(byte[] bytes) {  
		Object obj = null;  
		try {  
		    // bytearray to object  
		    ByteArrayInputStream bi = new ByteArrayInputStream(bytes);  
		    ObjectInputStream oi = new ObjectInputStream(bi);  
		  
		    obj = oi.readObject();  
		    bi.close();  
		    oi.close();  
		} catch (Exception e) {  
		    System.out.println("translation" + e.getMessage());  
		    e.printStackTrace();  
		}  
		       return obj;  
	}  
	
    private static byte[] ObjectToByte(java.lang.Object obj) {  
        byte[] bytes = null;  
        try {  
            // object to bytearray  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();  
            ObjectOutputStream oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
      
            bytes = bo.toByteArray();  
      
            bo.close();  
            oo.close();  
        } catch (Exception e) {  
            System.out.println("translation" + e.getMessage());  
            e.printStackTrace();  
        }  
        return bytes;  
    }  
	
	public static void setObjectCached(String key,Object value){
		setObjectCached(key,value,expire_time);
	}
	
	public static void setObjectCached(String key,Object value,int time){
		Jedis jedis = RedisUtils.getJedis();
		if(jedis != null){
			byte[] bytes = ObjectToByte(value);
			jedis.set(key.getBytes(), bytes);
			jedis.expire(key.getBytes(), time);
		}
		jedis.close();
	}
	
	public static <T> T getObjectCached(String key,Class<T> clazz){
		T value = null;
		Jedis jedis = RedisUtils.getJedis();
		if(jedis != null){
			byte[] bytes = jedis.get(key.getBytes());
			if(bytes != null){
				value = (T) ByteToObject(bytes);
			}
			jedis.close();
		}
		return value;
	}
	
	public static Object hasObject(String key){
		Jedis jedis = RedisUtils.getJedis();
		Object o = null;
		if(jedis != null){
			 o=jedis.get(key.getBytes());
			 jedis.close();
		}
		return o;
	}
	
	public static void del(String key){
		Jedis jedis = RedisUtils.getJedis();
		if(jedis != null){
			jedis.del(key);
			jedis.close();
		}
	}
}
