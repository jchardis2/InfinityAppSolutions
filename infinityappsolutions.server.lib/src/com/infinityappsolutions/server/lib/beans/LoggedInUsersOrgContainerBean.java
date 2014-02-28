package com.infinityappsolutions.server.lib.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "loggedInUsersOrgContainerBean")
public class LoggedInUsersOrgContainerBean implements Serializable {
	private static final long serialVersionUID = 527748713727226533L;
	private ArrayList<OrgUserBean> orgUsersBeansList;
	private OrgUserBean currentOrgUsersBean;

	public LoggedInUsersOrgContainerBean() {

	}

	public ArrayList<OrgUserBean> getOrgUsersBeansList() {
		return orgUsersBeansList;
	}

	public void setOrgUsersBeansList(ArrayList<OrgUserBean> orgUsersBeansList) {
		this.orgUsersBeansList = orgUsersBeansList;
	}

	public OrgUserBean getCurrentOrgUsersBean() {
		return currentOrgUsersBean;
	}

	public void setCurrentOrgUsersBean(OrgUserBean currentOrgUsersBean) {
		this.currentOrgUsersBean = currentOrgUsersBean;
	}

}
