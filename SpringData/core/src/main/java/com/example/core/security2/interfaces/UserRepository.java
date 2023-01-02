package com.example.core.security2.interfaces;

import com.example.core.security2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);

    Long findUserIdByName(String userName);

}
