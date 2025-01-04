package com.springboot.blog.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

//
@Table(
		name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
	
	@Id //To identify Primary key of Post 
	//To Specify Primary key generation strategy
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	
	
	private long id;
	
	@Column(name = "title" , nullable = false)
	private String title;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "content", nullable = false)
	private String Content;

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
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Comment> comments = new HashSet<>();

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	
}
