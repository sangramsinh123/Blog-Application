package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(long postId, CommentDto commentDto);
	
	List<CommentDto> getCommnetsByPostId(long postId);
	
	CommentDto getCommenById(long postId, long commentId);
	
	CommentDto updateComment(long postId, long commentId, CommentDto commentRequest);
	
	String deleteComment(long postId, long commentId);
}
