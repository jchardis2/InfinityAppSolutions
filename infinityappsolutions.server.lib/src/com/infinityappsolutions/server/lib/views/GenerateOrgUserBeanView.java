package com.infinityappsolutions.server.lib.views;

import java.io.Serializable;
import java.util.ArrayList;

import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.lib.ui.actions.GenerateOrgUserBeanAction;

public class GenerateOrgUserBeanView implements Serializable {
	private static final long serialVersionUID = 1888897305093017519L;
	private ArrayList<OrgUserBean> orgUsersBeansList;

	public GenerateOrgUserBeanView() {
		generateOrgUsersBean();
	}

	public ArrayList<OrgUserBean> generateOrgUsersBean() {
		GenerateOrgUserBeanAction action = new GenerateOrgUserBeanAction(
				AbstractDAOFactory.getProductionInstance());
		try {
			orgUsersBeansList = action
					.generateOrgUsersBean(IASRootFacesProvider.getInstance()
							.getLoggedInUserBean().getId());
			return orgUsersBeansList;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
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
