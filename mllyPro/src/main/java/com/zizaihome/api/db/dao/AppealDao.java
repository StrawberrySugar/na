package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.AppealModel;

public class AppealDao extends BaseDao<AppealModel>{
	private static AppealDao instance = new AppealDao("appeal", "id","status");
    
    public static AppealDao getInstance() {
        return instance;
    }
	
	public AppealDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
