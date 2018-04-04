package com.zizaihome.api.resources.user;

import org.restlet.Request;

import com.zizaihome.api.db.model.UserModel;
import com.zizaihome.api.resources.base.BaseResource;
import com.zizaihome.api.service.UserService;

import net.sf.json.JSONObject;

public class LoginResource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		int cardId=getParameter("cardId", 0);
		String password=getParameter("password","");
		UserService userService=new UserService();
		UserModel userModel=userService.getByCardId(cardId);
		if (userModel==null) {
			return succRequest("未找到用户", json);
		}
		if (userModel.getPassWord().equals(password)) {
			return succRequest("成功", json);
		}else {
			return succRequest("密码错误", json);
		}
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
