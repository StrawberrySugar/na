package com.zizaihome.api.db.dao;

import java.util.List;

import com.zizaihome.api.db.model.GoodsModel;

public class GoodsDao extends BaseDao<GoodsModel>{
	private static GoodsDao instance = new GoodsDao("goods", "id","status");
    
    public static GoodsDao getInstance() {
        return instance;
    }
	
	public GoodsDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
	
	public List<GoodsModel> GetGoods(){
		String sql = "SELECT * FROM `" + tableName + "` WHERE status=0 ";
	    Object[] paramObjs ={};
	    List<GoodsModel> modelList = this.queryModelList(sql, paramObjs);
	    return modelList;	
	}
}
