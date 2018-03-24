package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.UserApplyModel;

public class UserApplyDao extends BaseDao<UserApplyModel>{
	private static UserApplyDao instance = new UserApplyDao("user_apply", "id","status");
    
    public static UserApplyDao getInstance() {
        return instance;
    }
	
	public UserApplyDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
