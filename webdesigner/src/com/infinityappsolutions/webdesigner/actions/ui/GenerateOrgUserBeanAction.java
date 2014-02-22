package com.infinityappsolutions.webdesigner.actions.ui;

import java.util.ArrayList;

import com.infinityappsolutions.webdesigner.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.webdesigner.beans.OrgUserBean;
import com.infinityappsolutions.webdesigner.dao.DAOFactory;
import com.infinityappsolutions.webdesigner.dao.mysql.OrgUserDAO;
import com.infinityappsolutions.webdesigner.exceptions.DBException;
import com.infinityappsolutions.webdesigner.exceptions.WebDesignerException;
import com.infinityappsolutions.webdesigner.faces.FacesProvider;

public class GenerateOrgUserBeanAction {

	public GenerateOrgUserBeanAction() {
	}

	public ArrayList<OrgUserBean> generateOrgUsersBean(long userid) throws DBException, WebDesignerException {
		LoggedInUsersOrgContainerBean liucb = FacesProvider.getInstance().getLoggedInUsersOrgContainerBean();
		OrgUserDAO dao = new OrgUserDAO(DAOFactory.getProductionInstance());
		ArrayList<OrgUserBean> orgUsersBeansList = dao.getOrgUsers(userid);
		liucb.setOrgUsersBeansList(orgUsersBeansList);
		liucb.setCurrentOrgUsersBean(liucb.getOrgUsersBeansList().get(0));
		return orgUsersBeansList;
	}

	public void setCurrentOrgUserBean(OrgUserBean oub) {
		LoggedInUsersOrgContainerBean liucb = FacesProvider.getInstance().getLoggedInUsersOrgContainerBean();
		liucb.setCurrentOrgUsersBean(oub);
	}
}
