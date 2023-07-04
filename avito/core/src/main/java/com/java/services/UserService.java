package com.java.services;

import com.java.models.User;
import com.java.repositoryes.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User findUser(String userName) {
		return userRepository.findByUsername(userName);
	}
}
