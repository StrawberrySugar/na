package com.zizaihome.api.service;

import java.util.List;

import com.zizaihome.api.db.dao.GoodsImgDao;
import com.zizaihome.api.db.model.GoodsImgModel;

public class GoodsImgService extends BaseService<GoodsImgModel, GoodsImgDao>{
	
	public List<GoodsImgModel> getGoodsImg(int goodsId){
		return GoodsImgDao.getInstance().getGoodsImg(goodsId);
	}
}
