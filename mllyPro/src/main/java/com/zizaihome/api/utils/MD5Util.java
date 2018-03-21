package com.zizaihome.api.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",  
        "8", "9", "a", "b", "c", "d", "e", "f"};  

/** 
 * 转换字节数组为16进制字串 
 * @param b 字节数组 
 * @return 16进制字串 
 */  
public static String byteArrayToHexString(byte[] b) {  
    StringBuilder resultSb = new StringBuilder();  
	    for (byte aB : b) {  
	        resultSb.append(byteToHexString(aB));  
	    }  
	    return resultSb.toString();  
	}  
	
	/** 
	 * 转换byte到16进制 
	 * @param b 要转换的byte 
	 * @return 16进制格式 
	 */  
	private static String byteToHexString(byte b) {  
	    int n = b;  
	    if (n < 0) {  
	        n = 256 + n;  
	    }  
	    int d1 = n / 16;  
	    int d2 = n % 16;  
	    return hexDigits[d1] + hexDigits[d2];  
	}  
	
	/** 
	 * MD5编码 
	 * @param origin 原始字符串 
	 * @return 经过MD5加密之后的结果 
	 */  
	public static String getMD5(String str) {  
	    String resultString = null;  
	    try {  
	        resultString = str;  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(resultString.getBytes("UTF-8"));  
	        resultString = byteArrayToHexString(md.digest());  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return resultString;  
	} 
//	public static String getMD5(String str) {
//
//        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};         
//        try {  
//            byte[] btInput = str.getBytes();  
//            // 获得MD5摘要算法的 MessageDigest 对象  
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
//            // 使用指定的字节更新摘要  
//            mdInst.update(btInput);  
//            // 获得密文  
//            byte[] md = mdInst.digest();  
//            // 把密文转换成十六进制的字符串形式  
//            int j = md.length;  
//            char strList[] = new char[j * 2];  
//            int k = 0;  
//            for (int i = 0; i < j; i++) {  
//                byte byte0 = md[i];  
//                strList[k++] = hexDigits[byte0 >>> 4 & 0xf];  
//                strList[k++] = hexDigits[byte0 & 0xf];  
//            }  
//            return new String(str);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            return null;  
//        } 
//	}
}
