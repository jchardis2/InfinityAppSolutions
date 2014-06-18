package com.infinityappsolutions.server.webdesigner.beans;

/**
 * A bean for each widget that will act as a template to be copied and placed on
 * a web page. A Basic widget template bean is one which was created by IAS
 * 
 * @author jchardis
 * 
 */
public class BasicWidgetTemplateBean {

	private long bwtBeanID;
	private String name;
	private long widgettypeid;
	private Boolean iscontainer;
	private String element;
	private String droppableTarget;

	public BasicWidgetTemplateBean(long bwtBeanID, String name,
			long widgettypeid, Boolean iscontainer, String element,
			String droppableTarget) {
		super();
		this.bwtBeanID = bwtBeanID;
		this.name = name;
		this.widgettypeid = widgettypeid;
		this.iscontainer = iscontainer;
		this.element = element;
		this.droppableTarget = droppableTarget;
	}

	public long getId() {
		return bwtBeanID;
	}

	public void setId(long id) {
		this.bwtBeanID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWidgettypeid() {
		return widgettypeid;
	}

	public void setWidgettypeid(long widgettypeid) {
		this.widgettypeid = widgettypeid;
	}

	public Boolean getIscontainer() {
		return iscontainer;
	}

	public void setIscontainer(Boolean iscontainer) {
		this.iscontainer = iscontainer;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getDroppableTarget() {
		return droppableTarget;
	}

	public void setDroppableTarget(String droppableTarget) {
		this.droppableTarget = droppableTarget;
	}
}