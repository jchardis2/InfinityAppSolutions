package com.infinityappsolutions.server;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.jetty.jaas.JAASLoginService;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.webapp.WebAppContext;

import com.infinityappsolutions.server.login.WebDesignerLoginAuthenticator;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setProperty("java.security.auth.login.config",
				"/home/jchardis/git/Webclipse/WebDesignerServer/etc/login.conf");

		Server server = new Server(8080);

		MBeanContainer mbContainer = new MBeanContainer(
				ManagementFactory.getPlatformMBeanServer());
		server.addBean(mbContainer);

		JDBCLoginService jdbcLoginService = new JDBCLoginService("JDBCRealm",
				"etc/jdbcRealm.properties");
		JAASLoginService jaasLoginService = new JAASLoginService("JAASRealm");
		jaasLoginService.setLoginModuleName("jdbc");
		jaasLoginService.setIdentityService(jdbcLoginService
				.getIdentityService());
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

		WebDesignerLoginAuthenticator authenticator = new WebDesignerLoginAuthenticator(
				"/login.xhtml", "/login-error.xhtml", false);
		authenticator.setAlwaysSaveUri(true);

		// setup webcontext
		WebAppContext iasWebAppContext = new WebAppContext();
		iasWebAppContext.setContextPath("/");
		iasWebAppContext.setWar("../InfinityAppSolutionsServer/WebContent");
		iasWebAppContext.setSecurityHandler(getSecurityHandler(
				constraintMappingsList, authenticator, jaasLoginService));

		// SetupWebDesignServer context
		WebAppContext wvsWebAppContext = new WebAppContext();
		wvsWebAppContext.setContextPath("/webdesigner");
		wvsWebAppContext
				.setWar("/home/jchardis/git/Webclipse//WebDesignerServer/WebContent");
		wvsWebAppContext.setSecurityHandler(getSecurityHandler(
				constraintMappingsList, authenticator, jaasLoginService));
		// SessionHandler handler = iasWebAppContext.getSessionHandler();
		// wvsWebAppContext.setSessionHandler(handler);

		// add Handlers
		HandlerList handlerList = new HandlerList();
		handlerList.addHandler(wvsWebAppContext);
		handlerList.addHandler(iasWebAppContext);

		server.setHandler(handlerList);
		server.start();
		server.join();
		System.out.println("Server Stopped");

	}

	public static ConstraintSecurityHandler getSecurityHandler(
			ArrayList<ConstraintMapping> constraintMappingsList,
			WebDesignerLoginAuthenticator authenticator,
			JAASLoginService jaasLoginService) {
		ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
		securityHandler.setConstraintMappings(Collections
				.unmodifiableList(constraintMappingsList));
		securityHandler.setAuthenticator(authenticator);
		securityHandler.setLoginService(jaasLoginService);
		securityHandler.setSessionRenewedOnAuthentication(false);
		return securityHandler;
	}
}
