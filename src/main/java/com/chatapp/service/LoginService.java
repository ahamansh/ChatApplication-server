package com.chatapp.service;

import java.util.Optional;

import com.chatapp.model.LoginCredentials;
import com.chatapp.model.MinimalProfile;

public interface LoginService {

	public abstract Optional<MinimalProfile> login(LoginCredentials credentials);
	public abstract Optional<MinimalProfile> isUserAlreadyLoggedIn(LoginCredentials credentials);

}