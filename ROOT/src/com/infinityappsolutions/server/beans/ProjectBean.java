package com.infinityappsolutions.server.beans;

public class ProjectBean {

	private Long id;
	private Long orgid;
	private String name;
	private String type;
	private String descriptor;

	public ProjectBean() {
	}

	public ProjectBean(Long id, Long orgid, String name, String type,
			String descriptor) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
}