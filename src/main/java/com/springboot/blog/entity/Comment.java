package com.springboot.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table(
		name = "comments"
)

public class Comment {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "body", nullable = false)
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)// postId is foriegn key for comment so we specify here
	private Post post;

	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
}
