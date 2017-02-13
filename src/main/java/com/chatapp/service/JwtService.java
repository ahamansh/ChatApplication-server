package com.chatapp.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import com.chatapp.model.MinimalProfile;

public interface JwtService {

	public abstract String tokenFor(MinimalProfile minimalProfile)
			throws IOException, URISyntaxException;

	public abstract Optional<MinimalProfile> verify(String token)
			throws IOException, URISyntaxException;

}