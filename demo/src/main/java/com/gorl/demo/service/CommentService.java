package com.gorl.demo.service;

import java.util.List;

import com.gorl.demo.dto.CommentDTO;

public interface CommentService {
	
	public CommentDTO createComment(long postId, CommentDTO commentDto);
	
	public List<CommentDTO> findCommentsByPostId(long id);
	
	public CommentDTO findCommentById(long postId, long commentId);
	
	public CommentDTO updateCommentById(long postId, long commentId, CommentDTO cmtDto);
	
	public String deleteCommentById(long postId, long commentId);

}
