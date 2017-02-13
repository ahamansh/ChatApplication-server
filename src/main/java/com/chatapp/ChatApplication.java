package com.chatapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ChatApplication {

	@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ChatApplication.class);
	
	public static void main(String[] args) {
		logger.debug("Starting Chat application");
		SpringApplication.run(ChatApplication.class, args);
	}	
    
    
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }
}
