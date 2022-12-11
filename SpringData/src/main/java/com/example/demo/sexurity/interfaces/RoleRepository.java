package com.example.demo.sexurity.interfaces;

import com.example.demo.sexurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
