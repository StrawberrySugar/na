package com.zizaihome.api.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUtils {
	
	public static String qiniuToken = ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("qiniu_token");
	public static String picturePath = ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("picture_path");
	public static String qiniuPicHost = ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("qiniu_pic_host");
	
	public static void main(String args[]){
		String qiniu_access_key = ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("qiniu_access_key");
		String qiniu_secret_key = ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("qiniu_secret_key");
		String bucket = "imgs";
		Auth auth = Auth.create(qiniu_access_key, qiniu_secret_key);
		long expireSeconds = 2*365*24*60*60;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, null);
		LogUtils.log.info(upToken);
	}
	
	public static String uploadPic2QiNiu(String filePath,String picurl){
		File file = null;
		String key = "";
		//如果直接传入文件路径直接使用路径获取文件
		if(picurl.equals("")){
			file = new File(filePath);
			//上传到七牛后保存的文件名
			key = MD5Util.getMD5(filePath.replace(picturePath, "")+""+System.currentTimeMillis())+".jpg";
		}
		//如果传入图片url去拉取图片到本地再获取文件
		else{
			try {
				// 构造URL
				URL url = new URL(picurl);
		        // 打开连接
		        URLConnection con = url.openConnection();
		        // 设置请求超时为5s
		        con.setConnectTimeout(5 * 1000);
		        // 输入流
		        InputStream is = con.getInputStream();
		        //上传到七牛后保存的文件名
		        key = MD5Util.getMD5(picurl+System.currentTimeMillis())+".jpg";
		        //将图片保存至本地
		        FileOutputStream fos = new FileOutputStream(picturePath+key);
		        byte[] b = new byte[1024];
		        file = new File(picturePath+key);
		        if (!file.exists()) {
		        	file.mkdirs();
		        }
		        OutputStream os = new FileOutputStream(picturePath+key);
		        // 开始读取
		        int len;
		        while ((len = is.read(b)) != -1) {
		            os.write(b, 0, len);
		        }
		        // 完毕，关闭所有链接
		        os.close();
		        is.close();
		        filePath = picturePath+key;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
		//密钥配置
		//创建上传对象
		UploadManager uploadManager = new UploadManager();
		try {
			//调用put方法上传
//			if(!KeyName.equals("")){
//				key = KeyName;
//			}
			Response res = uploadManager.put(filePath, key, qiniuToken);
			//打印返回的信息
			System.out.println(res.bodyString()); 
		} catch (QiniuException e) {
			Response r = e.response;
			try {
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			file.delete();
			return "";
		}      
		file.delete();
		return qiniuPicHost+"/"+key;
	}
}
