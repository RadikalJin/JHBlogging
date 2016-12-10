package com.jdh.post.service;

import com.jdh.post.dto.PostDto;

import java.util.List;

public interface PostService {

    public List<PostDto> getAllPersistedPosts() throws Exception;
    public void persistPost(PostDto postDto) throws Exception;

    public PostDto getPostById(Integer postId) throws Exception;
    public void deletePostById(Integer postId);
    public List<PostDto> getPostsByBlogName(String blogName);
    public List<PostDto> getPostsByUsername(String username);
    public List<PostDto> getPostsByPostTitle(String postName);

    public void updatePost(final PostDto postDto);

}
