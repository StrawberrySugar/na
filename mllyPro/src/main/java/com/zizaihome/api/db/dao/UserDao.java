package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.UserModel;

public class UserDao extends BaseDao<UserModel>{
	private static UserDao instance = new UserDao("user", "id","status");
    
    public static UserDao getInstance() {
        return instance;
    }
	
	public UserDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
