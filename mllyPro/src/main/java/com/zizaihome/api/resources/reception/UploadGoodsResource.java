package com.zizaihome.api.resources.reception;

import org.restlet.Request;

import com.zizaihome.api.resources.base.BaseResource;

import net.sf.json.JSONObject;

public class UploadGoodsResource extends BaseResource{

	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		int cardId=getParameter("cardId",0);
		String indexImg=getParameter("indexImg", "");
		String worth=getParameter("worth", "");
		String details=getParameter("details","");
		String story=getParameter("story", "");
		return null;
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
