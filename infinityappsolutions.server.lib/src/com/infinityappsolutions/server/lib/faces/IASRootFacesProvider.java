package com.infinityappsolutions.server.lib.faces;

import javax.faces.context.FacesContext;

import com.infinityappsolutions.server.lib.beans.LoggedInAdminBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUsersOrgContainerBean;

/**
 * A Singleton class for returning the different beans for production. This also
 * allows us to test jsf in some instances without running the server.
 * 
 * @author Jimmy Hardison
 * 
 */
public class IASRootFacesProvider {
	/**
	 * Static instance for each user
	 */
	private static IASRootFacesProvider fp;

	/**
	 * A Singleton class for returning the different beans for production
	 */
	protected IASRootFacesProvider() {

	}

	/**
	 * 
	 * @return An instance of IASRootFacesProvider
	 */
	public static IASRootFacesProvider getInstance() {
		if (fp == null) {
			fp = new IASRootFacesProvider();
		}
		return fp;
	}

	/**
	 * 
	 * @return The logged in Admin Bean
	 */
	public LoggedInAdminBean getLoggedInAdminBean() {
		LoggedInAdminBean liab = (LoggedInAdminBean) FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						"#{loggedInAdminBean}", LoggedInAdminBean.class)
				.getValue(FacesContext.getCurrentInstance().getELContext());
		if (liab == null) {
			liab = new LoggedInAdminBean();
			setLoggedInAdminBean(liab);
		}
		return liab;
	}

	/**
	 * 
	 * @return The logged in User Bean
	 */
	public LoggedInUserBean getLoggedInUserBean() {
		LoggedInUserBean liub = (LoggedInUserBean) FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						"#{loggedInUserBean}", LoggedInUserBean.class)
				.getValue(FacesContext.getCurrentInstance().getELContext());
		if (liub == null) {
			liub = new LoggedInUserBean();
			setLoggedInUserBean(liub);
		}
		return liub;
	}

	/**
	 * 
	 * @return The OrgUserBean
	 */
	public LoggedInUsersOrgContainerBean getLoggedInUsersOrgContainerBean() {
		LoggedInUsersOrgContainerBean liuocb = (LoggedInUsersOrgContainerBean) FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						"#{loggedInUsersOrgContainerBean}",
						LoggedInUsersOrgContainerBean.class)
				.getValue(FacesContext.getCurrentInstance().getELContext());
		if (liuocb == null) {
			liuocb = new LoggedInUsersOrgContainerBean();
			setLoggedInUsersOrgContainerBean(liuocb);
		}
		return liuocb;
	}

	public Object setLoggedInUserBean(LoggedInUserBean liub) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("loggedInUserBean", liub);
	}

	public Object setLoggedInAdminBean(LoggedInAdminBean liab) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("loggedInAdminBean", liab);
	}

	public Object setLoggedInUsersOrgContainerBean(
			LoggedInUsersOrgContainerBean liuocb) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("loggedInUsersOrgContainerBean", liuocb);
	}

}
