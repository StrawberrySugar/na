package com.zizaihome.api.service;

import java.util.List;

import com.zizaihome.api.db.dao.GoodsDao;
import com.zizaihome.api.db.model.GoodsModel;

public class GoodsService extends BaseService<GoodsModel, GoodsDao>{
	
	public List<GoodsModel> getGoods(){
		return GoodsDao.getInstance().GetGoods();
	}
}
