package com.jdh.blog.service;

import com.jdh.blog.dto.BlogDto;

import java.util.List;

public interface BlogService {

    public List<BlogDto> getAllPersistedBlogs() throws Exception;
    public void persistBlog(BlogDto blogDto) throws Exception;

    public BlogDto getBlogById(Integer blogId) throws Exception;
    public void deleteBlogById(Integer blogId);
    public List<BlogDto> getBlogsByBlogName(String blogName);

    public void updateBlog(final BlogDto blogDto);

}
