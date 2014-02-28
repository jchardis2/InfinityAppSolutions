package com.infinityappsolutions.server.lib.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "orgUserBean")
public class OrgUserBean implements Serializable {
	private static final long serialVersionUID = -2654536084040779773L;
	private Long orgid;
	private Long userid;
	private String name;

	public OrgUserBean() {
	}

	public OrgUserBean(Long orgid, Long userid, String name) {
		super();
		this.orgid = orgid;
		this.userid = userid;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}