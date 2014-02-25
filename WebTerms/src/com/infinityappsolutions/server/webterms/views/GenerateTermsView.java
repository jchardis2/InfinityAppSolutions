package com.infinityappsolutions.server.webterms.views;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.webterms.actions.GenerateTermsAction;
import com.infinityappsolutions.server.webterms.beans.Term;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;

@ViewScoped
@ManagedBean(name = "generateTermsView")
public class GenerateTermsView implements Serializable {
	private static final long serialVersionUID = 6752442236530393770L;
	public ArrayList<Term> termList;
	public Term savedTerm;

	public GenerateTermsView() {
		generateTerms();
	}

	public ArrayList<Term> getTermList() {
		return termList;
	}

	public void setTermList(ArrayList<Term> termList) {
		this.termList = termList;
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public ArrayList<Term> generateTerms() {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());
		try {
			termList = generateTermsAction.generateTermList();
			return termList;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Term getSavedTerm() {
		return savedTerm;
	}

	public void setSavedTerm(Term savedTerm) {
		this.savedTerm = savedTerm;
		saveTerm(savedTerm);

	}

	public void saveTerm(Term term) {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());

		try {
			generateTermsAction.saveTerm(term);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateEmptyTerm() {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());

		try {
			generateTermsAction.generateEmptyTerm();
			generateTerms();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String addTerm() {
		LoggedInUserBean liub = IASRootFacesProvider.getInstance()
				.getLoggedInUserBean();
		termList.add(new Term(0L, "", "", liub.getId()));
		return null;
	}

}
