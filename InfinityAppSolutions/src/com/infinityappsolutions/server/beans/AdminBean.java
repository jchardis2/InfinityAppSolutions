package com.infinityappsolutions.server.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "adminBean")
public class AdminBean extends UserBean {

	private static final long serialVersionUID = -4157533455778109996L;
	private Long adminId;

	public AdminBean() {
	}

	public AdminBean(Long adminId, Long id, String username, String email,
			String password, String firstname, String lastname) {
		super(id, username, email, password, firstname, lastname);
		this.adminId = adminId;
	}

}