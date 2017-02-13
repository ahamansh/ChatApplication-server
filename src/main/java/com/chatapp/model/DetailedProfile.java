package com.chatapp.model;


public class DetailedProfile {

    private  Picture picture;
    private  Name name;
    private  String email;
    private  String username;

    public DetailedProfile(Profile profile) {
        name = profile.getName();
        email = profile.getEmail();
        picture = profile.getPicture();
        username = profile.getLogin().getUsername();
    }
    
    
    

	public DetailedProfile(Picture picture, Name name, String email,
			String username) {
		super();
		this.picture = picture;
		this.name = name;
		this.email = email;
		this.username = username;
	}




	public DetailedProfile() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Picture getPicture() {
		return picture;
	}

	public Name getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}
    
}
