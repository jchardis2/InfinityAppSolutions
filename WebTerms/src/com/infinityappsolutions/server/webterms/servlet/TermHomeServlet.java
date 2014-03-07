package com.infinityappsolutions.server.webterms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.lib.webterms.bean.Term;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;
import com.infinityappsolutions.server.webterms.dao.mysql.TermsDAO;

public class TermHomeServlet extends AbstractTermsServlet {
	private static final long serialVersionUID = 1458278335635638361L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoggedInUserBean liub = (LoggedInUserBean) session
				.getAttribute("loggedInUserBean");

		TermsDAO termsDAO = new TermsDAO(DAOFactory.getProductionInstance());
		ArrayList<Term> terms = new ArrayList<Term>();
		try {
			terms = termsDAO.getTermsByOwner(liub.getId());
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DataBean<Term> dataBean = new DataBean<Term>();
		dataBean.setUserBean(liub);
		dataBean.setTerms(terms);
		IASGson<DataBean<Term>> iasGson = new IASGson<DataBean<Term>>();
		String dataBeanGsonString = iasGson.toGson(dataBean);
		String requestURI = request.getRequestURI();
		response.setHeader("Location", requestURI);
		response.getWriter().write(dataBeanGsonString);
	}
}
