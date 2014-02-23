package com.infinityappsolutions.server.actions;

import com.infinityappsolutions.server.lib.beans.UserBean;
import com.infinityappsolutions.server.lib.dao.DAOFactory;
import com.infinityappsolutions.server.lib.dao.mysql.UserDAO;
import com.infinityappsolutions.server.lib.exceptions.DBException;

public class CreateAccountAction {

	public void createAccount(UserBean ub) throws DBException {
		UserDAO dao = new UserDAO(DAOFactory.getProductionInstance());
		dao.addNewUser(ub);
	}
}
