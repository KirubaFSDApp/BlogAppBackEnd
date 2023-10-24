package com.gorl.demo.service;

import java.util.List;

import com.gorl.demo.dto.PostCommentDto;
import com.gorl.demo.dto.PostDTO;
import com.gorl.demo.dto.PostPagingResponse;

public interface PostService {
	
	public PostDTO createpost(PostDTO postDto); 
	
	public List<PostDTO> getAllPostWithoutPage();
	
	public PostPagingResponse getAllPost(int pageNo, int pageSize, String sort);
	
	public PostDTO getByid(long id);
	
	public PostDTO updatePost(long id, PostDTO dto);
	
	public void deletePostById(long id);
	
	public List<PostCommentDto> getAllPostWithCmt();
}
