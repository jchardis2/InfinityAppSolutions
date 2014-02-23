package com.infinityappsolutions.server.actions.ui;

import java.util.ArrayList;

import com.infinityappsolutions.server.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.server.faces.FacesProvider;
import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.dao.DAOFactory;
import com.infinityappsolutions.server.lib.dao.mysql.OrgUserDAO;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;

public class GenerateOrgUserBeanAction {

	public GenerateOrgUserBeanAction() {
	}

	public ArrayList<OrgUserBean> generateOrgUsersBean(long userid)
			throws DBException, IASException {
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
