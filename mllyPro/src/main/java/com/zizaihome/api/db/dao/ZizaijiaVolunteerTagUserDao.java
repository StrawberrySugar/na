package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.ZizaijiaVolunteerTagUserModel;

public class ZizaijiaVolunteerTagUserDao extends BaseDao<ZizaijiaVolunteerTagUserModel> {

	private static ZizaijiaVolunteerTagUserDao instance = new ZizaijiaVolunteerTagUserDao("zizaijia_volunteer_tag_user", "id","");
    
    public static ZizaijiaVolunteerTagUserDao getInstance() {
        return instance;
    }
	
	public ZizaijiaVolunteerTagUserDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}

}
