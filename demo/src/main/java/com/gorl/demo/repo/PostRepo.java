package com.gorl.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gorl.demo.entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
