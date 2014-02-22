package com.infinityappsolutions.lib.server.faces;

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
public class FacesProvider {
	/**
	 * Static instance for each user
	 */
	private static FacesProvider fp;

	/**
	 * A Singleton class for returning the different beans for production
	 */
	protected FacesProvider() {

	}

	/**
	 * 
	 * @return An instance of FacesProvider
	 */
	public static FacesProvider getInstance() {
		if (fp == null) {
			fp = new FacesProvider();
		}
		return fp;
	}

	/**
	 * 
	 * @return The logged in Admin Bean
	 */
	public LoggedInAdminBean getLoggedInAdminBean() {
		return (LoggedInAdminBean) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
				.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{loggedInAdminBean}", LoggedInAdminBean.class).getValue(FacesContext.getCurrentInstance().getELContext());
	}

	/**
	 * 
	 * @return The logged in User Bean
	 */
	public LoggedInUserBean getLoggedInUserBean() {
		return (LoggedInUserBean) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
				.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{loggedInUserBean}", LoggedInUserBean.class).getValue(FacesContext.getCurrentInstance().getELContext());
	}

	/**
	 * 
	 * @return The OrgUserBean
	 */
	public LoggedInUsersOrgContainerBean getLoggedInUsersOrgContainerBean() {
		return (LoggedInUsersOrgContainerBean) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
				.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{loggedInUsersOrgContainerBean}", LoggedInUsersOrgContainerBean.class)
				.getValue(FacesContext.getCurrentInstance().getELContext());
	}

}
