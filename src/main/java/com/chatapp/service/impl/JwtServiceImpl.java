package com.chatapp.service.impl;


import static java.time.ZoneOffset.UTC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.cache.LoggedInUserCache;
import com.chatapp.model.MinimalProfile;
import com.chatapp.service.JwtService;
import com.chatapp.service.ProfileService;
import com.chatapp.util.SecretKeyProvider;

@Component
public class JwtServiceImpl implements JwtService {
	
    private static final String ISSUER = "in.sdqali.jwt";
    public static final String USERNAME = "username";
    
    private final SecretKeyProvider secretKeyProvider;
    private final ProfileService profileService;

    @SuppressWarnings("unused")
    public JwtServiceImpl() {
        this(null, null);
    }

    @Autowired
    public JwtServiceImpl(SecretKeyProvider secretKeyProvider, ProfileService profileService) {
        this.secretKeyProvider = secretKeyProvider;
        this.profileService = profileService;
    }

    /* (non-Javadoc)
	 * @see com.chatapp.service.impl.JwtService#tokenFor(com.chatapp.model.MinimalProfile)
	 */
    @Override
	public String tokenFor(MinimalProfile minimalProfile) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
        String token = Jwts.builder()
                .setSubject("jwt-demo")
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .claim(USERNAME, minimalProfile.getUsername())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        
        LoggedInUserCache.addLoggedInUserToCache(token, minimalProfile);
        
        return token;
    }

    /* (non-Javadoc)
	 * @see com.chatapp.service.impl.JwtService#verify(java.lang.String)
	 */
    @Override
	public Optional<MinimalProfile> verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return profileService.minimal(claims.getBody().get(USERNAME).toString());
    }
}
