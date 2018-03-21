package com.zizaihome.api.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 处理一些较低优先级的任务
 * @author apple
 *
 */
public class LowLevelTaskThreadPool {

	// 构造一个线程池  
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 1, TimeUnit. SECONDS ,  
	new ArrayBlockingQueue<Runnable>(5000), new ThreadPoolExecutor.DiscardOldestPolicy() );  
	
	public static ThreadPoolExecutor getThreadPool() {
		return threadPool;
	}

	/**
	 * @param args
	 */

}
