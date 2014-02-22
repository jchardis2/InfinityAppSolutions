package com.infinityappsolutions.webdesigner.actions;

import com.infinityappsolutions.webdesigner.beans.UserBean;
import com.infinityappsolutions.webdesigner.dao.DAOFactory;
import com.infinityappsolutions.webdesigner.dao.mysql.UserDAO;
import com.infinityappsolutions.webdesigner.exceptions.DBException;

public class CreateAccountAction {

	public void createAccount(UserBean ub) throws DBException {
		UserDAO dao = new UserDAO(DAOFactory.getProductionInstance());
		dao.addNewUser(ub);
	}
}
