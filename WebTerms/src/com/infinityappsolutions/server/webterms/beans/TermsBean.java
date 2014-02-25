package com.infinityappsolutions.server.webterms.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.webterms.actions.GenerateTermsAction;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;

@ViewScoped
@ManagedBean(name = "termsBean")
public class TermsBean implements Serializable {
	private static final long serialVersionUID = 8689077504747301832L;
	public ArrayList<Term> termsList;

	public TermsBean() {
		generateTerms();
	}

	public ArrayList<Term> getTerms() {
		return termsList;
	}

	public void setTerms(ArrayList<Term> terms) {
		this.termsList = terms;
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
			termsList = generateTermsAction.generateOrgUsersBean();
			return termsList;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
