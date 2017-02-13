package com.chatapp.service;

import java.util.Optional;

import com.chatapp.model.LoginCredentials;
import com.chatapp.model.MinimalProfile;

public interface RegistrationService {

	public Optional<MinimalProfile> registerNewUser(LoginCredentials newUser);	
	
}
