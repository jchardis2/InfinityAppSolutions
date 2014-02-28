package com.infinityappsolutions.server.lib.ui.actions;

import java.util.ArrayList;

import com.infinityappsolutions.server.lib.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.mysql.OrgUserDAO;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;

public class GenerateOrgUserBeanAction {

	private AbstractDAOFactory daoFactory;

	public GenerateOrgUserBeanAction(AbstractDAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ArrayList<OrgUserBean> generateOrgUsersBean(long userid)
			throws DBException, IASException {
		LoggedInUsersOrgContainerBean liucb = IASRootFacesProvider
				.getInstance().getLoggedInUsersOrgContainerBean();
		OrgUserDAO dao = new OrgUserDAO(daoFactory);
		ArrayList<OrgUserBean> orgUsersBeansList = dao.getOrgUsers(userid);
		liucb.setOrgUsersBeansList(orgUsersBeansList);
		liucb.setCurrentOrgUsersBean(liucb.getOrgUsersBeansList().get(0));
		return orgUsersBeansList;
	}

	public void setCurrentOrgUserBean(OrgUserBean oub) {
		LoggedInUsersOrgContainerBean liucb = IASRootFacesProvider
				.getInstance().getLoggedInUsersOrgContainerBean();
		liucb.setCurrentOrgUsersBean(oub);
	}
}
