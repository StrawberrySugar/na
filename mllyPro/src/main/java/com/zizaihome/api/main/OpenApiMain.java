package com.zizaihome.api.main;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.coola.jutil.common.CongfigResource;
import com.zizaihome.api.Version;
import com.zizaihome.api.common.Constants;
import com.zizaihome.api.common.InitConfigFile;
import com.zizaihome.api.utils.LogUtils;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class OpenApiMain {
	public static final String versionPrefix = "/" + Version.getVersion();
	public static final String log4jConfiguration = "log4j.configuration";
	private static final ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	
	

	public static void main(String[] args) {
		try {
			System.setProperty("api.base.dir", System.getProperty("user.dir"));
			LogUtils.log.info(System.getProperty("user.dir"));
			System.setProperty("api.conf.dir", System.getProperty("api.base.dir") + File.separator + "conf");
			LogUtils.log.info(System.getProperty("api.conf.dir"));
			System.setProperty("api.logs.dir", System.getProperty("api.base.dir") + File.separator + "logs");
			LogUtils.log.info(System.getProperty("api.logs.dir"));

			InputStream input = null;
			// 读取log日志配置
			if (System.getProperty(log4jConfiguration) != null) {
				File logConfigFile = new File(System.getProperty(log4jConfiguration));
				if (logConfigFile.exists()) {
					input = new FileInputStream(logConfigFile);
				} else {
					input = CongfigResource.loadConfigFile(log4jConfiguration, OpenApiMain.class);
				}
			} else {
				input = CongfigResource.loadConfigFile(
						System.getProperty("api.conf.dir") + File.separator + "log4j.properties", OpenApiMain.class);
			}
			if (input != null) {
				Properties prop = new Properties();
				prop.load(input);
				PropertyConfigurator.configure(prop);
				SLF4JBridgeHandler.install();
			}
			
			
			InitConfigFile.init();
			Component comp = new Component();
			comp.getServers().add(Protocol.HTTP, Constants.PORT);

			comp.getClients().add(Protocol.FILE);

			Engine.getInstance().registerDefaultConverters();

			//Restlet pinterest = new QinQinClientApplication();

			// api主应入口
			// Restlet apiApp = new OpenApiApplication();

			// 验证签名的过滤器
			// SignatureValidFilter signFilter = new SignatureValidFilter();
			// Resource访问日志
			// AccessLogFilter accessLogFilter = new AccessLogFilter();
			// 设置过滤器链
			// signFilter.setNext(accessLogFilter);

			// accessLogFilter.setNext(apiApp);

			// Resource访问日志
//			AccessLogFilter accessLogFilter = new AccessLogFilter();
//			accessLogFilter.setNext(pinterest);
//			comp.getDefaultHost().attach("/zizaihome", accessLogFilter);
			
	
			// AccessLogFilter accessLogFilter2 = new AccessLogFilter();
			StaticResourceApplication arApp = new StaticResourceApplication();
			// accessLogFilter2.setNext(arApp);
			// comp.getDefaultHost().attach("/static", accessLogFilter2);
			comp.getDefaultHost().attach("/static", arApp);
//			comp.getDefaultHost().attach("/", arApp);
			// comp.getClients().add(Protocol.ALL);
			// comp.getDefaultHost().attach(versionPrefix, signFilter);

		
//			UsersApplication usersApplication=new UsersApplication();
//			comp.getDefaultHost().attach("/common",usersApplication);
//			
			
			// 设置jetty
			comp.getContext().getParameters().add("type", "1");// NIO
			comp.getContext().getParameters().add("lowResourcesMaxIdleTimeMs", "1000");
			comp.getContext().getParameters().add("acceptQueueSize", "50");
			comp.getContext().getParameters().add("maxTotalConnections", "100");
			comp.getContext().getParameters().add("minThreads", "250");
			comp.getContext().getParameters().add("maxThreads", "250");
			comp.getContext().getParameters().add("threadPool", "250");
			comp.getContext().getParameters().add("acceptorThreads", "50");
			comp.getContext().getParameters().add("useForwardedForHeader", "true");
			comp.getContext().getParameters().add("threadMaxIdleTimeMs", "3000");
			comp.start();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
