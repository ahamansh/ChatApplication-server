package com.chatapp.service;

import java.util.Optional;

import com.chatapp.model.DetailedProfile;
import com.chatapp.model.MinimalProfile;
import com.chatapp.model.Profile;

public interface ProfileService {

	public abstract Optional<MinimalProfile> minimal(String username);

	public abstract Optional<DetailedProfile> detailed(String username);
	
	public abstract Optional<MinimalProfile> registerNew(Profile newProfile);

}