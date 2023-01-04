package com.example.auth.interfaces;

import com.example.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);

    Long findUserIdByName(String userName);

}
