package com.infinityappsolutions.server.actions.ui;

import java.util.ArrayList;

import com.infinityappsolutions.server.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.server.beans.OrgUserBean;
import com.infinityappsolutions.server.dao.DAOFactory;
import com.infinityappsolutions.server.dao.mysql.OrgUserDAO;
import com.infinityappsolutions.server.exceptions.DBException;
import com.infinityappsolutions.server.exceptions.WebDesignerException;
import com.infinityappsolutions.server.faces.FacesProvider;

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
