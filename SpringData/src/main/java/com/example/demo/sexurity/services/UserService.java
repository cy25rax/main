package com.example.demo.sexurity.services;

import com.example.demo.sexurity.interfaces.RoleRepository;
import com.example.demo.sexurity.interfaces.UserRepository;
import com.example.demo.sexurity.models.Role;
import com.example.demo.sexurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in in ");
        System.out.println("find by id " + userRepository.findById(1L));
        System.out.println();
        System.out.println("find all " + userRepository.findAll());


        User user = userRepository.findByName(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
