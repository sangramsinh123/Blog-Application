package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {
	private CommentService commentService;
	
	
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}


	@PostMapping("/posts/{id}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "id") long postId,@Valid @RequestBody CommentDto commentDto){
		CommentDto newComment = commentService.createComment(postId, commentDto);
		return new ResponseEntity<CommentDto>(newComment,HttpStatus.CREATED);
	}
	
	//get all comments for postId
	@GetMapping("/posts/{id}/comments")
	public ResponseEntity<List<CommentDto>> getCommnetsByPostId(@PathVariable(value = "id") long postId){
		List<CommentDto> allComents = commentService.getCommnetsByPostId(postId);
		return new  ResponseEntity<List<CommentDto>>(allComents,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommnetsByCommentId(@PathVariable(value = "postId") long postId,
															@PathVariable(value = "commentId")	long commentId){
		CommentDto coment = commentService.getCommenById(postId, commentId);
		return new  ResponseEntity<CommentDto>(coment,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId, 
													@PathVariable(value = "commentId") long commentId, 
													@Valid @RequestBody CommentDto commentRequest){
		CommentDto updatedComment = commentService.updateComment(postId, commentId, commentRequest);
		return new ResponseEntity<CommentDto>(updatedComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId, 
												@PathVariable(value = "commentId") long commentId){
		String deleteComment = commentService.deleteComment(postId, commentId) ;
		return new ResponseEntity<String> (deleteComment,HttpStatus.OK);
	}
	
}
