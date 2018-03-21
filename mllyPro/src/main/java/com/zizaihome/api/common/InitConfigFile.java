package com.zizaihome.api.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InitConfigFile {
    private static final Logger errorLog = LoggerFactory.getLogger("error");
    private static final Logger infoLog = LoggerFactory.getLogger("info");

    public static final String config = "config.properties";

    /**
     * 初始化config.properties文件中的配置。
     * 配置 http端口
     * https端口
     * 上传文件个数
     */
    public static void init() {
	initFaceMap();
	//暂时不初始化来源
	//initAppMap("");
	InputStream is = null;
	ClassLoader cl = Thread.currentThread().getContextClassLoader();
	try {
	    File f = new File(System.getProperty("api.conf.dir")
		    + File.separator + config);
	    if (f.exists()) {
		is = new FileInputStream(f);
		infoLog.info("read " + config + " from "
			+ System.getProperty("api.conf.dir"));
	    } else {
		is = cl.getResourceAsStream(config);
	    }
	    ResourceBundle prop = new PropertyResourceBundle(is);
	    String port = prop.getString("port");
	    if (StringUtils.isNotEmpty(port)) {
		Constants.PORT = Integer.parseInt(port);
	    }
	    String https_port = prop.getString("https_port");
	    if (StringUtils.isNotEmpty(https_port)) {
		Constants.HTTPS_PORT = Integer.parseInt(https_port);
	    }
	    String upload_file_size = prop.getString("upload_file_size");
	    if (StringUtils.isNotEmpty(upload_file_size)) {
		Constants.UPLOAD_FILE_SIZE = Integer.parseInt(upload_file_size);
	    }
	    String redisPassword = prop.getString("redisPassword");
	    if (StringUtils.isNotEmpty(redisPassword)) {
	    	Constants.redisPassword = redisPassword;
	    }
	   /* String hessianurl_queue = prop.getString("hessianurl_queue");
	    if (StringUtils.isNotEmpty(hessianurl_queue)) {
	    	Constants.hessianurl_queue = hessianurl_queue;
	    }*/

	} catch (FileNotFoundException e) {
	    errorLog.error("读取properties文件出错。找不到文件。", e);
	} catch (IOException e) {
	    errorLog.error("读取properties文件出错。有文件，读取错误。", e);
	} finally {
	    if (is != null) {
		IOUtils.closeQuietly(is);
	    }
	}

    }

    /**
     * 初始换表情map对应数据
     */
    private static void initFaceMap() {
	Constants.faceMap.put("01", "{微笑}");
	Constants.faceMap.put("02", "{我晕}");
	Constants.faceMap.put("03", "{口水}");
	Constants.faceMap.put("04", "{开心}");
	Constants.faceMap.put("05", "{鄙视}");
	Constants.faceMap.put("06", "{我汗}");
	Constants.faceMap.put("07", "{好爽}");
	Constants.faceMap.put("08", "{偷笑}");
	Constants.faceMap.put("09", "{暴走}");
	Constants.faceMap.put("10", "{垂泪}");
	Constants.faceMap.put("11", "{死定}");
	Constants.faceMap.put("12", "{傲慢}");
	Constants.faceMap.put("13", "{发怒}");
	Constants.faceMap.put("14", "{害羞}");
	Constants.faceMap.put("15", "{吃惊}");
	Constants.faceMap.put("16", "{瞌睡}");
	Constants.faceMap.put("17", "{阴险}");
	Constants.faceMap.put("18", "{伤心}");
	Constants.faceMap.put("19", "{郁闷}");
	Constants.faceMap.put("20", "{摇头}");
	Constants.faceMap.put("21", "{牛逼}");
	Constants.faceMap.put("22", "{呕吐}");
	Constants.faceMap.put("23", "{可怜}");
	Constants.faceMap.put("24", "{耍酷}");
	Constants.faceMap.put("25", "{雷死}");
	Constants.faceMap.put("26", "{怒吼}");
	Constants.faceMap.put("27", "{啥玩意儿？}");
    }

    @SuppressWarnings("deprecation")
    public static String getFilePath(String fileName) {
	URL filePath = ClassLoader.getSystemResource(fileName);
	if (filePath == null) {
	    errorLog.error("找不到文件" + fileName);
	    return null;
	}
	String path = filePath.getPath();
	if (StringUtils.isEmpty(path)) {
	    errorLog.error("找不到文件" + fileName);
	    return null;
	}
	if (SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_WINDOWS_2000
		|| SystemUtils.IS_OS_WINDOWS_95 || SystemUtils.IS_OS_WINDOWS_98
		|| SystemUtils.IS_OS_WINDOWS_ME || SystemUtils.IS_OS_WINDOWS_NT
		|| SystemUtils.IS_OS_WINDOWS_XP) {
	    path = URLDecoder.decode(path);
	    if (0 == StringUtils.indexOf(path, "/")) {
		path = StringUtils.removeStart(path, "/");
	    }
	}
	return path;
    }

 
    /**
     * 初始化source来源
     * 
     * @param fileName
     */
    
    /**
     * 
    private static void initAppMap(String fileName) {
	try {
	    TwitterFromPlaceDao instance = TwitterFromPlaceDao.getInstance();
	    List<TwitterFromPlace> selectAllFromPlace = instance
		    .selectAllFromPlace();
	    for (TwitterFromPlace tfp : selectAllFromPlace) {
		Constants.apps.put(tfp.getName(), tfp);
		Constants.apps.put(String.valueOf(tfp.getId()), tfp);
	    }
	} catch (Exception e) {
	    LogUtils.log.info("加载source出错。异常：" + e);
	}
    }
    */
}
