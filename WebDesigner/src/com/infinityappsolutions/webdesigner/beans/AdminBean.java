package com.infinityappsolutions.webdesigner.beans;
public class AdminBean  {

	private Long id;
	private String username;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	public AdminBean(){}
	public AdminBean( Long id, String username, String email, String password, String firstname, String lastname){}
	public Long getId(){return id;}
	public void setId(Long id){this. id=id;}
	public String getUsername(){return username;}
	public void setUsername(String username){this. username=username;}
	public String getEmail(){return email;}
	public void setEmail(String email){this. email=email;}
	public String getPassword(){return password;}
	public void setPassword(String password){this. password=password;}
	public String getFirstname(){return firstname;}
	public void setFirstname(String firstname){this. firstname=firstname;}
	public String getLastname(){return lastname;}
	public void setLastname(String lastname){this. lastname=lastname;}
}