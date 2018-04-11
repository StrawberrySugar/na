package com.zizaihome.api.resources.goods;

import java.util.Date;

import org.restlet.Request;

import com.zizaihome.api.db.model.AppealModel;
import com.zizaihome.api.db.model.GoodsApplyModel;
import com.zizaihome.api.db.model.GoodsModel;
import com.zizaihome.api.resources.base.BaseResource;
import com.zizaihome.api.service.GoodsApplyService;
import com.zizaihome.api.service.GoodsService;

import net.sf.json.JSONObject;

public class UploadGoodsresource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		int userId=0;
		String goodsName=getParameter("goodsName","");
		String details=getParameter("details","");
		String indexImg=getParameter("indexImg","");
		int worth=getParameter("worth", 0);
		String story=getParameter("story", "");
		String applyDetails=getParameter("applyDetails","");
		GoodsModel goodsModel=new GoodsModel();
		GoodsApplyModel applyModel=new GoodsApplyModel();
		GoodsApplyService applyService=new GoodsApplyService();
		GoodsService goodsService=new GoodsService();
		goodsModel.setAddTime(new Date());
		goodsModel.setDetails(details);
		goodsModel.setIndexImg(indexImg);
		goodsModel.setName(goodsName);
		goodsModel.setStory(story);
		goodsModel.setType(0);
		goodsModel.setWorth(worth);
		goodsModel.setUserId(userId);
		int goodsId=goodsService.insert(goodsModel);
		applyModel.setAddTime(new Date());
		applyModel.setDetails(applyDetails);
		applyModel.setType(0);
		applyModel.setUserId(userId);
		applyModel.setGoodsId(goodsId);
		applyService.insert(applyModel);
		return succRequest("成功", json);
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
