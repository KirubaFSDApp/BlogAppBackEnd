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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gorl.demo.dto.PostCommentDto;
import com.gorl.demo.dto.PostDTO;
import com.gorl.demo.dto.PostPagingResponse;
import com.gorl.demo.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO dto)
	{
		PostDTO dtoPost = postService.createpost(dto);
		
		ResponseEntity<PostDTO> response = new ResponseEntity<PostDTO>(dtoPost, HttpStatus.CREATED);
		
		System.out.println(dto.toString());
		return response;
	}
	
	/*
	 //without pagination support
	@GetMapping("/all")
	public ResponseEntity<List<PostDTO>> getAllPosts()
	{
		 List<PostDTO> dto = postService.getAllPost();
		 
		 ResponseEntity<List<PostDTO>> response = new ResponseEntity<List<PostDTO>>(dto, HttpStatus.OK);
		
		 return response;
	}
	*/
	//pagination support
	@GetMapping("/all")
	public ResponseEntity<PostPagingResponse> getAllPost(
				@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
				@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
				@RequestParam(value = "sort", defaultValue = "title", required = false) String sort
			)
	{
		PostPagingResponse postPageRes = postService.getAllPost(pageNo, pageSize, sort);
		 
		 ResponseEntity<PostPagingResponse> response = new ResponseEntity<PostPagingResponse>(postPageRes, HttpStatus.OK);
		
		 return response;
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable long id)
	{
		PostDTO dto = postService.getByid(id);
		
		ResponseEntity<PostDTO> response = new ResponseEntity<PostDTO>(dto,HttpStatus.OK);
		
		
		return response;
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/all/{id}")
	public ResponseEntity<PostDTO> updatePostById(@PathVariable long id, @Valid @RequestBody PostDTO dto)
	{
		PostDTO dtopost = postService.updatePost(id,dto);
		ResponseEntity<PostDTO> response = new ResponseEntity<PostDTO>(dtopost,HttpStatus.OK);
		
		return response;
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/all/{id}")
	public ResponseEntity<String> deletepost(@PathVariable long id)
	{
		postService.deletePostById(id);
		
		ResponseEntity<String> response = new ResponseEntity<String>("Deleted success fully", HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/allPostCmt")
	public ResponseEntity<List<PostCommentDto>> findAllPostWitCmt()
	{
		List<PostCommentDto> dto = postService.getAllPostWithCmt();
		
		ResponseEntity<List<PostCommentDto>> response = new ResponseEntity<List<PostCommentDto>>(dto,HttpStatus.OK);
		
		return response;
	}
}
