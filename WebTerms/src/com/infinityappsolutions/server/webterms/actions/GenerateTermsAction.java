package com.infinityappsolutions.server.webterms.actions;

import java.util.ArrayList;

import com.infinityappsolutions.lib.webterms.bean.Term;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;
import com.infinityappsolutions.server.webterms.dao.mysql.TermsDAO;

public class GenerateTermsAction {

	private DAOFactory daoFactory;

	public GenerateTermsAction(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ArrayList<Term> generateTermList() throws DBException, IASException {
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

	public void generateEmptyTerm() throws DBException {
		LoggedInUserBean liub = IASRootFacesProvider.getInstance()
				.getLoggedInUserBean();
		TermsDAO termsDAO = new TermsDAO(daoFactory);
		termsDAO.insertTerm(new Term(0L, "", "", liub.getId()));
	}

	public void setCurrentOrgUserBean(OrgUserBean oub) {
		LoggedInUsersOrgContainerBean liucb = IASRootFacesProvider
				.getInstance().getLoggedInUsersOrgContainerBean();
		liucb.setCurrentOrgUsersBean(oub);
	}

	public void deleteTerms(ArrayList<Term> terms) throws DBException {
		TermsDAO termsDAO = new TermsDAO(daoFactory);
		termsDAO.deleteTerms(terms);
	}

	public void saveTerms(ArrayList<Term> termList) throws DBException {
		TermsDAO termsDAO = new TermsDAO(daoFactory);
		termsDAO.saveTerms(termList);
	}
}
