package com.zizaihome.api.main;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class SyncBindApplication extends Application {
    public SyncBindApplication() {
    }

    public Restlet createInboundRoot() {
	Router router = new Router(getContext());
	this.oauth(router);
	return router;
    }

    /**
     * OAuth验证
     * 
     * @param router
     */
    private void oauth(Router router) {
	// 用户登录授权
	/* router.attach("/oauth/syncBind", SyncBindResource.class);
	 router.attach("/oauth/syncBindOA", OASyncBindResource.class);
	 router.attach("/oauth/syncBindOAStatus", OASyncBindStatusResource.class);
	 router.attach("/oauth/delBindStatus", DelBindStatusResource.class);
	 router.attach("/oauth/follow", FollowResource.class);*/
    }
}
