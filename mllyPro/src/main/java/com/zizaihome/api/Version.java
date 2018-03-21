package com.zizaihome.api;

/**
 * 当前版本 
 * 
 * @author xuzhenqing 
 * 
 */
public final class Version {
    public final static String MAJOR = "1";
    public final static String MINOR = "1";
    public static String getVersion(){
	return MAJOR+"."+MINOR;
    }
}
