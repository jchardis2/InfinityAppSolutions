package com.infinityappsolutions.lib.webterms.bean;

public class Term {
	public Long id;
	public String name;
	public String definition;
	public Long ownerId;

	public Term() {

	}

	public Term(Long id, String name, String definition, Long ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.definition = definition;
		this.ownerId = ownerId;
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

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

}
