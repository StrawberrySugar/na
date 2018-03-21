package com.zizaihome.api.resources.base;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang.StringUtils;
import org.restlet.Request;
import org.restlet.data.ClientInfo;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizaihome.api.common.Constants;
import com.zizaihome.api.common.utils.GetObjectUtils;
import com.zizaihome.api.utils.ConfigReadUtils;
import com.zizaihome.api.utils.LogUtils;

import net.sf.json.JSONObject;

public abstract  class BaseResource extends ServerResource{
protected static final Logger log = LoggerFactory.getLogger("stdout");
	
	public static final int RESULT_SUCC=1; //接口处理成功
	public static final int RESULT_FAIL =-1 ; //接口处理失败
	public static final int LOGIN_OTHER =-2 ; //接口处理失败
	
	
	public static final int STATIS_NOSNS = -100;
	
	protected String postXML = "";
			
	protected MediaType mediaType;
	// 请求json还是xml
	protected String format;
	// 请求类型
	protected String category;
	// 查询字符串中的参数信息
	protected Map<String, Object> paramsMap;
	protected RestletFileUpload rfuPic = new RestletFileUpload();
	protected List<FileItem> fiList;
	protected Form form;
	protected String callback;
	protected String requestPath;
	protected String realip;
	protected Request request;
	
	protected BaseResource()
	{
		paramsMap = new HashMap<String, Object>();
		mediaType = MediaType.APPLICATION_JSON;
	}
	
	protected void doInit() throws ResourceException
	{
		super.doInit();
//		super.setConditional(false);
		//由于框架发现安卓机请求conditions中带有modifiedSince然后会请求一次get和post方法，所以将modifiedSince设置成null
		getRequest().getConditions().setModifiedSince(null);
		request = getRequest();
		requestPath = request.getResourceRef() == null ? "" : request
				.getResourceRef().getPath();
		rfuPic.setHeaderEncoding("UTF-8");
		rfuPic.setFileSizeMax(Constants.UPLOAD_FILE_SIZE * 1024 * 1024);// 每张图片最大2M
		rfuPic.setFileItemFactory(new DiskFileItemFactory());
		rectifyParamMap(request);
		Map<String, Object> paramMap = request.getAttributes();
		format = paramMap.get("format") == null ? "" : (String) paramMap
				.get("format");
		mediaType = MediaType.APPLICATION_JSON;
		if (StringUtils.equalsIgnoreCase(format, "json"))
		{
			mediaType = MediaType.APPLICATION_JSON;
		}
		else
		{
			mediaType = MediaType.APPLICATION_XML;
		}
		//initParameter();
		initParams(request);
	}
	

	/**
	 * 初始化参数
	 * 
	 * @author 叶佳鑫
	 * @create Feb 17, 2011
	 * @param request
	 */
	protected abstract void initParams(Request request);


