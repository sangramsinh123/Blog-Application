package com.springboot.blog.service.impl;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	private ModelMapper mapper;
	
	public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
		this.postRepository = postRepository;
		this.mapper = mapper;
	}


	@Override
	public PostDto createPost(PostDto postDto) {
		
		// Convert DTO into Entity
		Post post = mapToEntity(postDto);
		
		//SAVE TO DATABASE
		Post newPost = postRepository.save(post);//save in DB
		
		//convert entity to DTO
		PostDto postResponse = mapToDto(newPost);
		
		return postResponse;
	}


	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		//fetch all posts from Database
		
		//create Pageable instance
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		//get content from page object
		List<Post> listofPost = posts.getContent();
		List<PostDto> allposts = listofPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allposts);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}
	
	//Converted Entity Into DTO(Data Transfer Object)
	private PostDto mapToDto(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
		
		
		
//		PostDto postDto = new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		return postDto;
	}
	//Converted DTO INTO Entity
	private Post mapToEntity(PostDto postDto) {
		
		Post post = mapper.map(postDto, Post.class);
		
//		Post post = new Post();
//		post.setId(postDto.getId());
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		return post;
	}


	@Override
	public PostDto getPostbyId(long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		return mapToDto(post);
	}


	@Override
	public PostDto updatePostbyId(PostDto postDto, long postId) {
		// get post by ID from DB if post is not there in DB then throw exception
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}


	@Override
	public String deletePostbyId(long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		postRepository.delete(post);
		return "Post deleted successfully";
	}


	

}
