package com.chatapp.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.chatapp.exception.JwtAuthenticationException;
import com.chatapp.model.MinimalProfile;
import com.chatapp.service.JwtService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @SuppressWarnings("unused")
    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Optional<MinimalProfile> possibleProfile = jwtService.verify((String) authentication.getCredentials());
            return new JwtAuthenticatedProfile(possibleProfile.get());
        } catch (Exception e) {
            throw new JwtAuthenticationException("Failed to verify token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
