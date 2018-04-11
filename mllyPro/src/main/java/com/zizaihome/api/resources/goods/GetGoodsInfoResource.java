package com.zizaihome.api.resources.goods;

import java.util.List;

import org.restlet.Request;

import com.ibm.icu.text.SimpleDateFormat;
import com.zizaihome.api.db.model.GoodsImgModel;
import com.zizaihome.api.db.model.GoodsModel;
import com.zizaihome.api.resources.base.BaseResource;
import com.zizaihome.api.service.GoodsImgService;
import com.zizaihome.api.service.GoodsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetGoodsInfoResource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		int goodsId=getParameter("goodsId",0);
		GoodsImgService imgService=new GoodsImgService();
		List<GoodsImgModel> imgModels=imgService.getGoodsImg(goodsId);
		JSONArray jsonArray=new JSONArray();
		for (GoodsImgModel goodsImgModel : imgModels) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("img", goodsImgModel.getImg());
			jsonArray.add(jsonObject);
		}
		
		GoodsService goodsService=new GoodsService();
		GoodsModel goodsModel=goodsService.getModel(goodsId);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("goodsId",goodsModel.getId());
		jsonObject.put("name",goodsModel.getName());
		jsonObject.put("userId",goodsModel.getUserId());
		jsonObject.put("indexImg",goodsModel.getIndexImg());
		jsonObject.put("addTime",dateFormat.format(goodsModel.getAddTime()));
		jsonObject.put("details",goodsModel.getDetails());
		jsonObject.put("type",goodsModel.getType());
		jsonObject.put("story",goodsModel.getStory());
		jsonObject.put("imgList",jsonArray);
		json.put("data", jsonObject);
		return succRequest("成功", json);		
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
