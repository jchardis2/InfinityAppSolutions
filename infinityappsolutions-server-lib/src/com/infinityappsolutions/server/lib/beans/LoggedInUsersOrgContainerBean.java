package com.infinityappsolutions.server.lib.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "loggedInUsersOrgContainerBean")
public class LoggedInUsersOrgContainerBean {
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
