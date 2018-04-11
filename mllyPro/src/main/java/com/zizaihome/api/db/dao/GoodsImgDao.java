package com.zizaihome.api.db.dao;

import java.util.List;

import com.zizaihome.api.db.model.GoodsImgModel;
import com.zizaihome.api.db.model.GoodsModel;

public class GoodsImgDao extends BaseDao<GoodsImgModel>{
	private static GoodsImgDao instance = new GoodsImgDao("goods_img", "id","status");
    
    public static GoodsImgDao getInstance() {
        return instance;
    }
	
	public GoodsImgDao(String tableName, String tableKeyId,
			String statusColumn) {
		super(tableName, tableKeyId, statusColumn);
	}

	public List<GoodsImgModel> getGoodsImg(int goodsId){
		String sql = "SELECT * FROM `" + tableName + "` WHERE status=0  and goodsId=?";
	    Object[] paramObjs ={goodsId};
	    List<GoodsImgModel> modelList = this.queryModelList(sql, paramObjs);
	    return modelList;	
	}
}
