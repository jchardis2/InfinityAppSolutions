package com.infinityappsolutions.server.lib.beans;

public class OrgBean {

	private Long id;
	private String name;

	public OrgBean() {
	}

	public OrgBean(Long id, String name) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}