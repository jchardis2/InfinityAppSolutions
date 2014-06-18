package com.infinityappsolutions.server.webdesigner.beans;

public class WidgetTypesBean {

	private long id;
	private String name;
	private long orgid;

	public WidgetTypesBean() {
	}

	public WidgetTypesBean(long id, String name, long orgid) {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}
}