package com.gorl.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gorl.demo.dto.CommentDTO;
import com.gorl.demo.service.CommentService;

@RestController
@RequestMapping("/api/post")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("create/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable long postId, @RequestBody CommentDTO cmtDto)
	{
		CommentDTO dto = commentService.createComment(postId, cmtDto);
		
		ResponseEntity<CommentDTO> response = new ResponseEntity<CommentDTO>(dto, HttpStatus.CREATED);
		
		
		return response;
	}
	
	@GetMapping("create/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable long postId)
	{
		List<CommentDTO> dto = commentService.findCommentsByPostId(postId);
		
		ResponseEntity<List<CommentDTO>> response = new ResponseEntity<List<CommentDTO>>(dto, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("create/{postId}/comments/{cmtId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable long postId, @PathVariable long cmtId)
	{
		CommentDTO dto = commentService.findCommentById(postId, cmtId);
		
		ResponseEntity<CommentDTO> response = new ResponseEntity<CommentDTO>(dto, HttpStatus.OK);
		
		return response;
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("create/{postId}/comments/{cmtId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable long postId, @PathVariable long cmtId, @RequestBody CommentDTO cmtDto)
	{
		CommentDTO dto = commentService.updateCommentById(postId, cmtId, cmtDto);
		
		ResponseEntity<CommentDTO> response = new ResponseEntity<CommentDTO>(dto, HttpStatus.OK);
		
		return response;
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("create/{postId}/comments/{cmtId}")
	public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long cmtId)
	{
		String delete = commentService.deleteCommentById(postId, cmtId);
		
		ResponseEntity<String> response = new ResponseEntity<String>(delete, HttpStatus.OK);
		
		return response;
	}
}

