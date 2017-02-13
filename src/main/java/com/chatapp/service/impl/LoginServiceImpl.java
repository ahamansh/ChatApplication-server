package com.chatapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chatapp.auth.JwtAuthenticatedProfile;
import com.chatapp.cache.LoggedInUserCache;
import com.chatapp.model.LoginCredentials;
import com.chatapp.model.MinimalProfile;
import com.chatapp.service.LoginService;
import com.chatapp.service.LogoutService;

@Component
public class LoginServiceImpl implements LoginService,LogoutService {

	private ProfileServiceImpl profileService;

	@SuppressWarnings("unused")
	public LoginServiceImpl() {
		this(null);
	}

	@Autowired
	public LoginServiceImpl(ProfileServiceImpl profileService) {
		this.profileService = profileService;
	}

	/* (non-Javadoc)
	 * @see com.chatapp.service.impl.LoginService#login(com.chatapp.model.LoginCredentials)
	 */
	@Override
	public Optional<MinimalProfile> login(LoginCredentials credentials) {



		return profileService.get(credentials.getUsername())
				.filter(profile -> profile.getLogin().getPassword().equals(credentials.getPassword()))
				.map(profile -> new MinimalProfile(profile));
	}

	@Override
	public Optional<MinimalProfile> isUserAlreadyLoggedIn(
			LoginCredentials credentials) {
		// TODO Auto-generated method stub

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

		Optional<MinimalProfile> prof = Optional.empty();


		if(loggedInUser == null || 
				!(loggedInUser instanceof MinimalProfile) || 
				!((MinimalProfile)loggedInUser).getUsername().equals(credentials.getUsername()))
			return prof;

		prof = Optional.of((MinimalProfile)loggedInUser);

		return prof;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();		

		if( loggedInUser instanceof JwtAuthenticatedProfile)		
			LoggedInUserCache.logoutUserFromCache((MinimalProfile) loggedInUser.getPrincipal());

	}
}
