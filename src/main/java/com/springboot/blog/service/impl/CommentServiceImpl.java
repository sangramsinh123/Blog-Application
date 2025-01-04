package com.springboot.blog.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	private ModelMapper mapper;

	public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository, ModelMapper mapper) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}


	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Comment comment = mapToEntity(commentDto);
		//retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		comment.setPost(post);
		Comment newComment = commentRepository.save(comment);
		
		
		return mapToDto(newComment);
	}
	
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setBody(commentDto.getBody());
		return comment;
	}


	@Override
	public List<CommentDto> getCommnetsByPostId(long postId) {
		// retrieve comments by postId
		List<Comment> comments = commentRepository.findByPostId(postId);
		List<CommentDto> allComments = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
		return allComments;
	}


	@Override
	public CommentDto getCommenById(long postId, long commentId) {
		//retrieve post by postId from Database
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		//from all comments select comments 
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(postId)) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doest not exist for this Post");
		}
		return mapToDto(comment);
	}


	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {
		//retrieve post by postId from Database
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		//retrieve comment by commenrId
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(postId)) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doest not exist for this Post");
		}
		
		comment.setName(commentRequest.getName());
		comment.setBody(commentRequest.getBody());
		Comment updatedComment = commentRepository.save(comment);
		return mapToDto(updatedComment);
	}


	@Override
	public String deleteComment(long postId, long commentId) {
		//retrieve post by postId from Database
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
				
		//retrieve comment by commenrId
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(postId)) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doest not exist for this Post");
		}
		
		//now delete from database
		commentRepository.delete(comment);
		
		return "Comment deleted successfully";
	}


	

}
