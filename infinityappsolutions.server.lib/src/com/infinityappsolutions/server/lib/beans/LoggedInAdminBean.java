package com.infinityappsolutions.server.lib.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * This bean provides the instance of the currently logged in user
 * 
 * @author Jimmy Hardison
 */
@SessionScoped
@ManagedBean(name = "loggedInAdminBean")
public class LoggedInAdminBean extends AdminBean {
	private static final long serialVersionUID = -4911657762523707563L;

	/**
	 * This bean provides the instance of the currently logged in user
	 */
	public LoggedInAdminBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * This bean provides the instance of the currently logged in user
	 */
	public LoggedInAdminBean(Long adminId, Long id, String username,
			String email, String password, String firstname, String lastname) {
		super(adminId, id, username, email, password, firstname, lastname);
		// TODO Auto-generated constructor stub
	}

}
