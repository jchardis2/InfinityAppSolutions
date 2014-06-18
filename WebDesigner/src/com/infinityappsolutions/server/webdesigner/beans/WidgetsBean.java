package com.infinityappsolutions.server.webdesigner.beans;

public class WidgetsBean extends WidgetTemplateBean {
	private long widgetID;

	public WidgetsBean(long widgetID, long bwtBeanID, long orgid, String name,
			long widgettypeid, Boolean iscontainer, String element,
			String droppableTarget) {
		super(bwtBeanID, orgid, name, widgettypeid, iscontainer, element,
				droppableTarget);
		this.widgetID = widgetID;
	}

	public long getWidgetID() {
		return widgetID;
	}

	public void setWidgetID(long widgetID) {
		this.widgetID = widgetID;
	}

}