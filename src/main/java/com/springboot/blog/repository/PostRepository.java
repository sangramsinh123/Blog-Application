package com.springboot.blog.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Post;

//We also dont need to annotate @Repository because JpaRepository have already
public interface PostRepository extends JpaRepository<Post,Long> {
	// Don't need to write any code because JpaRepository supports all CRUD as well as Pagination and sorting
	
}
