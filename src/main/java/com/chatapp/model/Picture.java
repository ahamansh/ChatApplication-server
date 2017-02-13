package com.chatapp.model;

import java.net.URL;


public class Picture {
    private URL large;
    private URL medium;
    private URL thumbnail;
    
      
    
	public Picture(URL large, URL medium, URL thumbnail) {
		super();
		this.large = large;
		this.medium = medium;
		this.thumbnail = thumbnail;
	}
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public URL getLarge() {
		return large;
	}
	public void setLarge(URL large) {
		this.large = large;
	}
	public URL getMedium() {
		return medium;
	}
	public void setMedium(URL medium) {
		this.medium = medium;
	}
	public URL getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(URL thumbnail) {
		this.thumbnail = thumbnail;
	}
    
    
    
    
}
