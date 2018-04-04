package com.zizaihome.api.db.dao;

import java.util.List;

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
	
	public UserModel getByCardId(int cardId){
		  String sql = "SELECT * FROM `" + tableName + "` WHERE cardId = ? and type=1 and status = 0";
	        Object[] paramObjs = {cardId};
	        List<UserModel> modelList = this.queryModelList(sql, paramObjs);
	        if(modelList.size() > 0){
	        	return modelList.get(0);
	        }
	        return null;
	}
	
	
}
