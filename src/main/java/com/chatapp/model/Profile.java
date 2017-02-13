package com.chatapp.model;



public class Profile {
    private Name name;
    private String email;
    private Login login;
    private Picture picture;
    
    
    public Profile(Name name, String email, Login login, Picture picture) {
		super();
		this.name = name;
		this.email = email;
		this.login = login;
		this.picture = picture;
	}
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
    
    
    
    
    
    
}
