package com.zizaihome.api.utils;

import java.io.UnsupportedEncodingException;

public class SplitStringUtils {

	/**  
     * 字符串按字节截取(保证不截断中文（2个字节）)
     * @param str 原字符  
     * @param len 截取长度  
     * @param elide 省略符  
     * @return String  
    * @throws UnsupportedEncodingException 
     */  
     public static String splitString(String str,int len,String elide) throws UnsupportedEncodingException {   
            if (str == null) {   
                   return "";   
            }   
            byte[] strByte=null;
			try {
				strByte = str.getBytes("gbk");//因为某些机器默认是utf-8编码，utf-8中文占用3个字节，所以需要转成gbk
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}   
            int strLen = strByte.length;   
            LogUtils.log.info(strLen+"");
            int elideLen = 0;   
            if (len >= strLen || len < 1) {   
                   return str;   
            }   
            if (len - elideLen > 0) {   
                   len = len - elideLen;   
            }   
            int count = 0;   
            for (int i = 0; i < len; i++) {   
                   int value = (int) strByte[i];   
                   if (value < 0) {   
                          count++;   
                   }   
            }   
            if (count % 2 != 0) {   
                   len = (len == 1) ? len + 1 : len - 1;   
            }   
            return new String(strByte, 0, len , "gbk") + elide.trim();   
     }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
