package com.zizaihome.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReadUtils {

	private static  Map<String , Properties> props = new HashMap<String , Properties>();
	
	static{
		System.setProperty("api.base.dir", System.getProperty("user.dir"));
		LogUtils.log.info(System.getProperty("user.dir"));
		System.setProperty("api.conf.dir", System
				.getProperty("api.base.dir")
				+ File.separator + "conf");
		
	}
	
	public synchronized static Properties loadConfig(String filename) {
		if(props.get(filename)!=null){
			return props.get(filename);
		}
		Properties p = new Properties();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input = cl.getResourceAsStream(filename);

		if (input != null) {
			try {
				BufferedReader configInput = new BufferedReader(
						new InputStreamReader(input, "gbk"));
				p.load(configInput);
			} catch (FileNotFoundException e) {
				System.err.println("配置文件" + filename + "找不到！");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("读取配置文件" + filename + "错误！");
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				input = null;
			}
		}
		
		props.put(filename, p);
		
		return p;
	}
	
	/**
	 * 读取conf目录底下的配置文件
	 * @param filename
	 * @return
	 */
	public synchronized static Properties loadConfigByConfDir(String filename) {
		if(props.get(filename)!=null){
			return props.get(filename);
		}
		Properties p = new Properties();
		//ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input=null;
		try {
			input = new FileInputStream(System.getProperty("api.conf.dir")
				    + File.separator + filename);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (input != null) {
			try {
				BufferedReader configInput = new BufferedReader(
						new InputStreamReader(input, "gbk"));
				p.load(configInput);
			} catch (FileNotFoundException e) {
				System.err.println("配置文件" + filename + "找不到！");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("读取配置文件" + filename + "错误！");
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				input = null;
			}
		}
		
		props.put(filename, p);
		
		return p;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
