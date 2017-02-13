package com.chatapp.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.exception.FailedToRegisterException;
import com.chatapp.model.LoginCredentials;
import com.chatapp.model.MinimalProfile;
import com.chatapp.service.RegistrationService;


@RestController
public class RegistrationController {
	
	
	private final RegistrationService registerService;

	@Autowired
	public RegistrationController(RegistrationService registerService) {
		super();
		this.registerService = registerService;
	}
	
	
	@PostMapping("/register")
    public MinimalProfile registerUser(@RequestBody LoginCredentials newProfile,
                                HttpServletResponse response) {
		
		
		return registerService.registerNewUser(newProfile)
				.map(minimalProfile -> {
                    
                    return minimalProfile;
                })
                .orElseThrow(() -> new FailedToRegisterException(newProfile.getUsername()));
		
		
		
	}
	
}
