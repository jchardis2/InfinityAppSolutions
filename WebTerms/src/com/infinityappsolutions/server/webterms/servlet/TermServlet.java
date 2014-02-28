package com.infinityappsolutions.server.webterms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TermServlet extends HttpServlet {
	private static final long serialVersionUID = 1458278335635638361L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>Helflo Servlet</h1>");
		response.getWriter().println(
				"session=" + request.getSession(true).getId());
	}
}
 