package com.zizaihome.api.db.dao;

import java.util.List;

import com.zizaihome.api.db.model.GoodsApplyModel;
import com.zizaihome.api.db.model.UserModel;

public class GoodsApplyDao extends BaseDao<GoodsApplyModel>{
	private static GoodsApplyDao instance = new GoodsApplyDao("goods_apply", "id","status");
    
    public static GoodsApplyDao getInstance() {
        return instance;
    }
	
	public GoodsApplyDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}
	

	public GoodsApplyModel getFromGoodsId(int goodsId){
		String sql = "SELECT * FROM `" + tableName + "` WHERE goodsId = ?  and status = 0";
        Object[] paramObjs = {goodsId};
        List<GoodsApplyModel> modelList = this.queryModelList(sql, paramObjs);
        if(modelList.size() > 0){
        	return modelList.get(0);
        }
        return null;
	}
}
