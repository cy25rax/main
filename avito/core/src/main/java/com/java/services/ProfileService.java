package com.java.services;

import com.java.models.Advertisement;
import com.java.models.User;
import com.java.repositoryes.AdvetisementRepository;
import com.java.repositoryes.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
	
	private final AdvetisementRepository advetisementRepository;
	private final UserRepository userRepository;
	
	public List<Advertisement> getProfile(String username) {
		return advetisementRepository.findByUser(getUser(username));
	}
	
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
}
