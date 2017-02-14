package com.chatapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.auth.AuthResponse;
import com.chatapp.cache.LoggedInUserCache;
import com.chatapp.model.Message;
import com.chatapp.model.MinimalProfile;
import com.chatapp.model.OutputMessage;
import com.chatapp.service.JwtService;
import com.chatapp.service.LoginService;
import com.chatapp.service.LogoutService;

@RestController
public class UserController {
	
	 private final LogoutService logoutService;
	 
	 @Autowired
	    public UserController(LogoutService logoutService) {
	        this.logoutService = logoutService;
	    }
	
	//@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/users")
	public List<MinimalProfile> getUsers(){
		
    	return LoggedInUserCache.getAllLoggedInUsers();
		
	}
    
    @GetMapping("/api/remove")
    public AuthResponse remove() {
    	
    	this.logoutService.logout();
    	return new AuthResponse("logout");
    	
    }
    
    @GetMapping("/api/loguser")
    public AuthResponse logCurrentUser(){
    	
    	System.out.println("This user");
    	
    	return new AuthResponse("Logged");
    }
    
    @MessageMapping("/api/hello")
    @SendTo("/topic/greetings")
    public OutputMessage greeting() throws Exception {
        Thread.sleep(1000); // simulated delay
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        System.out.println("I dont know");
        return new OutputMessage("ansh", "Hi", time);
       // return output;
    }   
    
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public OutputMessage send(final Message message) throws Exception {
//
//        final String time = new SimpleDateFormat("HH:mm").format(new Date());
//        return new OutputMessage(message.getFrom(), message.getText(), time);
//    }
	
	
}
