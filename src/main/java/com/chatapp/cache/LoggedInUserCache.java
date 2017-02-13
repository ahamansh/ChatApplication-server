package com.chatapp.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.chatapp.model.MinimalProfile;

public class LoggedInUserCache {



	private static ConcurrentHashMap<String, MinimalProfile> cache = new ConcurrentHashMap<String, MinimalProfile>();


	public static List<MinimalProfile> getAllLoggedInUsers() {			
		return new ArrayList<MinimalProfile>(cache.values());
	}

	public static MinimalProfile addLoggedInUserToCache(String token,MinimalProfile profile) {			
		return cache.put(token, profile);				
	}
	
	public static void logoutUserFromCache(MinimalProfile profile){
		
		
		Optional<Entry<String, MinimalProfile>> entryVal =  cache.entrySet().stream().
							filter(entry -> entry.getValue().getUsername().equals(profile.getUsername())).findFirst();
		
		String token = entryVal.
							map(entry -> { return entry.getKey();}).orElse("");
		
		cache.remove(token);
		
		System.out.println("Removed user "+profile.getUsername()+" From cache");
		
	}
	
	


}
