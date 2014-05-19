package com.infinityappsolutions.server.webterms.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.security.SecureHashUtil;
import com.infinityappsolutions.server.lib.actions.IASRootLoginAction;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;

public class TermLoginServlet extends AbstractTermsServlet {
	private static final long serialVersionUID = 1458278335635638361L;


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String string = (String) paramNames.nextElement();
			System.out.println("Param: " + paramNames);
			System.out.println("Value: " + request.getParameter(string));
		}

		String username = request.getParameter(System
				.getProperty("user.login.username"));
		String password = request.getParameter(System
				.getProperty("user.login.password"));
		if (username != null && password != null) {
			try {
				IASRootLoginAction action = new IASRootLoginAction(
						DAOFactory.getProductionInstance());
				LoggedInUserBean liub = new LoggedInUserBean();
				session.setAttribute("loggedInUserBean", liub);
				SecureHashUtil hashUtil = new SecureHashUtil();
				password = hashUtil.sha256Hash((String) password);
				action.login(username, password, request, liub);
				response.sendRedirect(BASE_DIRECTORY + "/user/mobile/home");
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.getWriter().println("Login Failed");
	}
}
