package com.chatapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.factory.PorfilePictureFactory;
import com.chatapp.model.Login;
import com.chatapp.model.LoginCredentials;
import com.chatapp.model.MinimalProfile;
import com.chatapp.model.Name;
import com.chatapp.model.Picture;
import com.chatapp.model.Profile;
import com.chatapp.service.ProfileService;
import com.chatapp.service.RegistrationService;

@Component
public class RegistrationServiceImpl implements RegistrationService {

	private final ProfileService profileService;
	
	@Autowired
	public RegistrationServiceImpl(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}


	@Override
	public Optional<MinimalProfile> registerNewUser(LoginCredentials newUser) {
		// TODO Auto-generated method stub
		
		Login login = new Login(newUser.getUsername(), newUser.getPassword());
		Name name = new Name(newUser.getFirstName(), newUser.getLastName());
		Profile genProfile = new Profile(
				name, 
				name.toString()+"@example.com", 
				login, 
				PorfilePictureFactory.getRandomPicture());
		
		return profileService.registerNew(genProfile);
		
		
	}

}
