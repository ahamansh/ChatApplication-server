package com.chatapp.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.chatapp.model.DetailedProfile;
import com.chatapp.model.MinimalProfile;
import com.chatapp.model.Profile;
import com.chatapp.service.ProfileService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProfileServiceImpl implements ProfileService {
	private final List<Profile> profiles;

	private final Path PROFILES_FILE = Paths.get(this.getClass().getResource("/profiles.json").toURI());

	public ProfileServiceImpl() throws IOException, URISyntaxException {
		ObjectMapper objectMapper = new ObjectMapper();
		profiles = objectMapper.readValue(PROFILES_FILE.toFile(), new TypeReference<List<Profile>>() {
		});
	}

	protected Optional<Profile> get(String username) {
		Optional<Profile> prof = profiles.stream()
				.filter(profile -> profile.getLogin().getUsername().equals(username))
				.findFirst();

		return prof;
	}

	/* (non-Javadoc)
	 * @see com.chatapp.service.impl.ProfileService#minimal(java.lang.String)
	 */
	@Override
	public Optional<MinimalProfile> minimal(String username) {
		return get(username).map(profile -> new MinimalProfile(profile));
	}

	/* (non-Javadoc)
	 * @see com.chatapp.service.impl.ProfileService#detailed(java.lang.String)
	 */
	@Override
	public Optional<DetailedProfile> detailed(String username) {
		return get(username).map(profile -> new DetailedProfile(profile));
	}

	@Override
	public Optional<MinimalProfile> registerNew(Profile newProfile) {
		// TODO Auto-generated method stub
		profiles.add(newProfile);
		return get(newProfile.getLogin().getUsername())
				.map(profile -> new MinimalProfile(profile));
	}



}
