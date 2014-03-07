package com.infinityappsolutions.lib.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBean<T> implements Serializable {
	private static final long serialVersionUID = -2219141609441574910L;
	private UserBean ub;
	private ArrayList<T> terms;

	public UserBean getUserBean() {
		return ub;
	}

	public void setUserBean(UserBean ub) {
		this.ub = ub;
	}

	public UserBean getUb() {
		return ub;
	}

	public void setUb(UserBean ub) {
		this.ub = ub;
	}

	public ArrayList<T> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<T> terms) {
		this.terms = terms;
	}

}
