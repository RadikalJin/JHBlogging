package com.jdh.blog.dao.jpa;

import com.jdh.blog.domain.Blog;

import java.util.List;

public interface BlogDao {

	public void persistBlog(Blog blog);
	public List<Blog> getAllPersistedBlogs() throws Exception;
	public Blog getBlogById(Integer blogId) throws Exception;
	public void deletePersistedBlogById(Integer blogId);
	public void updateBlog(Blog blog);
	public List<Blog> getBlogsByBlogName(String blogName);

}
