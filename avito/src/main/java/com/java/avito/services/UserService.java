package com.java.avito.services;

import com.java.avito.models.User;
import com.java.avito.repositoryes.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(User user) {
        user.setRoles(List.of(roleService.getUserRole()));
        userRepository.save(user);
    }
}