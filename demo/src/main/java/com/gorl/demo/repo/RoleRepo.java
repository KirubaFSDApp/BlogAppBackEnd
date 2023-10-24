package com.gorl.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.demo.entity.Role;


public interface RoleRepo extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);
}
