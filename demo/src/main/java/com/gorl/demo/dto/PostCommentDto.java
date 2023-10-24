package com.gorl.demo.dto;

import java.util.List;
import java.util.Set;

import com.gorl.demo.entity.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class PostCommentDto {
	
	private String title;
	private String desc;
	private String content;
	private List<CommentDTO> comments;
	public PostCommentDto(String title, String desc, String content, List<CommentDTO> comments) {
		super();
		this.title = title;
		this.desc = desc;
		this.content = content;
		this.comments = comments;
	}
	public PostCommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	
	
}
