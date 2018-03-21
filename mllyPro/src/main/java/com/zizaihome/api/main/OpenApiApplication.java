package com.zizaihome.api.main;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.zizaihome.api.resources.TestResource;


public class OpenApiApplication extends Application {
    public static final String KEY = "org.restlet.application";

    public OpenApiApplication() {
    }

    public OpenApiApplication(Context con) {
    	super(con);
    }


   
    @Override
    public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		share(router);
		return router;
    }

   
    private void share(Router router) {
    	router.attach("/test",TestResource.class);
    }
    
   
}
