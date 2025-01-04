package com.springboot.blog.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	//PostDto is Java Bean
	//Java Bean is nothing but fields with getters & setters methods
	private long id;
	
	//title should not be null or empty
	//title have at least 2 char
	@NotEmpty
	@Size(min = 2, message = "post title should have at least 2 char")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "post description should have at least 10 char")
	private String description;
	
	@NotEmpty
	private String Content;
	
	private Set<CommentDto> comments;
	
	// I don't understand why we use Dto(data transfer object)
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getContent() {
		return Content;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	
	
	
}
