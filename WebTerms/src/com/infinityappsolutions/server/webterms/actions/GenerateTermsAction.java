package com.infinityappsolutions.server.webterms.actions;

import java.util.ArrayList;

import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.mysql.OrgUserDAO;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.webterms.beans.Term;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;
import com.infinityappsolutions.server.webterms.dao.mysql.TermsDAO;

public class GenerateTermsAction {

	private DAOFactory daoFactory;

	public GenerateTermsAction(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ArrayList<Term> generateOrgUsersBean() throws DBException,
			IASException {
		LoggedInUserBean liub = IASRootFacesProvider.getInstance()
				.getLoggedInUserBean();
		TermsDAO termsDAO = new TermsDAO(daoFactory);
		return termsDAO.getTermsByOwner(liub.getId());
	}

	public void saveTerm(Term term) throws DBException {
		LoggedInUserBean liub = IASRootFacesProvider.getInstance()
				.getLoggedInUserBean();
		TermsDAO termsDAO = new TermsDAO(daoFactory);
		termsDAO.editTerm(term);
	}

	public void setCurrentOrgUserBean(OrgUserBean oub) {
		LoggedInUsersOrgContainerBean liucb = IASRootFacesProvider
				.getInstance().getLoggedInUsersOrgContainerBean();
		liucb.setCurrentOrgUsersBean(oub);
	}
}
