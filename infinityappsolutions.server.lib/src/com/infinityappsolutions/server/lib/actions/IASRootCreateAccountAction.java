package com.infinityappsolutions.server.lib.actions;

import com.infinityappsolutions.lib.beans.UserBean;
import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.mysql.UserDAO;
import com.infinityappsolutions.server.lib.exceptions.DBException;

public class IASRootCreateAccountAction {
	private AbstractDAOFactory daoFactory;

	public IASRootCreateAccountAction(AbstractDAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void createAccount(UserBean ub) throws DBException {
		UserDAO dao = new UserDAO(daoFactory);
		dao.addNewUser(ub);
	}
}
