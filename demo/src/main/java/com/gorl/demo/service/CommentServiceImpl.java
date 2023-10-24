package com.gorl.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gorl.demo.dto.CommentDTO;
import com.gorl.demo.entity.Comment;
import com.gorl.demo.entity.Post;
import com.gorl.demo.exception.BadRequestException;
import com.gorl.demo.exception.ResourcenotFoundException;
import com.gorl.demo.repo.CommentsRepo;
import com.gorl.demo.repo.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Override
	public CommentDTO createComment(long postId, CommentDTO commentDto) {
		
		Post post = postRepo.findById(postId).orElseThrow(
				() -> new ResourcenotFoundException("Post", "id", ""+postId));
		
		Comment commentEntity = new Comment();
		
		commentEntity.setUname(commentDto.getUname());
		commentEntity.setEmail(commentDto.getEmail());
		commentEntity.setBody(commentDto.getBody());
		commentEntity.setPost(post);
		
		commentEntity = commentRepo.save(commentEntity);
		
		commentDto.setId(commentEntity.getId());
		
		return commentDto;
	}
	public List<CommentDTO> findCommentsByPostId(long id)
	{
		List<Comment> listCmt = commentRepo.findByPostId(id);
		
		List<CommentDTO> listCmtDto = new ArrayList<>();
		
		for(Comment cmt:listCmt)
		{
			CommentDTO dto = new CommentDTO();
			dto.setId(cmt.getId());
			dto.setEmail(cmt.getEmail());
			dto.setUname(cmt.getUname());
			dto.setBody(cmt.getBody());
			
			listCmtDto.add(dto);
		}
		
		return listCmtDto;
	}
	
	public CommentDTO findCommentById(long postId, long commentId)
	{
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourcenotFoundException("Post", "id", ""+postId));
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourcenotFoundException("Comment", "id", ""+commentId));
		
		if(!post.getId().equals(comment.getPost().getId()))
		{
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Comment not belong to post");
		}
		
		CommentDTO dto = new CommentDTO();
		
		dto.setId(comment.getId());
		dto.setUname(comment.getUname());
		dto.setEmail(comment.getEmail());
		dto.setBody(comment.getBody());
		
		return dto;
	}
	
	public CommentDTO updateCommentById(long postId, long commentId, CommentDTO cmtDto)
	{
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourcenotFoundException("Post", "ID", ""+postId));
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourcenotFoundException("Comment", "ID", ""+commentId));
		
		if(!post.getId().equals(comment.getPost().getId()))
		{
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Comment Id and Post Id not matched");
		}
		
		comment.setUname(cmtDto.getUname());
		comment.setEmail(cmtDto.getEmail());
		comment.setBody(cmtDto.getBody());
		
		commentRepo.save(comment);
		
		return cmtDto;
	}
	
	public String deleteCommentById(long postId, long commentId)
	{
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourcenotFoundException("Post", "ID", ""+postId));
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourcenotFoundException("Comment", "ID", ""+commentId));
		
		if(!post.getId().equals(comment.getPost().getId()))
		{
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Comment Id and Post Id not matched");
		}
		
		commentRepo.delete(comment);
		
		return "Delete successful";
	}
}
