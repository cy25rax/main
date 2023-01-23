package com.example.auth.interfaces;

import com.example.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);

    Long findUserIdByName(String userName);
    
//    Optional<User> findByName(String username);
}
