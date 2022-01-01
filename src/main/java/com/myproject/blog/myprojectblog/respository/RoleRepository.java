package com.myproject.blog.myprojectblog.respository;

import com.myproject.blog.myprojectblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
