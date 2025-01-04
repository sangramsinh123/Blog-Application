package com.springboot.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private List<PostDto> allposts;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	public List<PostDto> getAllPosts() {
		return allposts;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setContent(List<PostDto> allPosts) {
		this.allposts = allPosts;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
}
 