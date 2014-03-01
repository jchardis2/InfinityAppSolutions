package com.infinityappsolutions.server.webterms.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;

import com.infinityappsolutions.lib.webterms.bean.Term;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.webterms.actions.GenerateTermsAction;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;

@ViewScoped
@ManagedBean(name = "generateTermsView")
public class GenerateTermsView implements Serializable {
	private static final long serialVersionUID = 6752442236530393770L;
	public ArrayList<Term> termList;
	public ArrayList<Term> selectedTerms;
	public Term selectedTerm;
	public Term savedTerm;
	public boolean isSelected;
	public HashMap<Long, Boolean> checkedMap;

	public GenerateTermsView() {
		checkedMap = new HashMap<>();
		selectedTerms = new ArrayList<>();
		generateTerms();
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
			termList = generateTermsAction
					.generateTermList(IASRootFacesProvider.getInstance()
							.getLoggedInUserBean());
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

	public void saveTerm(Term term) {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());

		try {
			generateTermsAction.saveTerm(getSelectedTerm(),
					IASRootFacesProvider.getInstance().getLoggedInUserBean());
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateEmptyTerm(ActionEvent event) {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());

		try {
			generateTermsAction.generateEmptyTerm(IASRootFacesProvider
					.getInstance().getLoggedInUserBean());
			generateTerms();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String selectTerm(ActionEvent event) {
		Term term = (Term) event.getComponent().getAttributes()
				.get("selectedTerm");
		System.out.println("Term name:" + term.getName());
		if (term != null)
			selectedTerms.add(term);
		return null;
	}

	public String unSelectTerm(ActionEvent event) {
		Term term = (Term) event.getComponent().getAttributes()
				.get("selectedTerm");
		if (term != null)
			selectedTerms.remove(term);
		return null;
	}

	public void deleteTerms(ActionEvent event) {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());
		ArrayList<Term> checkedItems = getCheckedTerms();
		try {
			generateTermsAction.deleteTerms(checkedItems);
			generateTerms();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveTerms(ActionEvent event) {
		GenerateTermsAction generateTermsAction = new GenerateTermsAction(
				DAOFactory.getProductionInstance());
		try {
			generateTermsAction.saveTerms(termList);
			generateTerms();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectTerm() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String term = params.get("term");
		Boolean b = checkedMap.get(term);

		System.out.println("Boolean: " + b);

	}

	private ArrayList<Term> getCheckedTerms() {
		ArrayList<Term> checkedItems = new ArrayList<Term>();
		Iterator<Long> it = checkedMap.keySet().iterator();
		for (Term item : termList) {
			if (checkedMap.get(item.getId())) {
				checkedItems.add(item);
			}
		}
		return checkedItems;
	}

	public Term getSavedTerm() {
		return savedTerm;
	}

	public void setSavedTerm(Term savedTerm) {
		this.savedTerm = savedTerm;
		saveTerm(savedTerm);

	}

	public ArrayList<Term> getSelectedTerms() {
		return selectedTerms;
	}

	public void setSelectedTerms(ArrayList<Term> selectedTerms) {
		this.selectedTerms = selectedTerms;
	}

	public ArrayList<Term> getTermList() {
		return termList;
	}

	public void setTermList(ArrayList<Term> termList) {
		this.termList = termList;
	}

	public Term getSelectedTerm() {
		return selectedTerm;
	}

	public void setSelectedTerm(Term selectedTerm) {
		this.selectedTerm = selectedTerm;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public HashMap<Long, Boolean> getCheckedMap() {
		return checkedMap;
	}

	public void setCheckedMap(HashMap<Long, Boolean> checkedMap) {
		this.checkedMap = checkedMap;
	}

}
