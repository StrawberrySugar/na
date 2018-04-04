package com.zizaihome.api.service;

import com.zizaihome.api.db.dao.UserDao;
import com.zizaihome.api.db.model.UserModel;

public class UserService extends BaseService<UserModel,UserDao>{
	
	public UserModel getByCardId(int cardId){
		return UserDao.getInstance().getByCardId(cardId);
	}
}
