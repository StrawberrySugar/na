package com.zizaihome.api.db.dao;

import com.zizaihome.api.db.model.GoodsImgModel;

public class GoodsImgDao extends BaseDao<GoodsImgModel>{
	private static GoodsImgDao instance = new GoodsImgDao("goods_img", "id","status");
    
    public static GoodsImgDao getInstance() {
        return instance;
    }
	
	public GoodsImgDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
}
