package com.infinityappsolutions.webdesigner.beans;
public class WidgetTypesBean  {

	private Long id;
	private String name;
	private Long orgid;
	public WidgetTypesBean(){}
	public WidgetTypesBean( Long id, String name, Long orgid){}
	public Long getId(){return id;}
	public void setId(Long id){this. id=id;}
	public String getName(){return name;}
	public void setName(String name){this. name=name;}
	public Long getOrgid(){return orgid;}
	public void setOrgid(Long orgid){this. orgid=orgid;}
}