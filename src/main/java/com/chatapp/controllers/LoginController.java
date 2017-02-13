package com.chatapp.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.auth.AuthResponse;
import com.chatapp.exception.FailedToLoginException;
import com.chatapp.model.LoginCredentials;
import com.chatapp.model.MinimalProfile;
import com.chatapp.service.JwtService;
import com.chatapp.service.LoginService;

@RestController
public class LoginController {

    private final LoginService loginService;
    private final JwtService jwtService;

    @SuppressWarnings("unused")
    public LoginController() {
        this(null, null);
    }

    @Autowired
    public LoginController(LoginService loginService, JwtService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public MinimalProfile login(@RequestBody LoginCredentials credentials,
                                HttpServletResponse response) {
    	
    	
    	Optional<MinimalProfile> profile = loginService.isUserAlreadyLoggedIn(credentials)
    			.map(minimalProfile -> { return minimalProfile;});
    	
    	if(profile.isPresent())
    		return profile.get();
    	else
        return loginService.login(credentials)
                .map(minimalProfile -> {
                	
                	AuthResponse finalReturn = new AuthResponse();
                	
                    try {
                    	String token = jwtService.tokenFor(minimalProfile);
                    	Cookie rt_identity = new Cookie("SM_IDENTITY", token);
                    	response.addCookie(rt_identity);
                        response.setHeader("Token", token);
                        finalReturn.setToken(token);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return minimalProfile;
                })
                .orElseThrow(() -> new FailedToLoginException(credentials.getUsername()));
    }
    
   
    
    
}
