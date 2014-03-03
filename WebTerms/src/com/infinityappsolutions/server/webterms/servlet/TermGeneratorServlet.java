package com.infinityappsolutions.server.webterms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.lib.webterms.bean.Term;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.webterms.actions.GenerateTermsAction;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;

public class TermGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1458278335635638361L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute(System
				.getProperty("user.username"));
		Long userID = (Long) session
				.getAttribute(System.getProperty("user.id"));

		LoggedInUserBean liub = (LoggedInUserBean) session
				.getAttribute("loggedInUserBean");
		GenerateTermsAction action = new GenerateTermsAction(
				DAOFactory.getProductionInstance());
		try {
			ArrayList<Term> termList = action.generateTermList(liub); 
			IASGson<ArrayList<Term>> iasGson = new IASGson<>();
			String jsonTermList = iasGson.toGson(termList);
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(jsonTermList);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
