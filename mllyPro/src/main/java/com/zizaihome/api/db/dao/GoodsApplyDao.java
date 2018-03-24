package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.GoodsApplyModel;

public class GoodsApplyDao extends BaseDao<GoodsApplyModel>{
	private static GoodsApplyDao instance = new GoodsApplyDao("goods_apply", "id","status");
    
    public static GoodsApplyDao getInstance() {
        return instance;
    }
	
	public GoodsApplyDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
