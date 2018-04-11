package com.zizaihome.api.service;

import com.zizaihome.api.db.dao.GoodsApplyDao;
import com.zizaihome.api.db.model.GoodsApplyModel;

public class GoodsApplyService extends BaseService<GoodsApplyModel,GoodsApplyDao>{
	
	public GoodsApplyModel getFromGoodsId(int goodsId){
		return GoodsApplyDao.getInstance().getFromGoodsId(goodsId);
	}
	}
