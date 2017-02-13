package com.chatapp.model;

import java.net.URL;


public class MinimalProfile {
	
    private  String username;
    private  Name name;
    private  URL thumbnail;

    public MinimalProfile(Profile profile) {
        name = profile.getName();
        username = profile.getLogin().getUsername();
        thumbnail = profile.getPicture().getThumbnail();
    }
    
    

	public MinimalProfile(String username, Name name, URL thumbnail) {
		super();
		this.username = username;
		this.name = name;
		this.thumbnail = thumbnail;
	}



	public MinimalProfile() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getUsername() {
		return username;
	}

	public Name getName() {
		return name;
	}

	public URL getThumbnail() {
		return thumbnail;
	}
    
    
}
