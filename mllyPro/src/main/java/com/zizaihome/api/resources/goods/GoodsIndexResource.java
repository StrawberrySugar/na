package com.zizaihome.api.resources.goods;

import java.util.List;

import org.restlet.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zizaihome.api.db.model.GoodsModel;
import com.zizaihome.api.resources.base.BaseResource;
import com.zizaihome.api.service.GoodsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoodsIndexResource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		GoodsService goodsService =new GoodsService();
		List<GoodsModel> goodsModels=goodsService.getGoods();
		Gson gson=new Gson();
		JSONArray array=JSONArray.fromObject(gson.toJson(goodsModels,new TypeToken<List<GoodsModel>>(){}.getType()));
		json.put("data",array);
		return succRequest("成功", json);
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
