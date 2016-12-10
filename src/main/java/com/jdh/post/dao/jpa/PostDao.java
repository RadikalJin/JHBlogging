package com.jdh.post.dao.jpa;

import com.jdh.post.domain.Post;

import java.util.List;

public interface PostDao {

	public void persistPost(Post post);
	public List<Post> getAllPersistedPosts() throws Exception;
	public Post getPostById(Integer postId) throws Exception;
	public void deletePersistedPostById(Integer postId);
	public void updatePost(Post post);
	public List<Post> getPostsByBlogName(String blogName);
	public List<Post> getPostsByUserName(String userName);
	public List<Post> getPostsByPostTitle(String postName);

}
