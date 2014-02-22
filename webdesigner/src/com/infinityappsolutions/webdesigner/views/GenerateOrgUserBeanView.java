package com.infinityappsolutions.webdesigner.views;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.infinityappsolutions.webdesigner.actions.ui.GenerateOrgUserBeanAction;
import com.infinityappsolutions.webdesigner.beans.OrgUserBean;
import com.infinityappsolutions.webdesigner.exceptions.DBException;
import com.infinityappsolutions.webdesigner.exceptions.WebDesignerException;
import com.infinityappsolutions.webdesigner.faces.FacesProvider;

@ViewScoped
@ManagedBean(name = "generateOrgUserBeanView")
public class GenerateOrgUserBeanView {
	private ArrayList<OrgUserBean> orgUsersBeansList;

	public GenerateOrgUserBeanView() {
		generateOrgUsersBean();
	}

	public ArrayList<OrgUserBean> generateOrgUsersBean() {
		GenerateOrgUserBeanAction action = new GenerateOrgUserBeanAction();
		try {
			orgUsersBeansList = action.generateOrgUsersBean(FacesProvider.getInstance().getLoggedInUserBean().getId());
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
