package com.zizaihome.api.utils;

import java.util.Random;

public class RandomUtils {

	 /** 
     * 产生一个随机的字符串  大写字母小写字母和数字
     *   
     * @param 字符串长度 
     * @return 
     */ 
    public static String getRandomString(int length) {   
        Random random=new Random();   
        StringBuffer sb=new StringBuffer();   
        for(int i=0;i<length;i++){   
            int number=random.nextInt(3);   
            long result=0;   
            switch(number){   
                case 0:   
                    result = Math.round(Math.random()*25+65);   
                    sb.append(String.valueOf((char)result));   
                    break;   
                case 1:   
                    result = Math.round(Math.random()*25+97);   
                    sb.append(String.valueOf((char)result));   
                    break;   
                case 2:   
                    sb.append(String.valueOf(new Random().nextInt(10)));   
                    break;   
            }   
        }   
        return sb.toString();   
    }   
	
	 /** 
     * 产生一个随机的字符串  数字
     *   
     * @param 字符串长度 
     * @return 
     */ 
    public static String getRandomNumber(int length) {   
        StringBuffer sb=new StringBuffer();   
        for(int i=0;i<length;i++){   
            sb.append(String.valueOf(new Random().nextInt(10)));   
        }   
        return sb.toString();
    }   
    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
