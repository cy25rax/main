package com.example.demo.security2.interfaces;

import com.example.demo.security2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);
}
