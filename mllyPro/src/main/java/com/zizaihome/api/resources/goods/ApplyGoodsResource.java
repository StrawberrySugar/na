package com.zizaihome.api.resources.goods;

import org.restlet.Request;

import com.zizaihome.api.db.model.GoodsApplyModel;
import com.zizaihome.api.db.model.GoodsModel;
import com.zizaihome.api.resources.base.BaseResource;
import com.zizaihome.api.service.GoodsApplyService;
import com.zizaihome.api.service.GoodsService;

import net.sf.json.JSONObject;

public class ApplyGoodsResource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		int goodsId=getParameter("goodsId",0);
		int type=getParameter("type",0);
		String returnDetails=getParameter("returnDetails","");
		GoodsApplyService applyService=new GoodsApplyService();
		GoodsApplyModel applyModel=applyService.getFromGoodsId(goodsId);
		applyModel.setType(type);
		applyModel.setDetails(returnDetails);
		applyService.updateAny(applyModel);
		if (type==1) {
			GoodsService goodsService=new GoodsService();
			GoodsModel goodsModel=goodsService.getModel(goodsId);
			goodsModel.setType(-1);
			goodsService.updateAny(goodsModel);
		}
		return succRequest("成功", json);
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
