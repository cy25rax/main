package com.example.demo.sexurity.interfaces;

import com.example.demo.sexurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);
}
