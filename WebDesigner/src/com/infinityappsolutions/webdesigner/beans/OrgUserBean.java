package com.infinityappsolutions.webdesigner.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "orgUserBean")
public class OrgUserBean implements Serializable {
	private static final long serialVersionUID = -2313692260336098747L;
	private Long orgid;
	private Long userid;
	private String name;
	private boolean createProjects;
	private boolean deleteProjects;
	private boolean editProjects;
	private boolean addUser;
	private boolean addAdmin;

	public OrgUserBean() {
	}

	public OrgUserBean(Long orgid, Long userid, String name,
			boolean createProjects, boolean deleteProjects,
			boolean editProjects, boolean addUser, boolean addAdmin) {
		super();
		this.orgid = orgid;
		this.userid = userid;
		this.name = name;
		this.createProjects = createProjects;
		this.deleteProjects = deleteProjects;
		this.editProjects = editProjects;
		this.addUser = addUser;
		this.addAdmin = addAdmin;
	}

	public boolean isCreateProjects() {
		return createProjects;
	}

	public void setCreateProjects(boolean createProjects) {
		this.createProjects = createProjects;
	}

	public boolean isDeleteProjects() {
		return deleteProjects;
	}

	public void setDeleteProjects(boolean deleteProjects) {
		this.deleteProjects = deleteProjects;
	}

	public boolean isEditProjects() {
		return editProjects;
	}

	public void setEditProjects(boolean editProjects) {
		this.editProjects = editProjects;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public boolean isAddUser() {
		return addUser;
	}

	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}

	public boolean isAddAdmin() {
		return addAdmin;
	}

	public void setAddAdmin(boolean addAdmin) {
		this.addAdmin = addAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}