	private void rectifyParamMap(Request request)
	{
		// 取头部的信息
		Series sheaders = (Series)getRequest().getAttributes().get(  
	            "org.restlet.http.headers");
		Map<String,String> headers = sheaders.getValuesMap();
		ClientInfo ci = request.getClientInfo();
		
	
//		LogUtils.log.info("headers==========="+headers);
//		LogUtils.log.info("request.getClientInfo()===="+ci.getAddress());
		realip = ci.getAddress();
		Object obj = request.getAttributes().get(
				"org.restlet.http.headers");
//		Form headers1 = new Form(sheaders.get(0));
//		LogUtils.log.info("realip=====headers1=="+headers1);
		if (sheaders!=null)
		{
			Set namesSet = sheaders.getNames();
			Iterator<String> iter =  namesSet.iterator();
			while (iter.hasNext())
			{
				String name = iter.next();
				paramsMap.put(name, sheaders.getFirstValue(name));
				if (name.contains("X-Real-IP"))
				{
					realip = sheaders.getFirstValue(name);
				}
			}
			
		}
		LogUtils.log.info("realip====="+realip);
		/**
		 * 根据不同的请求类型，处理参数的获取
		 */
		// 取get请求的信息
		if (request.getMethod().equals(Method.GET))
		{
			form = request.getResourceRef().getQueryAsForm();
			for (String name : form.getNames())
			{
				try {
					//paramsMap.put(name,form.getFirstValue(name));
					paramsMap.put(name, java.net.URLDecoder.decode( form.getFirstValue(name),"utf-8"));
				} catch (Exception e) {
					paramsMap.put(name,form.getFirstValue(name));
					//e.printStackTrace();
				}
			}
		}
		else if (request.getMethod().equals(Method.POST))
		{
			// 取POST请求的信息
			if (headers != null)
			{
				if (headers.get("Content-Type") != null && headers.get("Content-Type").startsWith(MediaType.TEXT_XML.getName()))
				{
					//获取postXML数据
					try {
						postXML = request.getEntity().getText();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}	
				else if(headers.get("Content-Type") != null && headers.get("Content-Type").startsWith(MediaType.APPLICATION_JSON.getName())){
					paramsMap = JSONObject.fromObject(request.getEntityAsText());
				}
				else if (headers.get("Content-Type") != null && !headers.get("Content-Type").startsWith(MediaType.MULTIPART_FORM_DATA.getName()))
				{
					// 如果不是上传文件(multipart/form-data)
					// 将form中的数据全部转移到paramMap中.
					if (request.getEntity().isAvailable())
					{
						form = new Form(request.getEntity());
						paramsMap.putAll(form.getValuesMap());
					}
				}
				else
				{
					try
					{
						fiList = rfuPic.parseRequest(getRequest());
		                for (final Iterator<FileItem> it = fiList.iterator(); it.hasNext(); ) {
		                    FileItem fi = it.next();
		                    String name = fi.getName();
		                    if (name == null) {
		                    	paramsMap.put(fi.getFieldName(), new String(fi.get(), "UTF-8"));
		                    } else {
		                    	String[] nameList = name.split("/");
		                    	name = nameList[nameList.length-1];
		                    	String picturePath = ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("picture_path");
		                        File file = new File(picturePath + name);
		                        fi.getInputStream();
		                        fi.write(file);
		                        paramsMap.put(fi.getFieldName(), picturePath + name);
		                    }
		                }		
						LogUtils.log.info(fiList.toString());
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						form = new Form(request.getEntity());
//						paramsMap.putAll(form.getValuesMap());
					}
				}
			}
		}
	}
	
	
	@Get
	public Representation get() throws ResourceException
	{
		log.info(this.getClass().getSimpleName()+ "======api request start=====GET");
		JSONObject json = null;
		if(json ==null){
			json = new JSONObject();
			json = getMethod(json);
		}
		String callback =  getParameter("callback", "");
		String returnStr = json.toString();
		if(!callback.equals("")){
			returnStr = callback+"("+returnStr+")";
		}
		
		StringRepresentation representation = new StringRepresentation(returnStr,MediaType.APPLICATION_JSON);
		
		log.info(this.getClass().getSimpleName()+ "======api request end=====GET");
		return representation;
	}
	
	@Post
	public Representation post () throws ResourceException
	{
		log.info(this.getClass().getSimpleName()+ "======api request start=====POST");
		for(Map.Entry<String, Object> entry:paramsMap.entrySet()){
			log.info(entry.getKey()+"="+entry.getValue().toString());
		}
		JSONObject json = null;
		if(json ==null){
			json = new JSONObject();
			json = postMethod(json);
		}
		//增加金币的json
		StringRepresentation representation = new StringRepresentation(json.toString(),MediaType.APPLICATION_JSON);
		log.info(this.getClass().getSimpleName()+ "======api request end=====POST");
		return representation;
	}
	
	/**
	 * 处理get请求的方法
	 * @param json
	 * @return json将返回到客户端
	 */
	protected abstract JSONObject getMethod(JSONObject json);
	
	/**
	 * 处理post请求的方法
	 * @param json
	 * @return json将返回到客户端
	 */
	protected abstract JSONObject postMethod(JSONObject json);
	

	public String getParameter(String paramName , String defaultValue){
		return  GetObjectUtils.toString(paramsMap.get(paramName), defaultValue);
	}
	public int getParameter(String paramName , int defaultValue){
		return GetObjectUtils.toInt(paramsMap.get(paramName), defaultValue);
	}
	public double getParameter(String paramName , double defaultValue){
		return  GetObjectUtils.toDouble(paramsMap.get(paramName), defaultValue);
	}
	
	
	public JSONObject failRequest(String msg , JSONObject json){
		json.put("msg",msg);
		json.put("result", RESULT_FAIL);
		return json;
	}
	
	public JSONObject succRequest(String msg , JSONObject json){
		json.put("msg",msg);
		json.put("result", RESULT_SUCC);
		return json;
	}
}
