package com.jdh.post.dao.jpa;

import com.jdh.post.dao.jpa.*;
import com.jdh.post.domain.Post;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component(PostDaoImpl.BEAN_NAME)
public class PostDaoImpl implements PostDao {

	public static final String BEAN_NAME = "postDao";

	private final static Charset ENCODING = StandardCharsets.UTF_8;

	@PersistenceContext
	private EntityManager em;

	/*
	*
	* HIBERNATE
	*
	* */
	public void persistPost(Post post) {
		em.persist(post);
	}

	public void persistPosts(List<Post> posts) {
		for (Post post : posts) {
			persistPost(post);
		}
	}

	public void updatePost(Post post) {
		Post existingVersionOfPost = em.find(Post.class, post.getId());
		existingVersionOfPost.setBlog(post.getBlog());
		existingVersionOfPost.setUser(post.getUser());
		existingVersionOfPost.setTitle(post.getTitle());
		existingVersionOfPost.setCreatedDate(post.getCreatedDate());
		existingVersionOfPost.setBannerImageURL(post.getBannerImageURL());
		existingVersionOfPost.setContent(post.getContent());
		existingVersionOfPost.setTags(post.getTags());
		em.merge(existingVersionOfPost);
	}

	public List<Post> getAllPersistedPosts() {
		return em.createQuery("SELECT a FROM Post a", Post.class).getResultList();
	}

	public Post getPostById(Integer postId) {
		return em.find(Post.class, postId);
	}

	public void deletePersistedPostById(Integer postId) {
		Post post = em.find(Post.class, postId);
		if (post != null) {
			em.remove(post);
		}
	}

	public void deleteAllPersistedPosts() {
		em.createQuery("DELETE from Post").executeUpdate();
	}

	public List<Post> getPostsByBlogName(String blogName) {
		return em.createQuery("SELECT p FROM Post p JOIN FETCH p.blog WHERE p.blog.blogName = '" + blogName + "'", Post.class).getResultList();
	}

	public List<Post> getPostsByUserName(String userName) {
		return em.createQuery("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.username = '" + userName + "'", Post.class).getResultList();
	}

	public List<Post> getPostsByPostTitle(String postName) {
		return em.createQuery("SELECT a FROM Post a WHERE title = '" + postName + "'", Post.class).getResultList();
	}

}

