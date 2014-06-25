package com.infinityappsolutions.server.webdesigner.beans;

/**
 * A bean for each widget that will act as a template to be copied and placed on
 * a web page
 * 
 * @author jchardis
 * 
 */
public class UserWidgetTemplateBean extends BasicWidgetTemplateBean {
	private long orgID;

	public UserWidgetTemplateBean(long bwtBeanID, long orgid, String name,
			long widgettypeid, Boolean iscontainer, String element,
			String droppableTarget) {
		super(bwtBeanID, name, widgettypeid, iscontainer, element,
				droppableTarget);
		this.orgID = orgid;
	}

	public long getOrgID() {
		return orgID;
	}

	public void setOrgID(long orgID) {
		this.orgID = orgID;
	}

}