package com.zizaihome.api.resources.user;

import java.util.Date;

import org.restlet.Request;

import com.zizaihome.api.db.model.UserModel;
import com.zizaihome.api.resources.base.BaseResource;
import com.zizaihome.api.service.UserService;

import net.sf.json.JSONObject;

public class RegisterResource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		String name=getParameter("name","");
		String password=getParameter("password","");
		String headImg=getParameter("headImg","");
		int schCardNum=getParameter("schCardNum",0);
		String userName=getParameter("userName","");
		String className=getParameter("className","");
		String details=getParameter("details","");
		String major=getParameter("major","");
		String phone=getParameter("phone","");
		String cardImg=getParameter("cardImg","");
		int floorNum=getParameter("floorNum",0);
		UserService userService=new UserService();
		UserModel userModel=userService.getByCardId(schCardNum);
		if ("".equals(name)||"".equals(password)||schCardNum==0||"".equals(userName)||"".equals(className)
				||"".equals(major)||floorNum==0||"".equals(details)) {
			return succRequest("参数不足", json);
		}
		if (userModel!=null) {
			return succRequest("已存在用户", json);
		}
		userModel=new UserModel();
		userModel.setAddTime(new Date());
		userModel.setMajor(major);
		userModel.setCardImg(cardImg);
		userModel.setSchCardNum(schCardNum);
		userModel.setClassName(className);
		userModel.setDetails(details);
		userModel.setName(name);
		userModel.setHeadImg(headImg);
		userModel.setFloorNum(floorNum);
		userModel.setPhone(phone);
		userModel.setType(-1);
		userService.insert(userModel);
		return succRequest("注册成功，等待审核", json);
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
