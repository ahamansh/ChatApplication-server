package com.chatapp.util;

import com.chatapp.model.User;

public class UserFactory {
	
	
	public static User getRandomUser(){		
		return new User(String.valueOf(Math.random()),String.valueOf(Math.random())+"@gmail.com");
	}

}
