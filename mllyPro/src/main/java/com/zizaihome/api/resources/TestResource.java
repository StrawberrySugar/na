package com.zizaihome.api.resources;



import net.sf.json.JSONObject;

import org.restlet.Request;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import com.zizaihome.api.resources.base.BaseResource;

public class TestResource extends BaseResource {


	
	protected Representation representation; // 错误信息，null表示没有错误
	
	
	@Override
	protected void initParams(Request request) {
		// TODO Auto-generated method stub

	}
	
	@Get
	public Representation get() throws ResourceException
	{
		
     
        JSONObject json = new JSONObject();
        json.put("userId",10038);
       
        representation = new StringRepresentation(json.toString(),MediaType.APPLICATION_JSON);
		return representation;
	}

	@Override
	protected JSONObject getMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSONObject postMethod(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
