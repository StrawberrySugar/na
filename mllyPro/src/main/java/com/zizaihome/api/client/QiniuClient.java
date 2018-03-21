package com.zizaihome.api.client;

import java.io.File;
import java.util.Random;
import java.util.UUID;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zizaihome.api.common.Constants;
import com.zizaihome.api.utils.LogUtils;

public class QiniuClient {
	
	public static String uploadFile(File file){
		String logImageName = "";
		/** 构建文件保存的目录* */
//		String logoPathDir = "/tmp/";
		/** 页面控件的文件流* */
		/** 获取文件的后缀* */
		String suffix = file.getName().substring(file.getName().lastIndexOf("."));
		/** 使用UUID生成文件名称* */
		logImageName = System.currentTimeMillis() + suffix;// 构建文件名称
		/** 拼成完整的文件保存路径加文件* */
//		String fileName = logoPathDir + File.separator + logImageName;
//		item.write(file);
//		uploadFlag = true;
		//上传到七牛后保存的文件名
		String key = logImageName;
		//密钥配置
		Auth auth = Auth.create(Constants.QINIU_ACCESS_KEY, Constants.QINIU_SECRET_KEY);
		//创建上传对象
		UploadManager uploadManager = new UploadManager();
		String token = auth.uploadToken(Constants.QINIU_BUCKETNAME_IMG);
		try {
			//调用put方法上传
			Response res = uploadManager.put(file.getAbsoluteFile(), key, token);
			//打印返回的信息
			LogUtils.log.info(res.bodyString()); 
		} catch (QiniuException e) {
			Response r = e.response;
			try {
				LogUtils.log.info(r.bodyString());
			} catch (QiniuException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			file.delete();
			return null;
		}
		return Constants.QINIU_URL+"/"+logImageName;
	}
	
	public static String uploadWebPic(String picUrl){
		if(!picUrl.equals("")){
			
			try{
				Auth auth = Auth.create(Constants.QINIU_ACCESS_KEY, Constants.QINIU_SECRET_KEY);
				Random r = new Random();
				//获取空间管理器
				BucketManager bucketManager = new BucketManager(auth);
				String suffix = picUrl.substring(picUrl.lastIndexOf("."));
				String imageKey = UUID.randomUUID()+".jpg";;
				try {
					DefaultPutRet putret = bucketManager.fetch(picUrl, Constants.QINIU_BUCKETNAME_IMG, imageKey);
				} catch (QiniuException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return Constants.QINIU_URL+"/"+imageKey;
			}
			catch(Exception e){
				LogUtils.log.info(e.toString());
				e.printStackTrace();
			}
		}
		return null;
	}
	

}
