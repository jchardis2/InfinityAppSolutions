package com.infinityappsolutions.server.lib.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.dao.DAOFactory;
import com.infinityappsolutions.server.lib.dao.mysql.UserDAO;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;

public class IASRootLoginAction {

	/**
	 * in case you are not using jaasloginservice
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password) {
		return false;
	}

	/**
	 * 
	 */
	public boolean logout() {
		return false;
	}

	/**
	 * 
	 * @param username
	 *            the username to be checked
	 * @param password
	 *            the password to be checked
	 * @param request
	 *            the request to be used to login
	 * @throws ServletException
	 *             - if the configured login mechanism does not support username
	 *             password authentication, or if a non-null caller identity had
	 *             already been established (prior to the call to login), or if
	 *             validation of the provided username and password fails
	 * @throws DBException
	 */
	public void login(String username, String password,
			HttpServletRequest request) throws ServletException, DBException {
		request.login(username, password);
		UserDAO userDAO = new UserDAO(DAOFactory.getProductionInstance());
		LoggedInUserBean loggedInUserBean = IASRootFacesProvider.getInstance()
				.getLoggedInUserBean();
		userDAO.getUserByCredentials(username, password, loggedInUserBean);
	}

	/**
	 * 
	 * @param username
	 *            the username to be checked
	 * @param password
	 *            the password to be checked
	 * @param request
	 *            the request to be used to login
	 * @throws ServletException
	 *             - if the configured login mechanism does not support username
	 *             password authentication, or if a non-null caller identity had
	 *             already been established (prior to the call to login), or if
	 *             validation of the provided username and password fails
	 * @throws DBException
	 */
	public void login(String username, String password,
			HttpServletRequest request, LoggedInUserBean loggedInUserBean)
			throws ServletException, DBException {
		request.login(username, password);
		UserDAO userDAO = new UserDAO(DAOFactory.getProductionInstance());
		userDAO.getUserByCredentials(username, password, loggedInUserBean);
	}

	public void logout(HttpServletRequest request) throws ServletException {
		request.logout();
	}

}
