package com.zizaihome.api.main;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.zizaihome.api.resources.users.*;


public class UsersApplication extends  Application{
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		this.oauth(router);
		return router;
	}
	
	private void oauth(Router router) {
		//获取禅在用户
		router.attach("/chanzaiUserList",ChanzaiUserListResource.class);
		//编辑用户
		router.attach("/editUser",EditUserResource.class);
		//获得用户信息
		router.attach("/getUser",GetUserResource.class);
		//合并用户
		router.attach("/mergeUser",MergeUserResource.class);
		//注册
		router.attach("/registerUser",RegisterUserResource.class);
		//更新redis
		router.attach("/updateCache",UpdateCacheResource.class);
		//更新用户状态
		router.attach("/updateLogin",UpdateLoginUserResource.class);
		
	}
}
