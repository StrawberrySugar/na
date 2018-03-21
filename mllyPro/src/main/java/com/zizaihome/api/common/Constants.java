package com.zizaihome.api.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class Constants {
    /**
     * 默认获取的记录个数
     */
    public static int DEFAULT_COUNT = 20;

    public static int SQL_TIMEOUT = 1000;

    /**
     * PORT 11001
     */
    public static int PORT = 11001;
    public static int HTTPS_PORT = 11002;
    public static int UPLOAD_FILE_SIZE = 50;
    public static String redisPassword = "asdlkncoaoweom,zxc";
    public static String hessianurl_queue = "";
    
    public static int SOCKET_PORT = 11000;
    
    
    /**
     * 将表情符号对应替换为文字。
     */
    public static Map<String, String> faceMap = new ConcurrentHashMap<String, String>();

       /**
     * 请求超时配置
     */
    public static int TIMEOUT = 10000; // 超过10秒钟，同时打印到timeout日志

    public static String XMLROOT = "root"; // xml文档根节点名称
    public static String TASK = "task"; // 任务节点名称
    public static String DISCOUNT = "discount"; // 优惠节点名称
    public static String ADDRESS = "address"; // 地点节点名称
    public static String RESULT = "result"; // 结果节点名称

    public static final int FROM_PLACE_ID = 15; //
    public static final int DIGU_UID = 10010231; // 嘀咕团队uid
    
    public static String BLOGNAME = "blog_name"; // 社区名称
    
    
    public static int OTHER_FUNNY = 0; // 
    public static int ME_FUNNY = 1; // 
    public static int TOBE_FUNNY = 2; // 
    
    public static final String USER_DEFAULT_USER_HEAD ="7cadefdac798460297d8aab830745e4c0001.png";//邀请链接URI
    
    
    //七牛访问参数
  	public static final String QINIU_ACCESS_KEY = "uOLlfdbDvUrLvvfZ9MRwIsrrVg_6pEtuxeWajiKu";
  	public static final String QINIU_SECRET_KEY = "AvW2PZPUkgy-vlHhI6gt1YQff2QtlksrXV3OwEzV";
  	public static final String QINIU_BUCKETNAME_IMG = "imgs";
  	public static final String QINIU_URL = "https://pic.zizaihome.com";
    
    
    
    
}
