package com.infinityappsolutions.server.views;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.infinityappsolutions.server.actions.ui.GenerateOrgUserBeanAction;
import com.infinityappsolutions.server.beans.OrgUserBean;
import com.infinityappsolutions.server.exceptions.DBException;
import com.infinityappsolutions.server.exceptions.WebDesignerException;
import com.infinityappsolutions.server.faces.FacesProvider;

@ViewScoped
@ManagedBean(name = "generateOrgUserBeanView")
public class GenerateOrgUserBeanView implements Serializable {
	private static final long serialVersionUID = 1888897305093017519L;
	private ArrayList<OrgUserBean> orgUsersBeansList;

	public GenerateOrgUserBeanView() {
		generateOrgUsersBean();
	}

	public ArrayList<OrgUserBean> generateOrgUsersBean() {
		GenerateOrgUserBeanAction action = new GenerateOrgUserBeanAction();
		try {
			orgUsersBeansList = action.generateOrgUsersBean(FacesProvider
					.getInstance().getLoggedInUserBean().getId());
			return orgUsersBeansList;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebDesignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<OrgUserBean> getOrgUsersBeansList() {
		return orgUsersBeansList;
	}

	public void setOrgUsersBeansList(ArrayList<OrgUserBean> orgUsersBeansList) {
		this.orgUsersBeansList = orgUsersBeansList;
	}

}
