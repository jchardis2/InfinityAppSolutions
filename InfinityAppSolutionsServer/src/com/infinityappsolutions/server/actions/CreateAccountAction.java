package com.infinityappsolutions.server.actions;

import com.infinityappsolutions.server.beans.UserBean;
import com.infinityappsolutions.server.dao.DAOFactory;
import com.infinityappsolutions.server.dao.mysql.UserDAO;
import com.infinityappsolutions.server.exceptions.DBException;

public class CreateAccountAction {

	public void createAccount(UserBean ub) throws DBException {
		UserDAO dao = new UserDAO(DAOFactory.getProductionInstance());
		dao.addNewUser(ub);
	}
}
