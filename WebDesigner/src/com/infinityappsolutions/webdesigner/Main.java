package com.infinityappsolutions.webdesigner;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.jetty.jaas.JAASLoginService;
import org.eclipse.jetty.jaas.callback.DefaultCallbackHandler;
import org.eclipse.jetty.jaas.callback.ObjectCallback;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.webapp.WebAppContext;

import com.infinityappsolutions.webdesigner.log.Logger;
import com.infinityappsolutions.webdesigner.login.WebDesignerLoginAuthenticator;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setProperty("java.security.auth.login.config", "/home/jchardis/git/Webclipse/WebDesignerServer/etc/login.conf");

		Server server = new Server(8080);
		// MBeanContainer mbContainer = new MBeanContainer(
		// ManagementFactory.getPlatformMBeanServer());
		// server.addBean(mbContainer);

		// Realm
		// HashLoginService hashLoginService = new HashLoginService("Realm",
		// "etc/realm.properties");

		JDBCLoginService jdbcLoginService = new JDBCLoginService("JDBCRealm", "etc/jdbcRealm.properties");

		JAASLoginService jaasLoginService = new JAASLoginService("JAASRealm");
		jaasLoginService.setLoginModuleName("jdbc");
		jaasLoginService.setIdentityService(jdbcLoginService.getIdentityService());
		// jaasLoginService.setCallbackHandlerClass(DefaultCallbackHandler.class.getName());

		Constraint constraintUsers = new Constraint();
		constraintUsers.setName("user");
		constraintUsers.setAuthenticate(true);
		constraintUsers.setRoles(new String[] { "user", "admin" });

		ConstraintMapping mappingUsers = new ConstraintMapping();
		mappingUsers.setPathSpec("/user/*");
		mappingUsers.setConstraint(constraintUsers);

		Constraint constraintAdmins = new Constraint();
		constraintAdmins.setName("admin");
		constraintAdmins.setAuthenticate(true);
		constraintAdmins.setRoles(new String[] { "admin" });

		ConstraintMapping mappingAdmins = new ConstraintMapping();
		mappingAdmins.setPathSpec("/admins/*");
		mappingAdmins.setConstraint(constraintAdmins);

		// users not logged in
		Constraint constraintNoUser = new Constraint();
		constraintNoUser.setName("nouser");
		constraintNoUser.setAuthenticate(true);
		constraintNoUser.setRoles(new String[] {});

		ConstraintMapping mappingNoUser = new ConstraintMapping();
		mappingNoUser.setPathSpec("/login.xhtml");
		mappingNoUser.setConstraint(constraintAdmins);

		ArrayList<ConstraintMapping> constraintMappingsList = new ArrayList<ConstraintMapping>();
		constraintMappingsList.add(mappingUsers);
		constraintMappingsList.add(mappingAdmins);
		constraintMappingsList.add(mappingNoUser);

		ConstraintSecurityHandler security = new ConstraintSecurityHandler();
		security.setConstraintMappings(Collections.unmodifiableList(constraintMappingsList));
		WebDesignerLoginAuthenticator authenticator = new WebDesignerLoginAuthenticator("/login.xhtml", "/login-error.xhtml", false);
		// FormAuthenticator authenticator = new
		// FormAuthenticator("/login.xhtml", "/login-error.xhtml", false);
		authenticator.setAlwaysSaveUri(true);
		// WebDesignerLoginAuthenticator authenticator = new
		// WebDesignerLoginAuthenticator();
		// security.setAuthenticator(new FormAuthenticator("/login.xhtml",
		// "/login-error.xhtml", false));
		// security.setAuthenticator(new FormAuthenticator());
		security.setAuthenticator(authenticator);
		// security.setAuthenticator(new BasicAuthenticator());
		security.setLoginService(jaasLoginService);
		security.setSessionRenewedOnAuthentication(false);

		// setup webcontext
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar("./WebContent");
		webAppContext.setSecurityHandler(security);

		// add Handlers
		HandlerList handlerList = new HandlerList();
		handlerList.addHandler(webAppContext);
		// handlerList.addHandler(security);
		server.setHandler(handlerList);

		// Set logging to true
		// Log.getRootLogger().setDebugEnabled(true);
		Logger.getInstance().setDebug(true);
		System.setProperty(Logger.PROPERTY_DEBUG, Boolean.TRUE.toString());

		// http://www.eclipse.org/jetty/documentation/current/embedded-examples.html#embedded-secured-hello-handler
		server.start();
		Handler handlers[] = server.getHandlers();
		for (Handler handler : handlers) {
			System.out.println("Handler name: " + handler);
		}
		server.join();
		System.out.println("Server Stopped");

	}
}
