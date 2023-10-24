package com.gorl.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.demo.entity.Comment;

public interface CommentsRepo extends JpaRepository<Comment, Long>{
	
	List<Comment> findByPostId(long postId);

}
