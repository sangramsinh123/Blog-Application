package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	//create blog post
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		PostDto newPost = postService.createPost(postDto);
		return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNo" , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir" , defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	){
		
		return new ResponseEntity<PostResponse>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostbyId(@PathVariable(name = "id") long postId) {
		return new ResponseEntity<PostDto>(postService.getPostbyId(postId),HttpStatus.OK);
	}
	
	//
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostbyId(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long postId) {
		return new ResponseEntity<PostDto>(postService.updatePostbyId(postDto, postId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostbyId(@PathVariable(name = "id") long postId) {
		return new ResponseEntity<String>(postService.deletePostbyId(postId),HttpStatus.OK);
	}
}
