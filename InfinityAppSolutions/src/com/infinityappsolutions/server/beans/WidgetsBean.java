package com.infinityappsolutions.server.beans;
public class WidgetsBean  {

	private Long id;
	private Long orgid;
	private String name;
	private Long widgettypeid;
	private Boolean iscontainer;
	private String element;
	private String droppableTarget;
	public WidgetsBean(){}
	public WidgetsBean( Long id, Long orgid, String name, Long widgettypeid, Boolean iscontainer, String element, String droppableTarget){}
	public Long getId(){return id;}
	public void setId(Long id){this. id=id;}
	public Long getOrgid(){return orgid;}
	public void setOrgid(Long orgid){this. orgid=orgid;}
	public String getName(){return name;}
	public void setName(String name){this. name=name;}
	public Long getWidgettypeid(){return widgettypeid;}
	public void setWidgettypeid(Long widgettypeid){this. widgettypeid=widgettypeid;}
	public Boolean getIscontainer(){return iscontainer;}
	public void setIscontainer(Boolean iscontainer){this. iscontainer=iscontainer;}
	public String getElement(){return element;}
	public void setElement(String element){this. element=element;}
	public String getDroppableTarget(){return droppableTarget;}
	public void setDroppableTarget(String droppableTarget){this. droppableTarget=droppableTarget;}
}