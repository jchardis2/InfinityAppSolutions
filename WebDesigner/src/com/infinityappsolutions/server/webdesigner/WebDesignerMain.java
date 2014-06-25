package com.infinityappsolutions.server.webdesigner;

import org.eclipse.jetty.webapp.WebAppContext;

import com.infinityappsolutions.server.IASServer;

public class WebDesignerMain {

	public static void main(String[] args) throws Exception {

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar("/home/jchardis/git/InfinityAppSolutions-WebApps/WebDesigner");
		IASServer iasServer = new IASServer();
		iasServer.loadSystemProperties();
		System.setProperty("jetty.webapps",
				"/home/jchardis/git/InfinityAppSolutions-WebApps/WebDesigner");
		iasServer.configure();
		iasServer.setWebAppContext(webapp);
		iasServer.setLoginService();
		iasServer.start();
		iasServer.join();
	}
}
