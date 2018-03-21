package com.zizaihome.api.main;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;

import com.zizaihome.api.utils.ConfigReadUtils;

public class StaticResourceApplication extends Application {
	public static String ROOT_URI = "";  
	public static String TEMPLATE_URI = "";
	
	static{
		ROOT_URI=(ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("root_path"));
		TEMPLATE_URI=(ConfigReadUtils.loadConfigByConfDir("server.properties").getProperty("tml_path"));
	}
	
//	public static final String ROOT_URI = "file:///data/qinqin_api/static/";  
	  /** 
     * Constructor. 
     */  
    public StaticResourceApplication() {  
        // Sets the facultative name of the application.  
        setName("Static Resource Application");
        // the application requires the following client connector.  
//        getConnectorService().getClientProtocols().add(Protocol.CLAP);  
//        getConnectorService().getServerProtocols().add(Protocol.CLAP);  
    }  
    

    
    @Override
	public Restlet createInboundRoot() {
    	return new Directory(getContext(), ROOT_URI+"static");  
	}
    
}  