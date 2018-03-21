package com.zizaihome.api.cache.redis;


import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RBucket;

import com.zizaihome.api.utils.ConfigReadUtils;
import com.zizaihome.api.utils.LogUtils;

/**
 * 来redis进行缓存 采用LRU算法
 * @author luochangming
 *
 */
public class RedisCDTClient {

	private static Redisson redisson = null;
	
	static {
		//配置服务器地址  redis支持集群
		String redis_address = ConfigReadUtils.loadConfigByConfDir("qinqinconfig.properties").getProperty("redis_address");
		Config config = new Config();
		config.useSingleServer().setAddress(redis_address);
		redisson = Redisson.create(config);
	}
	
	/**
	 * 缓存一个对象
	 * @param key
	 * @param value 对象必须继承RBucket
	 */
	public static void addObject(String key , RBucket<Object> value){
		RBucket<Object> bucket = redisson.getBucket(key);
		bucket.set(value);
	}
	
	/**
	 * 缓存一个String
	 * @param key
	 * @param value
	 */
	public static void addStr(String key , String value){
		RBucket<Object> bucket = redisson.getBucket(key);
		bucket.set(value);
	}
	
	/**
	 * 获取一个缓存的对象
	 * @param key
	 * @return
	 */
	public static Object getObject(String key){
		RBucket<Object> bucket = redisson.getBucket(key);
		Object obj = bucket.get();
		return obj;
	}
	
	/**
	 * 获取一个缓存的String
	 * @param key
	 * @return
	 */
	public static String getStr(String key){
		RBucket<String> bucket = redisson.getBucket(key);
		String obj = bucket.get();
		return obj;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisCDTClient.addStr("test", "test1");
		LogUtils.log.info(RedisCDTClient.getStr("test"));
		
//		CdtUserModel cModel = CdtUserDao.getInstance().findByPrimaryKey(1000002103);
		
//		RedisCDTClient.addObject("1000002103", cModel);
//		LogUtils.log.info(((CdtUserModel)RedisCDTClient.getObject("1000002103")).getNickName());
	}

}
