package com.zizaihome.api.filter;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.routing.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter extends Filter {
    private volatile long startAccessTime;
    private static Logger LOG = LoggerFactory.getLogger("access");

    protected int beforeHandle(Request request, Response response) {
	startAccessTime = System.currentTimeMillis();
	Object obj = request.getAttributes().get(
			"org.restlet.http.headers");
	Form headers = new Form(obj.toString());
	String access_token = headers.getFirstValue("access_token");
	LOG.debug(" access_token=" + access_token + " : startAccessTime="
		+ startAccessTime);
	return CONTINUE;
    }

    protected void afterHandle(Request request, Response response) {
    	Object obj = request.getAttributes().get(
    			"org.restlet.http.headers");
    	Form headers = new Form(obj.toString());
	String access_token = headers.getFirstValue("access_token");
	LOG.debug(" access_token=" + access_token + " : "
		+ (System.currentTimeMillis() - startAccessTime) + " ms");
    }
}
