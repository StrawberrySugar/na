package com.zizaihome.api.utils;

import com.coola.jutil.encrypt.MD5Coding;
import com.zizaihome.api.exception.DecodeAccessTokenException;
import com.zizaihome.api.exception.DecodeVisitTokenException;
//import com.coola.jutil.crypto.MD5Coding;


public class ZizaihomeToken {
	
	private static String TOKEN_KEY="_a";
	private static String TOKEN_KEY_XMPP="_axmpp";
	
	
	/**
	 * 根据userid生成accesstoken；
	 * @param userId
	 * @return
	 */ 
	public static String encodeToken(int userId , String password){
		String access_token = EncryUtil.encode(userId+TOKEN_KEY+"_"+password);
		return access_token;
	}
	
	/**
	 * 根据userid生成accesstoken；
	 * @param userId
	 * @return
	 */ 
	public static String encodeToken2(int userId , String password,String uuId){
		String access_token = EncryUtil.encode(userId+TOKEN_KEY+"_"+password+"_"+uuId);
		return access_token;
	}
	
	/**
	 * 返回userid
	 * @param token
	 * @return
	 * @throws DecodeAccessTokenException 
	 */
	public static int decodeToken(String token) throws DecodeAccessTokenException{
		int userId = 0;
		try{
			String decodeToken = EncryUtil.decode(token);
			String userIdstr = decodeToken.split("_")[0];
			userId = Integer.valueOf(userIdstr);
		}catch(Exception e){
			//e.printStackTrace();
			throw new DecodeAccessTokenException("decode access_token出错");
		}
		return userId; 
		 
	}
	
	
	/**
	 * 返回userid
	 * @param token
	 * @return
	 * @throws DecodeAccessTokenException 
	 */
	public static String decodeTokenGetPassword(String token) throws DecodeAccessTokenException{
		String password="";
		try{
			String decodeToken = EncryUtil.decode(token);
			password = decodeToken.split("_")[2];
		}catch(Exception e){
			//e.printStackTrace();
			throw new DecodeAccessTokenException("decode access_token出错");
		}
		return password; 
		 
	}
	
	/**
	 * 返回解析token之后的数组长度（用于判断是否含有uuId，含有uuId长度为4，不包含长度为3）
	 * @param token
	 * @return
	 * @throws DecodeAccessTokenException 
	 */
	public static int decodeTokenGetListLength(String token) throws DecodeAccessTokenException{
		int uuId=0;
		try{
			String decodeToken = EncryUtil.decode(token);
			LogUtils.log.info(decodeToken);
			uuId = decodeToken.split("_").length;
		}catch(Exception e){
			//e.printStackTrace();
			throw new DecodeAccessTokenException("decode access_token出错");
		}
		return uuId; 
		 
	}
	
	
	/**
	 * 根据userid生成用于访问个人信息的token；
	 * @param userId
	 * @return
	 */
	public static String encodeVisitToken(int userId){
		String access_token = EncryUtil.encode(TOKEN_KEY+"_"+userId);
		return access_token;
	}
	
	/**
	 * 返回userid
	 * @param token
	 * @return
	 * @throws DecodeVisitTokenException 
	 */
	public static int decodeVisitToken(String token) throws DecodeVisitTokenException{
		if(token.startsWith("1000000")){
			return Integer.valueOf(token);
		}
		int userId = 0;
		try{
			String decodeToken = EncryUtil.decode(token);
			String userIdstr = decodeToken.split("_")[2];
			userId = Integer.valueOf(userIdstr);
		}catch(Exception e){
			throw new DecodeVisitTokenException("visit token出错");
		}
		return userId; 
		 
	}
	
	/**
	 * 根据userid生成用于访问xmpp服务器的token；
	 * @param userId
	 * @param password
	 * @return
	 */
	public static String encodeXMPPAccount(int userId , String password){
		String access_token = MD5Coding.encode2HexStr((userId+TOKEN_KEY_XMPP+"_"+password+System.currentTimeMillis()).getBytes()).toLowerCase();
		return access_token;
	}
	
	
	public static void main(String[] args) throws DecodeAccessTokenException{
		LogUtils.log.info(ZizaihomeToken.encodeToken(100000727, null)); 
//		LogUtils.log.info(CDTToken.decodeToken("tewdajbntawpBj1qi3nr4zprk1ua")+"");
	
	}
	
}
