package com.gorl.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.demo.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	
	Optional<Category> findByName(String name); 

}
