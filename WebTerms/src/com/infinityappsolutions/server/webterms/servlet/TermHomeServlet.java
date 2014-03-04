package com.infinityappsolutions.server.webterms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;

public class TermHomeServlet extends AbstractTermsServlet {
	private static final long serialVersionUID = 1458278335635638361L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoggedInUserBean liub = (LoggedInUserBean) session
				.getAttribute("loggedInUserBean");
		IASGson<LoggedInUserBean> iasGson = new IASGson<>();
		String liubGsonString = iasGson.toGson(liub);
		String requestURI = request.getRequestURI();
		response.setHeader("Location", requestURI);
		response.getWriter().write(liubGsonString);
	}
}
