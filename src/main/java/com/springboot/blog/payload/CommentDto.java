package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentDto {
	private long id;
	@NotEmpty(message = "Name should not be Null or Empty")
	private String name;
	
	@NotEmpty(message = "Comment body should not be Null or Empty")
	private String body;
	
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getBody() {
		return body;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
