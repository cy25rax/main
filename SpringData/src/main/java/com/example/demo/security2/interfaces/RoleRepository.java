package com.example.demo.security2.interfaces;

import com.example.demo.security2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
