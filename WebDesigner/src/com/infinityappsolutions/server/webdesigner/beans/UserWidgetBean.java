package com.infinityappsolutions.server.webdesigner.beans;

/**
 * This is a widget bean for widgets that have been placed inside of a users web
 * page
 * 
 * @author jchardis
 * 
 */
public class UserWidgetBean extends UserWidgetTemplateBean {
	private long uwID;

	public UserWidgetBean(long uwID, long bwtBeanID, long orgid, String name,
			long widgettypeid, Boolean iscontainer, String element,
			String droppableTarget) {
		super(bwtBeanID, orgid, name, widgettypeid, iscontainer, element,
				droppableTarget);
		this.uwID = uwID;
	}

	public long getUwID() {
		return uwID;
	}

	public void setUwID(long uwID) {
		this.uwID = uwID;
	}

}